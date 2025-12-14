package com.location_tracking.manjaree_project.serviceImpl;

import com.location_tracking.manjaree_project.Repository.LocationRepository;
import com.location_tracking.manjaree_project.Repository.UserRepository;
import com.location_tracking.manjaree_project.dto.CommonResponseDTO;
import com.location_tracking.manjaree_project.dto.LocationDateDTO;
import com.location_tracking.manjaree_project.dto.LocationRequestDTO;
import com.location_tracking.manjaree_project.model.Location;
import com.location_tracking.manjaree_project.model.User;
import com.location_tracking.manjaree_project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LocationServiceImpl implements LocationService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public CommonResponseDTO addLocationOfUser(LocationRequestDTO locationRequestDTO) {
        Location location1=new Location();
        Optional<User> user = userRepository.findByUserId(locationRequestDTO.getUser_id());
        if (user.isPresent()) {
            User u1 = new User();
            u1.setUserId(user.get().getUserId());
            location1 = new Location();
            location1.setUser(u1);

           Boolean flagLat=validateInput(locationRequestDTO.getLatitude());
            Boolean flagLng=validateInput(locationRequestDTO.getLongitude());
            if(flagLat && flagLng) {
                location1.setLatitude(locationRequestDTO.getLatitude());
                location1.setLongitude(locationRequestDTO.getLongitude());
                locationRepository.save(location1);
                return new CommonResponseDTO(true, "Location added successfully", null);
            }
            else {
                return new CommonResponseDTO(false,"Location Can Only Be Numerical Value",null);
                }
        }
        else {
            return new CommonResponseDTO(false,"User not found", null);
        }

    }


    public Boolean validateInput(String input) {
        String pattern = "^[0-9]+$";
        try {
            float floatValue = Float.parseFloat(input);
            if (floatValue > 0.0f) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Location> getLocationHistoryOfUser(Long id)
    {
        Optional<User> user = userRepository.findByUserId(id);
        List<Location> locationList=new ArrayList<>();
        if (user.isPresent())
        {
            locationList=locationRepository.getLocationHistoryUser(id);
        }
        return locationList;
    }

    @Override
    public List<Location> getLocationDateWise(LocationDateDTO locationDateDTO) {
        List<Location> locationList = new ArrayList<>();

        String start = locationDateDTO.getStartDate();
        String end = locationDateDTO.getEndDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;

        try {
            startDate = dateFormat.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(end));
            calendar.add(Calendar.DATE, 1);
            endDate = calendar.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return locationList;
        }
        locationList = locationRepository.getLocationDateWise(locationDateDTO.getUserId(), startDate, endDate);

        return locationList;
    }


}
