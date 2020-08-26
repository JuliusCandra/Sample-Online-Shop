package com.dev_candra.onlineshopproject.model;

import android.net.Uri;


public class UserHelperDatabaseWithGoogle {
    private String fullname;
    private String email;
    private String UserUid;
    private String phoneNumber;
    private String photoUrl;

    public UserHelperDatabaseWithGoogle(String fullname, String email, String userUid, String phoneNumber,String phoneUrl) {
        this.fullname = fullname;
        this.email = email;
        this.UserUid = userUid;
        this.phoneNumber = phoneNumber;
        this.photoUrl = phoneUrl;
    }

    public UserHelperDatabaseWithGoogle() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserUid() {
        return UserUid;
    }

    public void setUserUid(String UserUid) {
        this.UserUid = UserUid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
