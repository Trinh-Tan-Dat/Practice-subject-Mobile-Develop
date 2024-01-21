package com.example.lab6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
    private Powerstatechange powerStateChangeReceiver;

    private void initValue() {
        powerStateChangeReceiver = new Powerstatechange();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initValue();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(
                powerStateChangeReceiver,
                intentFilter
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(powerStateChangeReceiver);
    }
}