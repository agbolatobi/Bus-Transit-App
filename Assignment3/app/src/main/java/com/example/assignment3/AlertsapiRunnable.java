package com.example.assignment3;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.model.APIcalls;
import com.example.assignment3.model.Alerts;
import com.example.assignment3.model.Buses;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.transit.realtime.GtfsRealtime;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment3.AlertActivity.dataAlert;
import static com.example.assignment3.MapsActivity.mMap;
import static com.example.assignment3.MapsActivity.mMarkers;

public class AlertsapiRunnable extends AsyncTask {
    List<Alerts> alerts = new ArrayList<Alerts>();

    //this calls the API in the backgroud
    @Override
    protected List<Alerts> doInBackground(Object[] objects) {
        APIcalls apIcalls = new APIcalls();

        try{
            alerts = apIcalls.getAlerts();
        }catch(Exception e){
            Log.d("API",e.getMessage());
        }
        Log.d("API#Alerts",""+alerts.size()+"");

        return  alerts;
    }

    //this sends the result to the main thread
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        for(Alerts alert : alerts){
            dataAlert.add(alert);
        }


    }
}
