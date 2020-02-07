package com.example.assignment3.model;

public class Buses {
    //this is the buses class for the bus object
    private String bus_ID;
    private  String bus_Name;
    private float bus_lat;
    private float getBus_long;
    private float bus_angle;
    private String bus_route;

    public float getBus_lat() {
        return bus_lat;
    }

    public float getGetBus_long() {
        return getBus_long;
    }

    public String getBus_ID() {
        return bus_ID;
    }

    public void setBus_ID(String bus_ID) {
        this.bus_ID = bus_ID;
    }

    public void setBus_lat(float bus_lat) {
        this.bus_lat = bus_lat;
    }

    public void setGetBus_long(float getBus_long) {
        this.getBus_long = getBus_long;
    }

    public String getBus_Name() {
        return bus_Name;
    }

    public void setBus_Name(String bus_Name) {
        this.bus_Name = bus_Name;
    }

    public float getBus_angle() {
        return bus_angle;
    }

    public void setBus_angle(float bus_angle) {
        this.bus_angle = bus_angle;
    }

    public String getBus_route() {
        return bus_route;
    }

    public void setBus_route(String bus_route) {
        this.bus_route = bus_route;
    }
}
