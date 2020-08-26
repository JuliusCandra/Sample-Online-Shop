package com.dev_candra.onlineshopproject.method;

import android.content.Context;

import com.dev_candra.onlineshopproject.session.session_manager;
import com.dev_candra.onlineshopproject.model.UserHelperDatabaseWithGoogle;
import com.dev_candra.onlineshopproject.model.UserHelperDatabse;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class databaseFirebase {
    private Context context;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private UserHelperDatabse databseHelper;
    private UserHelperDatabaseWithGoogle googleUser;
    private session_manager manager;

    public databaseFirebase(Context context) {
        this.context = context;
    }

    // Menyimpan data ke realtimedatabse firebase
    public void menyimpanData(String fullname, String username, String email,String nmrTelepon,String password){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("User");
        databseHelper = new UserHelperDatabse(fullname,username,email,nmrTelepon,password);
        reference.child(nmrTelepon).setValue(databseHelper);
    }


}
