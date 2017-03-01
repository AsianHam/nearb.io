package com.example.asian.nearbio;

public class Event implements Comparable<Event> {
    private String mHtmlDescription = null;
    private String mTitle = null;
    private String mUrl = null;
    private float mLatitude = 0.0f;
    private float mLongitude = 0.0f;
    private String mStartDate = null;
    private float mDistance = 0.0f;  //in miles
    //private String = null;

    public Event (){
        super();
    }

    public void setHtmlDescription(String htmlDescription) {
        this.mHtmlDescription = htmlDescription;
    }
    public String getHtmlDescription() {
        return mHtmlDescription;
    }
    public void setTitle(String title) {
        this.mTitle = title;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setUrl(String url) {
        this.mUrl = url;
    }
    public String getUrl() {
        return mUrl;
    }

    public void setStartDate(String startDate) {
        this.mStartDate = startDate;
    }
    public String getStartDate() {
        return mStartDate;
    }

    public void setLatitude(float latitude) {
        this.mLatitude = latitude;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public void setLongitude(float longitude) {
        this.mLongitude = longitude;
    }

    public float getLongitude() {
        return mLongitude;
    }

    public void setDistance(float distance) {
        this.mDistance = distance;
    }

    public float getDistance() {
        return mDistance;
    }

    public int compareTo(Event anotherEvent) {
        if (this.mDistance < anotherEvent.getDistance()) {
            return -1;
        } else if (this.mDistance < anotherEvent.getDistance()) {
            return 1;
        } else {
            return 0;
        }
    }
}