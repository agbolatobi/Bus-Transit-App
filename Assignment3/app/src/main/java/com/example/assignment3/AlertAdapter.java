package com.example.assignment3;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.model.Alerts;
import com.google.transit.realtime.GtfsRealtime;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {

    private List<Alerts> mDataset;
    //the method implements the inteface and sets the view type
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
        }
    }

    public AlertAdapter(List<Alerts> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public AlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    //this sets the values in the recycler view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       TextView MessageText = holder.linearLayout.findViewById(R.id.alertMessage);
       MessageText.setText(mDataset.get(position).getMessage());
        TextView EffectText = holder.linearLayout.findViewById(R.id.alertEffect);
        EffectText.setText(mDataset.get(position).getEffect());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
