package com.dev_candra.onlineshopproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.dev_candra.onlineshopproject.R;

public class Activity_Order_Detail extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__order__detail);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Aksi();

    }

    private void Aksi(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Order Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItem = item.getItemId();
        if (menuItem == android.R.id.home){
            finish();
        }

        return true;
    }
}
