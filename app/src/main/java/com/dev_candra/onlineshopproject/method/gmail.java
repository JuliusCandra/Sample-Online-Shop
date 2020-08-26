package com.dev_candra.onlineshopproject.method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class gmail {
    private Context context;

    public gmail(Context context){
        this.context = context;
    }

    @SuppressLint("IntentReset")
    public void sendGmail(String receip, String subject, String body){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,receip);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        context.startActivity(Intent.createChooser(intent,"Pilih Gmail"));
    }

}
