package com.example.assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.model.Alerts;

import java.util.List;

import static com.example.assignment3.MapsActivity.busesFilter;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder>  {
    private List<String> mDataset; //this is the data that is going to be recycled

    //the method implements the inteface and sets the view type
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
        }
    }

    public FilterAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public FilterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_view, parent, false);

        FilterAdapter.MyViewHolder vh = new FilterAdapter.MyViewHolder(v);
        return vh;
    }

    //this sets the values in the recycler view
    @Override
    public void onBindViewHolder(FilterAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView Message = holder.linearLayout.findViewById(R.id.route_number);
        Message.setText(mDataset.get(position));
//        if(mDataset.get(position).equals(busesFilter)){
//            Message.setTextColor(Color.parseColor("#ffffff"));
//            holder.linearLayout.setBackgroundColor(Color.parseColor("#1a2735"));
//        }
        final String value = mDataset.get(position);

        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busesFilter = value;
                Intent intent = new Intent(v.getContext(),MapsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
