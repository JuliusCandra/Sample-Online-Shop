package com.dev_candra.onlineshopproject.model;

public class UserHelperDatabse {

    private String Fullname,Username,email,nmrTelepn,password;

    public UserHelperDatabse() {

    }

    public UserHelperDatabse(String fullname, String username, String email, String nmrTelepn, String password) {
       this. Fullname = fullname;
       this. Username = username;
        this.email = email;
        this.nmrTelepn = nmrTelepn;
        this.password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNmrTelepn() {
        return nmrTelepn;
    }

    public void setNmrTelepn(String nmrTelepn) {
        this.nmrTelepn = nmrTelepn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
