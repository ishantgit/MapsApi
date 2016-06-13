package com.example.ishant.driventest.entities.models;

import com.orm.SugarRecord;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Ishant Rana on 13/06/16.
 */
public class LocationModel extends SugarRecord{

    public LocationModel(){

    }
    private Double latitude;

    private Double longitude;

    private Long created_at;

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

