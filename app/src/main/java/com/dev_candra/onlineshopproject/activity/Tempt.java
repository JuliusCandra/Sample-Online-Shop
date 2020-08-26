package com.dev_candra.onlineshopproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.Fragment_Awal.Login_Up;
import static com.dev_candra.onlineshopproject.activity.Email_Activity.kembaliKeResetPassword;

/**
 *Created by Candra Julius Sinaga.
 * ON 21 June 2020
 * At 10:AM
 */

public class Tempt extends AppCompatActivity {

    private static final String TAG = "";
    private FrameLayout frameLayout;
    public static boolean onRegisterFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempt);
        frameLayout = findViewById(R.id.tempt);
        setDefaultFragment(new Login_Up());
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: login");
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (onRegisterFragment){
                onRegisterFragment = false;
                kembaliKeResetPassword = false;
                setBackFragment(new Login_Up());
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void setBackFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }
}
