package com.frankding.android.ridetj;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by frankding on 2/6/17.
 */

public class DataListener {
    private AppCompatActivity context;
    private ListView listView;

    //constructor
    public DataListener(AppCompatActivity context, ListView listView){
        this.context = context;
        this.listView = listView;
    }

    public void onSuccess(List<String> rideList){
        ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.ride_option, rideList);

        listView.setAdapter(adapter);
    }

}
