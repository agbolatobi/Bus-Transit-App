package com.example.assignment3.model.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumnInfo(name = "lat")
    private double lat;
    @ColumnInfo(name = "lon")
    private double lon;
    @ColumnInfo(name = "marker")
    private  String marker;
    @ColumnInfo(name = "isTrackerOn")
    private  boolean isTrackerOn;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getMarker() {
        return marker;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public boolean getIsTrackerOn() {
        return isTrackerOn;
    }

    public void setIsTrackerOn(boolean isTrackerOn) {
        this.isTrackerOn = isTrackerOn;
    }
}
