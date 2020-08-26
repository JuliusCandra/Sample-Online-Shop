package com.dev_candra.onlineshopproject.method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.dev_candra.onlineshopproject.R;

public class proses_dialog {
    private Activity activity;
    private AlertDialog alertDialog;

    public proses_dialog(Activity activity){
        this.activity = activity;
    }

    @SuppressLint("InflateParams")
    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.layout_proses_dialog,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

   public void endDialog(){
        alertDialog.dismiss();
    }
}
