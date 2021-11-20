package com.example.androidnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeData>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** TextView to show when the list is empty */
    private TextView mEmptyState;

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // get the list of earthquake locations.
        //ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes();

        // Create a new {@link EarthquakeAdapter} of earthquakes
        //final EarthquakeAdapter adapter = new EarthquakeAdapter(this,  earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<EarthquakeData>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);
        mEmptyState = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyState);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current earthquake that was clicked on
                EarthquakeData currentData = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentData.getmUrl());

                Intent intent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(intent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            //Update the empty state with no connection error message
            mEmptyState.setText(R.string.no_internet_connection);
        }

//        //Start the AsyncTask to fetch the earthquake data
//        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);
    }

    @NonNull
    @Override
    public Loader<List<EarthquakeData>> onCreateLoader(int id, @Nullable Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<EarthquakeData>> loader, List<EarthquakeData> data) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
        // Set empty state text to display "No earthquakes found."
        mEmptyState.setText(R.string.no_earthquakes);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<EarthquakeData>> loader) {
        mAdapter.clear();

    }


    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
//    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthquakeData>> {
//
//        /**
//         * This method is invoked (or called) on a background thread, so we can perform
//         * long-running operations like making a network request.
//         * <p>
//         * It is NOT okay to update the UI from a background thread, so we just return an
//         * {@link EarthquakeData} object as the result.
//         */
//        @Override
//        protected List<EarthquakeData> doInBackground(String... urls) {
//            //Don't perform the request if there are no URLs, or the first URL is null
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            List<EarthquakeData> result = QueryUtils.fetchEarthquakeData(urls[0]);
//            return result;
//        }
//
//
//        /**
//         * This method is invoked on the main UI thread after the background work has been
//         * completed.
//         *
//         * It IS okay to modify the UI within this method. We take the {@link EarthquakeData} object
//         * (which was returned from the doInBackground() method) and update the views on the screen.
//         */
//        @Override
//        protected void onPostExecute(List<EarthquakeData> data) {
//            // Clear the adapter of previous earthquake data
//                mAdapter.clear();
//
//            // If there is a valid list of {@link EarthquakeData}s, then
//            //add them to the adapter's data set.
//            // This will trigger the ListView to update
//            if (data != null && !data.isEmpty()){
//                return;
//            }
//            mAdapter.addAll(data);
//        }
//    }
}