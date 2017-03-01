package com.example.asian.nearbio;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GeoLocationManager {
    private static final String LOG_TAG = "GeoLocationManager";
    private static final long MINUTE = 60000;
    private static final float MIN_DIST_METER = 1000;
    private static final long MIN_TIME_MS = 15 * MINUTE;
    private static GeoLocationManager singleton = null;

    private LocationManager lManager = null;
    private Location mLastLocation = null;
    private final Object synchObj = new Object();

    public static GeoLocationManager getInstance(Context context) throws NoLocationProviderException {
        if (singleton == null) {
            singleton = new GeoLocationManager (context);
        }
        return singleton;
    }

    private GeoLocationManager (Context context) throws NoLocationProviderException {
        lManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        lManager.requestLocationUpdates(this.getBestLocationProvider(), MIN_TIME_MS, MIN_DIST_METER, (LocationListener) this);
    }

    /**
     * Best active location provider
     */
    private String getBestLocationProvider() throws NoLocationProviderException {
        java.util.List<String> providers = lManager.getProviders(true);

        if (providers != null && providers.size() > 0 && providers.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else if (providers != null && providers.size() > 0 && providers.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else {
            throw new NoLocationProviderException();
        }
    }

    public Location getMyLocation()  {
        synchronized (synchObj) {
            if (mLastLocation != null) {
                return mLastLocation;
            } else if (lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                return lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } else if (lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                return lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            while (mLastLocation == null) {
                try {
                    Log.i(LOG_TAG, "waiting for provider to return coordinate");
                    synchObj.wait();
                } catch (InterruptedException e) {
                }
            }
            return mLastLocation;
        }
    }

    public void onLocationChanged(Location location) {
        Log.i(LOG_TAG, "onLocationChanged");

        synchronized (synchObj) {
            mLastLocation = location;
            Log.i(LOG_TAG, "notifyAll()");
            synchObj.notifyAll();
        }
    }

    public void onProviderDisabled(String provider) {
        Log.i(LOG_TAG, "provider disabled: " + provider);
    }

    public void onProviderEnabled(String provider) {
        Log.i(LOG_TAG, "provider enabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
