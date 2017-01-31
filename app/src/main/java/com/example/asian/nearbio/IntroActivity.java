package com.example.asian.nearbio;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000; //Time to laund the other activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                }
            }, TIME_OUT);
        }
    }

