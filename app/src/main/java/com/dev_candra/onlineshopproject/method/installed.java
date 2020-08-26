package com.dev_candra.onlineshopproject.method;

import android.app.Activity;
import android.content.pm.PackageManager;

public class installed {
   private Activity activity;

    public installed(Activity activity){
        this.activity = activity;
    }

    public Boolean chekInstalled(String url){
        boolean installed;
        PackageManager packageManager = activity.getPackageManager();
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            installed = false;
        }
        return installed;
    }
}
