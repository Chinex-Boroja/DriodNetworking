package com.example.androidnetworking;

public class EarthquakeData {

    private final double mMagnitude;
    private final String mLocation;
    private final long mTimeInMilliseconds;

    public EarthquakeData(double magnitude, String location, long timeInMilliseconds) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
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

}
