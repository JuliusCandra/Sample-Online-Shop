package com.dev_candra.onlineshopproject.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;


public class session_manager {

    // class yang digunakan untuk mengecek apakah user sudah login atau tidak
    SharedPreferences userSessions;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLoggedIn";
    public static final String Fullname = "fullname";
    public static final String Username = "username";
    public static final String Email = "email";
    public static final String NomorTelepone = "telepone";
    public static final String Password = "password";
    public static final String loginUsername = "user";


    @SuppressLint("CommitPrefEdits")
    public session_manager(Context context){
        this.context = context;
        userSessions = context.getSharedPreferences(loginUsername,Context.MODE_PRIVATE);
        editor = userSessions.edit();
    }

    public void createLoginSessions(String fullname,String username,String email,String phoneNumber,String password){
      editor.putBoolean(IS_LOGIN,true);
      editor.putString(Fullname,fullname);
      editor.putString(Username,username);
      editor.putString(Email,email);
      editor.putString(NomorTelepone,phoneNumber);
      editor.putString(Password,password);
      editor.commit();
    }

    public HashMap<String,String> getUserDetailFromSessions(){
        HashMap<String,String>userData = new HashMap<String, String>();
        userData.put(Fullname,userSessions.getString(Fullname,null));
        userData.put(Username,userSessions.getString(Username,null));
        userData.put(Email,userSessions.getString(Email,null));
        userData.put(Password,userSessions.getString(Password,null));
        return userData;
    }

    public boolean checkLogin(){
        if (userSessions.getBoolean(IS_LOGIN,false)){
            return true;
        }else{
            Toast.makeText(context, "Anda tidak login", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
}
