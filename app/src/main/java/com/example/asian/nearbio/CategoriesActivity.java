package com.example.asian.nearbio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layoutNearby);
        Intent intent = getIntent();
        List<String> whichType = intent.getStringArrayListExtra("categories");
        if (whichType != null) {
            for (String i : whichType) {
                if (i.equals("\"airport\"")) {
                    Button button = (Button) findViewById(R.id.airports);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"bar\"")) {
                    Button button = (Button) findViewById(R.id.bars);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"cafe\"")) {
                    Button button = (Button) findViewById(R.id.cafes);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"earlham college\"")) {
                    Button button = (Button) findViewById(R.id.earlham);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"entertainment\"")) {
                    Button button = (Button) findViewById(R.id.entertainment);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"hair salon\"")) {
                    Button button = (Button) findViewById(R.id.hair);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"park\"")) {
                    Button button = (Button) findViewById(R.id.parks);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"restaurant\"")) {
                    Button button = (Button) findViewById(R.id.restaurants);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"shopping\"")) {
                    Button button = (Button) findViewById(R.id.shopping);
                    linearLayout.removeView(button);
                }
                if (i.equals("\"supermarket\"")) {
                    Button button = (Button) findViewById(R.id.supermarkets);
                    linearLayout.removeView(button);
                }
            }
        }
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
