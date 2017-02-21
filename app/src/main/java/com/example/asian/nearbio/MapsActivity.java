package com.example.asian.nearbio;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds theHeart = new LatLngBounds(
            new LatLng(39.822281, -84.914987), new LatLng(39.824926, -84.911393));
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    List<LatLng> AllPoints = new ArrayList<LatLng>();
    List<String> AllNames = new ArrayList<String>();
    double EarlLat = 39.8238;
    double EarlLon = -84.9132;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
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
            int radialDist = bundle.getInt("distance");
            List<String> categories = bundle.getStringArrayList("categories");
            InputStream inputStream = getResources().openRawResource(R.raw.processed_airports);
            CSVFile csvFile = new CSVFile(inputStream);
            List PlacesList = csvFile.read();

            for (int i = 0; i < PlacesList.size(); i++) {
                String[] Place = (String[]) PlacesList.get(i);
                double currentLat = Double.parseDouble(Place[4]);
                double currentLon = Double.parseDouble(Place[5]);
                String thisName = Place[0];
                String thisCategory = Place[7];

                if (categories.contains(thisCategory)){

                    float distance = distanceCalc(startLat,startLon,currentLat,currentLon);

                    if (distance < radialDist) {
                        AllNames.add(thisName);
                        AllPoints.add(new LatLng(currentLat, currentLon));
//                        Log.d("Checking type", thisCategory);    //For debugging
                    }
                }
            }
        }
    }

    private float distanceCalc(double startLat, double startLon, double endLat, double endLon){
        float[] result = new float[1];
        Location.distanceBetween(startLat,startLon,endLat,endLon,result);
        return result[0] * 0.000621371192f;
=======
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //LatLng theHeart = new LatLng(39.823230, -84.913315);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.addMarker(new MarkerOptions().position(theHeart).title("The Heart"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(theHeart.getCenter(), 17));
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}
