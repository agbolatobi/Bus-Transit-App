package com.example.assignment3;

import android.os.AsyncTask;
import android.util.Log;

import com.example.assignment3.model.APIcalls;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment3.FilterActivity.ROUTES;

public class FilterapiRunnable extends AsyncTask {
    List<String>  busRoutes = new ArrayList<String>();

    //this calls the API in the backgroud
    @Override
    protected List<String> doInBackground(Object[] objects) {
        APIcalls apIcalls = new APIcalls();

        try{
            busRoutes = apIcalls.getBusRoutes();
        }catch(Exception e){
            Log.d("API",e.getMessage());
        }
        Log.d("API#Alerts",""+busRoutes.size()+"");

        return busRoutes;
    }

    //this sends the result to the main thread
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        for(String routes : busRoutes){
            ROUTES.add(routes);
        }


    }
}
