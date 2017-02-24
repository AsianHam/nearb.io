package com.example.asian.nearbio;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds theHeart = new LatLngBounds(
            new LatLng(39.822281, -84.914987), new LatLng(39.824926, -84.911393));
    List<LatLng> AllPoints = new ArrayList<LatLng>();
    List<String> AllNames = new ArrayList<String>();
    List<String> AllCategories = new ArrayList<String>();
    double EarlLat = 39.8238;
    double EarlLon = -84.9132;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
    List<String> allCategories = Arrays.asList("Airport", "Bar", "Cafe", "Earlham college", "Entertainment",
            "Hair salon", "Park", "Restaurant", "Shopping", "Supermarket");
    List<Float> allColors = Arrays.asList(197f,353f,25f,341f,30f,45f,120f,15f,274f,51f);

    HashMap hMapColor = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        for (int i = 0; i <allCategories.size(); i++){
            hMapColor.put(allCategories.get(i),allColors.get(i));
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION },
                    MY_PERMISSION_ACCESS_FINE_LOCATION);
            return;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapCreator();
        mMap = googleMap;

        for (int i = 0; i < AllPoints.size(); i++) {
            LatLng pos = AllPoints.get(i);
            String thisName = AllNames.get(i);
            String thisCategory = AllCategories.get(i);
            Log.d("checking category",thisCategory);
            Log.d("Checking hMap", hMapColor.get(thisCategory).toString());
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(thisCategory)
                    .snippet(thisName))
                    .setIcon(BitmapDescriptorFactory.defaultMarker(Float.valueOf(hMapColor.get(thisCategory).toString())));
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
                        thisCategory = thisCategory.substring(1,2).toUpperCase() + thisCategory.substring(2,thisCategory.length()-1);
                        AllCategories.add(thisCategory);
                        AllNames.add(thisName);
                        AllPoints.add(new LatLng(currentLat, currentLon));
                    }
                }
            }
        }
    }

    private float distanceCalc(double startLat, double startLon, double endLat, double endLon){
        float[] result = new float[1];
        Location.distanceBetween(startLat,startLon,endLat,endLon,result);
        return result[0] * 0.000621371192f;
    }
}
