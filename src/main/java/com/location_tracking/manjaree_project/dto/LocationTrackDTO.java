package com.location_tracking.manjaree_project.dto;

import java.util.Date;

public class LocationTrackDTO {

    private String latitude;
    private String longitude;
    private Date visitedAt;
    private Long timeSpentMinutes; // null for first entry
    private String placeName;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }

    public Long getTimeSpentMinutes() {
        return timeSpentMinutes;
    }

    public void setTimeSpentMinutes(Long timeSpentMinutes) {
        this.timeSpentMinutes = timeSpentMinutes;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}

