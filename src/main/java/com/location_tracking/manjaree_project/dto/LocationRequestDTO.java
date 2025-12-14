package com.location_tracking.manjaree_project.dto;

import lombok.Data;

@Data
public class LocationRequestDTO {
    private long user_id;

    private String latitude;

    private String longitude;

    private String fcm_token;

}
