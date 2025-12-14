package com.location_tracking.manjaree_project.service;

import com.location_tracking.manjaree_project.dto.CommonResponseDTO;
import com.location_tracking.manjaree_project.dto.LocationDateDTO;
import com.location_tracking.manjaree_project.dto.LocationRequestDTO;
import com.location_tracking.manjaree_project.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService
{

    CommonResponseDTO addLocationOfUser(LocationRequestDTO locationRequestDTO);
    List<Location> getLocationHistoryOfUser(Long id);
    List<Location> getLocationDateWise(LocationDateDTO locationDateDTO);
}
