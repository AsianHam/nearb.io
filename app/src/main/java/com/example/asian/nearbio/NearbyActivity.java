package com.example.asian.nearbio;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asian.nearbio.CSVFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Text;

import org.w3c.dom.Text;

import static android.app.Activity.RESULT_OK;

public class NearbyActivity extends AppCompatActivity {

    List<String> whichType = new ArrayList();
    List<String> allCategories = Arrays.asList("\"airport\"", "\"bar\"", "\"cafe\"", "\"earlham college\"", "\"entertainment\"",
            "\"hair salon\"", "\"park\"", "\"restaurant\"", "\"shopping\"", "\"supermarket\"");

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
                if (category != null) {
                    int lastIndex = category.length() - 1;
                    String sendStr;
                    if (category.endsWith("s")) {
                        sendStr = category.substring(0, lastIndex);
                    } else {
                        sendStr = category;
                    }
                    sendStr = '"' + sendStr.toLowerCase() + '"';
                    whichType.add(sendStr);
                }
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

    public void mapView(View view) {
        EditText mEdit = (EditText) findViewById(R.id.radiusEdit);
        Intent listStore = new Intent(NearbyActivity.this, MapsActivity.class);

        int distance;
        String mText = (mEdit.getText().toString()).trim();
        try {
            distance = Integer.valueOf(mText);
        }catch (Exception e){
            distance = 10;
        }
        listStore.putExtra("distance",distance);
        if (whichType.isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "You didn't select any categories. Press the + button on bottom right to add categories.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            listStore.putStringArrayListExtra("categories", (ArrayList<String>) whichType);
            startActivity(listStore);
            finish();
        }

/*        try {

        }catch(Exception e){
//            listStore.putStringArrayListExtra("categories", (ArrayList<String>) allCategories);

        }*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}