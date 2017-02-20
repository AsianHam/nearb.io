package com.example.asian.nearbio;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            InputStream inputStream = getResources().openRawResource(R.raw.processed_airports);
            CSVFile csvFile = new CSVFile(inputStream);
            List PlacesList = csvFile.read();
            for (int i = 0; i < PlacesList.size(); i++) {
                String[] Place = (String[]) PlacesList.get(i);
                double latitude = Double.parseDouble(Place[4]);
                double longitude = Double.parseDouble(Place[5]);
                AllPoints.add(new LatLng(latitude, longitude));
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            if(resultCode == RESULT_OK) {
                InputStream inputStream = getResources().openRawResource(R.raw.processed_airports);
                CSVFile csvFile = new CSVFile(inputStream);
                List PlacesList = csvFile.read();
                for (int i =0; i<PlacesList.size(); i++){
                    String[] Place = (String[]) PlacesList.get(i);
                    double latitude = Double.parseDouble(Place[4]);
                    double longitude = Double.parseDouble(Place[5]);
                    AllPoints.add(new LatLng (latitude,longitude));
                }
            }
        }
    }
    */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < AllPoints.size(); i++) {
            LatLng pos = AllPoints.get(i);
            mMap.addMarker(new MarkerOptions().position(pos));
        }

        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //LatLng theHeart = new LatLng(39.823230, -84.913315);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.addMarker(new MarkerOptions().position(theHeart).title("The Heart"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(theHeart.getCenter(), 17));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
