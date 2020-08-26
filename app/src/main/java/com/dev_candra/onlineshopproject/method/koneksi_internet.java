package com.dev_candra.onlineshopproject.method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class koneksi_internet {
    private static koneksi_internet internet = new koneksi_internet();
    static Context context;
    ConnectivityManager connectivityManager;
    NetworkInfo wininfo,mobileInfo;
    boolean connected = false;

    public static koneksi_internet getInstance(Context ctx){
        context = ctx.getApplicationContext();
        return internet;
    }

    public boolean isOnline(){
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
                return connected;
            }
        }catch (Exception e){
            Log.d(TAG, "isOnline: connective offline");
        }
        return connected;
    }
}
