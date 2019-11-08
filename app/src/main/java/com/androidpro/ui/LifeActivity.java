package com.androidpro.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidpro.R;

public class LifeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        Log.d(this.getClass().getName(), "====onCreate====");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), "====onStart====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getClass().getName(), "====onResume====");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getName(), "====onPause====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getClass().getName(), "====onStop====");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getName(), "====onDestroy====");
    }
}
