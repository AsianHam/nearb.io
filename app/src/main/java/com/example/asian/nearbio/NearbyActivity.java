package com.example.asian.nearbio;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NearbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NearbyActivity.this, CategoriesActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            if(resultCode == RESULT_OK) {
                String category = data.getExtras().getString("category");
                TextView categoryList = new TextView(this);
                categoryList.setText(category);
                categoryList.setTextSize(20);
                categoryList.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                        (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,15, 0, 0);
                params.gravity = Gravity.CENTER;
                categoryList.setLayoutParams(params);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout);
                linearLayout.addView(categoryList);
            }
        }
    }

}
