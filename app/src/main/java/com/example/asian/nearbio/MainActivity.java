package com.example.asian.nearbio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        }

    public void mapView(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    public void nearbyView(View view) {
        Intent i = new Intent(MainActivity.this, NearbyActivity.class);
        startActivity(i);
    }

    public void infoView(View view) {
        Intent i = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(i);
    }
}
