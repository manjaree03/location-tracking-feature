package com.location_tracking.manjaree_project.controller;

import com.location_tracking.manjaree_project.dto.CommonResponseDTO;
import com.location_tracking.manjaree_project.dto.LocationRequestDTO;
import com.location_tracking.manjaree_project.model.Location;
import com.location_tracking.manjaree_project.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class LocationRController
{
    @Autowired
    private LocationService locationService;

    @GetMapping(value="/api/locationHistory/{id}")
    public List<Location> locationHistoryOfUser(@PathVariable Long id, HttpServletRequest request, Model model)
    {
        List<Location> locationList=new ArrayList<>();
        try {
            locationList=locationService.getLocationHistoryOfUser(id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return locationList;
    }
    @PostMapping(value = "/api/addLocation")
    public CommonResponseDTO allUserList(@RequestBody LocationRequestDTO locationRequestDTO, HttpServletRequest request) {
        Location location = new Location();
        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();
            try {
               commonResponseDTO=locationService.addLocationOfUser(locationRequestDTO);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return commonResponseDTO;
    }

}
