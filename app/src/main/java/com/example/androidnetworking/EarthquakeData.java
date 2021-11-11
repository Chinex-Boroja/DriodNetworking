package com.example.androidnetworking;

public class EarthquakeData {

    private final double mMagnitude;
    private final String mLocation;
    private final long mTimeInMilliseconds;
    private final String mUrl;

    public EarthquakeData(double magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    /**
     * Get the magnitude return the result
     */
    public double getmMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location and return the result
     */
    public String getmLocation() {
        return mLocation;
    }

    /**
     * Get the date return the result
     */
    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getmUrl() {
        return mUrl;
    }

}
