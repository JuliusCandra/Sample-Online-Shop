package com.dev_candra.onlineshopproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.Fragment_Awal.fragment_request_email;

public class Email_Activity extends AppCompatActivity {

    private static final String TAG = "";
    private FrameLayout frameLayout;
    public static boolean kembaliKeResetPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_);
        frameLayout = findViewById(R.id.Reset_Melalui_Email);
        setDefaultFragment(new fragment_request_email());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: forgot_passoword");
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (kembaliKeResetPassword) {
                kembaliKeResetPassword = false;
                setNewActivity();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setNewActivity(){
        startActivity(new Intent(Email_Activity.this,Tempt.class));
        finish();;
    }

}
