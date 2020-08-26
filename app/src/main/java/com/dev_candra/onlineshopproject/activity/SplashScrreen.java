package com.dev_candra.onlineshopproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.dev_candra.onlineshopproject.R;

public class SplashScrreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scrreen);

        SystemClock.sleep(3000);
        startActivity(new Intent(SplashScrreen.this,Tempt.class));
        finish();
    }
}
