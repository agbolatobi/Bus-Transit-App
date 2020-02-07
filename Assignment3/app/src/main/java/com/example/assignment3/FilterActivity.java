package com.example.assignment3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {
    public static List<String> ROUTES = new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);
        // this initializes the Recycler variables and passes the data to the Recycler view
        recyclerView = (RecyclerView) findViewById(R.id.filter_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new FilterAdapter(ROUTES);
        recyclerView.setAdapter(mAdapter);

    }
}
