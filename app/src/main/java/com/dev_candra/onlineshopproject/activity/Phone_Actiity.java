package com.dev_candra.onlineshopproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.Fragment_Awal.fragment_reset_phone;

public class Phone_Actiity extends AppCompatActivity {

    private FrameLayout actvityPhone;
    public static boolean phone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__actiity);

        actvityPhone = findViewById(R.id.activity_phone);
        setNewFragment(new fragment_reset_phone());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(Phone_Actiity.this,Tempt.class));
            finish();
            phone = false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setNewFragment(Fragment fragment){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
            fragmentTransaction.replace(actvityPhone.getId(),fragment);
            fragmentTransaction.commit();
    }
}
