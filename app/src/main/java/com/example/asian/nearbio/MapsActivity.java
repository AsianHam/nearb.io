package com.example.asian.nearbio;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds theHeart = new LatLngBounds(
            new LatLng(39.822281, -84.914987), new LatLng(39.824926, -84.911393));
    List<LatLng> AllPoints = new ArrayList();
    List<String> AllNames = new ArrayList();
    double EarlLat = 39.8238;
    double EarlLon = -84.9132;
//    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapCreator();
        mMap = googleMap;

        for (int i = 0; i < AllPoints.size(); i++) {
            LatLng pos = AllPoints.get(i);
            String thisName = AllNames.get(i);
            mMap.addMarker(new MarkerOptions().position(pos).title(thisName));
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        /*    ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COARSE_LOCATION);   */
            ActivityCompat.requestPermissions(this,
                    new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION },
                    MY_PERMISSION_ACCESS_FINE_LOCATION);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(theHeart.getCenter(), 17));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void mapCreator(){
        double startLat = EarlLat;
        double startLon = EarlLon;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            InputStream inputStream = getResources().openRawResource(R.raw.processed_airports);
            CSVFile csvFile = new CSVFile(inputStream);
            List PlacesList = csvFile.read();
            for (int i = 0; i < PlacesList.size(); i++) {
                String[] Place = (String[]) PlacesList.get(i);
                double currentLat = Double.parseDouble(Place[4]);
                double currentLon = Double.parseDouble(Place[5]);
                String thisName = Place[0];

                float distance = distanceCalc(startLat,startLon,currentLat,currentLon);

                if (distance < 150) {
                    AllNames.add(thisName);
                    AllPoints.add(new LatLng(currentLat, currentLon));
                }
            }
        }
    }

    private float distanceCalc(double startLat, double startLon, double endLat, double endLon){
        float[] result = new float[1];
        Location.distanceBetween(startLat,startLon,endLat,endLon,result);
        float distanceInMiles = result[0] * 0.000621371192f;
        return distanceInMiles;
    }
}
