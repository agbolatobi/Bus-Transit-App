package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.database;

public class MainActivity extends AppCompatActivity {
    TextView Tracker;
    TextView Alerts;
    TextView Filter;
    //this initializes all the events when the activity is called
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Tracker = findViewById(R.id.tracker);
        Alerts = findViewById(R.id.alert);
        Filter = findViewById(R.id.filter);

        if(database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession").getIsTrackerOn()){
            Tracker.setText("User Tracker On");
            Tracker.setBackgroundColor(getColor(R.color.colorPrimary));
            Tracker.setTextColor(getColor(R.color.colorWhite));
        }else{
            Tracker.setText("User Tracker Off");
            Tracker.setBackgroundColor(getColor(R.color.colorAccent));
            Tracker.setTextColor(getColor(R.color.colorWhite));
        }

        Tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getAppDatabase(getApplicationContext()).userDAO().setTracker("UserSession",!database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession").getIsTrackerOn());
                if(database.getAppDatabase(getApplicationContext()).userDAO().findByItem("UserSession").getIsTrackerOn()){
                    Tracker.setText("User Tracker On");
                    Tracker.setBackgroundColor(getColor(R.color.colorPrimary));
                    Tracker.setTextColor(getColor(R.color.colorWhite));
                }else{
                    Tracker.setText("User Tracker Off");
                    Tracker.setBackgroundColor(getColor(R.color.colorAccent));
                    Tracker.setTextColor(getColor(R.color.colorWhite));
                }
            }
        });

        Alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AlertActivity.class);
                startActivity(i);
            }
        });

        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),FilterActivity.class);
                startActivity(i);
            }
        });

    }

}
