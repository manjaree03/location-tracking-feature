package com.location_tracking.manjaree_project.controller;

import com.location_tracking.manjaree_project.dto.LocationDateDTO;
import com.location_tracking.manjaree_project.dto.LocationTrackDTO;
import com.location_tracking.manjaree_project.model.Location;
import com.location_tracking.manjaree_project.model.User;
import com.location_tracking.manjaree_project.service.LocationService;
import com.location_tracking.manjaree_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/portal")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    /**
     * PAGE 1: Select User
     */
    @GetMapping("/selectUserForLocation")
    public String selectUserForLocationHistory(Model model) {

        try {
            List<User> userList = userService.getUserList();
            model.addAttribute("userList", userList);
        } catch (Exception e) {
            log.error("Error loading user list", e);
        }

        return "admin/selectUserToGetLocation";
    }

    /**
     * Redirect after selecting user
     */
    @PostMapping("/selectUserForLocation")
    public String getUserForLocationHistory(@RequestParam("selectedUser") Long userId) {
        return "redirect:/portal/locationUser/" + userId;
    }

    /**
     * PAGE 2: Location Map + Table
     */
    @GetMapping("/locationUser/{userId}")
    public String selectUserForLocation(@PathVariable Long userId, Model model) {

        List<LocationTrackDTO> locationTrackList = new ArrayList<>();

        try {
            List<Location> locationList =
                    locationService.getLocationHistoryOfUser(userId);

            for (int i = 0; i < locationList.size(); i++) {

                Location current = locationList.get(i);
                LocationTrackDTO dto = new LocationTrackDTO();

                dto.setLatitude(current.getLatitude());
                dto.setLongitude(current.getLongitude());
                dto.setVisitedAt(current.getCreatedAt());

                // ⏱ Time spent calculation
                if (i == 0) {
                    dto.setTimeSpentMinutes(null);
                } else {
                    Date prevTime = locationList.get(i - 1).getCreatedAt();
                    Date currTime = current.getCreatedAt();

                    long diffMinutes =
                            (currTime.getTime() - prevTime.getTime()) / (1000 * 60);

                    dto.setTimeSpentMinutes(diffMinutes);
                }

                locationTrackList.add(dto);
            }

            model.addAttribute("locations", locationTrackList);
            model.addAttribute("userId", userId);

        } catch (Exception e) {
            log.error("Error fetching location history for user {}", userId, e);
            return "redirect:/portal/selectUserForLocation";
        }

        return "admin/locationUser";
    }

    /**
     * OPTIONAL: Date-wise filtering (kept but cleaned)
     */
    @PostMapping("/locationUser/{userId}")
    public String locationUserByDate(
            @PathVariable Long userId,
            @ModelAttribute LocationDateDTO locationDateDTO,
            Model model) {

        try {
            locationDateDTO.setUserId(userId);

            model.addAttribute("startDate", locationDateDTO.getStartDate());
            model.addAttribute("endDate", locationDateDTO.getEndDate());
            model.addAttribute(
                    "locations",
                    locationService.getLocationDateWise(locationDateDTO)
            );
            model.addAttribute("userId", userId);

        } catch (Exception e) {
            log.error("Error filtering locations by date", e);
        }

        return "admin/locationUser";
    }
}
