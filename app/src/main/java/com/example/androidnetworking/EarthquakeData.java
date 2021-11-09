package com.example.androidnetworking;

public class EarthquakeData {

    private final String mMagnitude;
    private final String mLocation;
    private final String mDate;

    public EarthquakeData(String magnitude, String location, String date) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDate = date;
    }

    /**
     * Get the magnitude
     */
    public String getmMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location
     */
    public String getmLocation() {
        return mLocation;
    }

    /**
     * Get the date
     */
    public String getmDate() {
        return mDate;
    }

}
