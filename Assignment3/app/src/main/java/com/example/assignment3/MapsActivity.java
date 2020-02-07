package com.example.assignment3;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.model.Buses;
import com.example.assignment3.model.User.User;
import com.example.assignment3.model.database;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.TreeMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    public static TreeMap<String, Marker> mMarkers = new TreeMap<String, Marker>();
    public static Marker mLocationMarker;
    public static String busesFilter;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng CurrentLocation;
    public TextView topText;

    //this method is called when the intent is activated it calls all the other functionalities, initializes the map and the API calls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(busesFilter==null){
            busesFilter = "All";
        }
        topText = findViewById(R.id.Top_text);
        topText.setText(busesFilter+" Buses");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        User user = database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession");
            CurrentLocation = new LatLng(user.getLat(), user.getLon());

        ImageView imageViewMyLocation = (ImageView) findViewById(R.id.center_pin);
        imageViewMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerMapOnLocation();
            }
        });

        ImageView imageViewSettings = findViewById(R.id.settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Function is called when the map is build it is user to initialize all the markers and to call all the background activities
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            mMap.setMinZoomPreference(15);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
         new apiRunnable().execute();
        new AlertsapiRunnable().execute();
        new FilterapiRunnable().execute();
        updateBuses();
        trackUser();

    }
    //this function is a timer that calls the updated buses at intervals
    private void updateBuses(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        new apiRunnable().execute();
                    }
                },0,
                15000
        );
    }
    //this method tracks the users location every by calling a method to get the users location and move the marker ever three seconds
    private void trackUser(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Log.d("Current_location","Test1");
                        if(database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession").getIsTrackerOn()){
                            moverUserLocation();
                        }
                    }
                },0,
                3000
        );
    }

    // this method centers the map to the users location and puts a marker to mark the user
    private void centerMapOnLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng CurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                if(mLocationMarker!=null){
                                    mLocationMarker.setPosition(CurrentLocation);
                                }else{
                                    Marker marker = mMap.addMarker(new MarkerOptions().position(CurrentLocation));
                                    mLocationMarker=marker;
                                }

                                mMap.moveCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
                            }
                        }
                    });
        }else{
            Toast permissionMessage = Toast.makeText(this,"Location Permission Denied",Toast.LENGTH_LONG);
            permissionMessage.show();
        }
    }
    //this method moves the markr to the users location when the functionality is enabled
    private void moverUserLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng CurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                if(mLocationMarker!=null){
                                    mLocationMarker.setPosition(CurrentLocation);
                                }else{
                                    Marker marker = mMap.addMarker(new MarkerOptions().position(CurrentLocation));
                                    mLocationMarker=marker;
                                }
                                Log.d("Current_location","Test");
                            }
                        }
                    });
        }
    }
    //this saves the users state when the application is paused
    @Override
    protected void onPause(){
        super.onPause();
        database.getAppDatabase(getApplicationContext()).userDAO().setLat("UserSession",mMap.getCameraPosition().target.latitude);
        database.getAppDatabase(getApplicationContext()).userDAO().setLon("UserSession",mMap.getCameraPosition().target.longitude);
    }

    //this reinitialises the user state when the application is renewed
    @Override
    protected void onResume(){
        super.onResume();
        User user = database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession");
        CurrentLocation = new LatLng(user.getLat(), user.getLon());
        mMarkers = new TreeMap<String, Marker>();
        new apiRunnable().execute();

    }
    //this saves the users state when the application is stopped
    @Override
    protected void onStop(){
        super.onStop();
        database.getAppDatabase(getApplicationContext()).userDAO().setLat("UserSession",mMap.getCameraPosition().target.latitude);
        database.getAppDatabase(getApplicationContext()).userDAO().setLon("UserSession",mMap.getCameraPosition().target.longitude);
    }

    //this saves the users state when the application is destoryed or closed
    @Override
    protected void onDestroy(){
        super.onDestroy();
        database.getAppDatabase(getApplicationContext()).userDAO().setLat("UserSession",mMap.getCameraPosition().target.latitude);
        database.getAppDatabase(getApplicationContext()).userDAO().setLon("UserSession",mMap.getCameraPosition().target.longitude);
    }
}
