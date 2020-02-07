package com.example.assignment3.model;

import android.util.Log;

import com.google.transit.realtime.GtfsRealtime;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.assignment3.MapsActivity.busesFilter;

public class APIcalls {
    //this calls the API to get the buses
    public List<Buses> getVehiclePositions() throws Exception {
        Log.d("API","called");
        List<Buses> BusList = new ArrayList<Buses>();
        URL url = new URL("http://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb");
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if(entity.hasId() && entity.hasVehicle()){
                if(busesFilter=="All"){
                    Buses bus = new Buses();
                    bus.setBus_ID(entity.getVehicle().getVehicle().getId());
                    bus.setBus_Name(entity.getVehicle().getTrip().getRouteId());
                    bus.setBus_lat(entity.getVehicle().getPosition().getLatitude());
                    bus.setGetBus_long(entity.getVehicle().getPosition().getLongitude());
                    bus.setBus_angle(entity.getVehicle().getPosition().getBearing());
                    BusList.add(bus);
                }else{
                    if(entity.getVehicle().getTrip().getRouteId().equals(busesFilter)){
                        Buses bus = new Buses();
                        bus.setBus_ID(entity.getVehicle().getVehicle().getId());
                        bus.setBus_Name(entity.getVehicle().getTrip().getRouteId());
                        bus.setBus_lat(entity.getVehicle().getPosition().getLatitude());
                        bus.setGetBus_long(entity.getVehicle().getPosition().getLongitude());
                        bus.setBus_angle(entity.getVehicle().getPosition().getBearing());
                        BusList.add(bus);
                    }
                }

            }

        }
        return BusList;
    }

    //this calls the API to get the alerts and returns it to a list
    public List<Alerts> getAlerts() throws Exception {
        List<Alerts> AlertsList = new ArrayList<Alerts>();
        URL url = new URL("http://gtfs.halifax.ca/realtime/Alert/Alerts.pb");
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        int count = 0;
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if (entity.hasAlert()) {
                Alerts item = new Alerts();
                count = count +1;
                item.setMessage(entity.getAlert().getDescriptionText().getTranslation(0).getText());
                item.setEffect(entity.getAlert().getEffect().getValueDescriptor().getName());
                item.setId(count);
                AlertsList.add(item);
            }
        }
        return AlertsList;
    }

    //this calls the api to get the bus routes and returns it to a list
    public List<String> getBusRoutes() throws Exception {
        Log.d("API","called");
        List<String> list = new ArrayList<String>();
        URL url = new URL("http://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb");
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        list.add("All");
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if(entity.hasId() && entity.hasVehicle()){

                if(!list.contains(entity.getVehicle().getTrip().getRouteId())){
                    list.add(entity.getVehicle().getTrip().getRouteId());
                }

            }

        }
        return list;
    }

    public List<Trip> getTripUpdates() throws Exception {
        List<Trip> tripList = new ArrayList<Trip>();
        URL url = new URL("http://gtfs.halifax.ca/realtime/TripUpdate/TripUpdates.pb");
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if (entity.hasTripUpdate()) {
                entity.getTripUpdate();
            }
        }
        return tripList;
    }
}
