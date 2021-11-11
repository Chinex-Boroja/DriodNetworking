package com.example.androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // get the list of earthquake locations.
        ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes();

        // Create a new {@link EarthquakeAdapter} of earthquakes
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this,  earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener((parent, view, position, id) -> {
            // Find the current earthquake that was clicked on
            EarthquakeData currentData = adapter.getItem(position);

            // Convert the String URL into a URI object (to pass into the Intent constructor)
            Uri earthquakeUri = Uri.parse(currentData.getmUrl());

            Intent intent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
            startActivity(intent);
        });
    }
}