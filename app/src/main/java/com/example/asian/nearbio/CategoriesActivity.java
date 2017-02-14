package com.example.asian.nearbio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.nearby.Nearby;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void nearbyAirport(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyBar(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyCafe(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyEarlham(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyEntertainment(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyHair(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyPark(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyRestaurant(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbyShopping(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }

    public void nearbySupermarket(View view){
        Intent store = new Intent(CategoriesActivity.this, NearbyActivity.class);
        Button b = (Button)view;
        String category = b.getText().toString();
        store.putExtra("category", category);
        setResult(CategoriesActivity.RESULT_OK, store);
        finish();
    }
}
