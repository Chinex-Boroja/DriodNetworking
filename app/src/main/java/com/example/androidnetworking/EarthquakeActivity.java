package com.example.androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // Create a fake list of earthquake locations.
        ArrayList<EarthquakeData> earthquakes = new ArrayList<EarthquakeData>();
        earthquakes.add(new EarthquakeData("6.1","London", "July 2, 2016"));
        earthquakes.add(new EarthquakeData("7.0","New York","2 May, 2021"));
        earthquakes.add(new EarthquakeData("4.5","New Delhi", "March 2, 2018"));
        earthquakes.add(new EarthquakeData("8.9","Campton", "Feb 2, 2016"));
        earthquakes.add(new EarthquakeData("7.0","Lagos", "Oct 2, 2010"));
        earthquakes.add(new EarthquakeData("5.6","Lisbon", "June 5, 2018"));
        earthquakes.add(new EarthquakeData("7.2","Abuja", "Feb 8, 2017"));
        earthquakes.add(new EarthquakeData("7.8","Georgia", "Sept 2, 2018"));
        earthquakes.add(new EarthquakeData("1.3","Vegas", "Feb 20, 2016"));
        earthquakes.add(new EarthquakeData("4.2","Paris", "Dec 2, 2018"));

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,  earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}