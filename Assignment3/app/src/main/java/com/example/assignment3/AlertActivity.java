package com.example.assignment3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.model.Alerts;

import java.util.ArrayList;
import java.util.List;

public class AlertActivity extends AppCompatActivity {
    public static List<Alerts> dataAlert = new ArrayList<Alerts>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);

        // this initializes the Recycler variables and passes the data to the Recycler view
        recyclerView = (RecyclerView) findViewById(R.id.alert_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AlertAdapter(dataAlert);
        recyclerView.setAdapter(mAdapter);
    }
}
