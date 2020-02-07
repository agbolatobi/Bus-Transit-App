package com.example.assignment3;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.assignment3.model.APIcalls;
import com.example.assignment3.model.Buses;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment3.MapsActivity.mMap;
import static com.example.assignment3.MapsActivity.mMarkers;

public class apiRunnable extends AsyncTask {
    List<Buses> buses = new ArrayList<Buses>();

    //this calls the API in the backgroud
    @Override
    protected List<Buses> doInBackground(Object[] objects) {
        APIcalls apIcalls = new APIcalls();

        try{
            buses = apIcalls.getVehiclePositions();
        }catch(Exception e){
            Log.d("API",e.getMessage());
        }
        Log.d("API#",""+buses.size()+"");

        return  buses;
    }

    //this sends the result to the main thread
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        for(Buses bus : buses){
            LatLng busLocation = new LatLng(bus.getBus_lat(), bus.getGetBus_long());
            if(mMarkers.containsKey(bus.getBus_ID())){
                mMarkers.get(bus.getBus_ID()).setPosition(busLocation);
                mMarkers.get(bus.getBus_ID()).setRotation(bus.getBus_angle());

            }else {
                Marker marker = mMap.addMarker(new MarkerOptions().position(busLocation).title(bus.getBus_Name()).rotation(bus.getBus_angle()).icon(BitmapDescriptorFactory.fromResource(R.drawable.buses_map)));
                mMarkers.put(bus.getBus_ID(),marker);
            }
        }
    }
}
