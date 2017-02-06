package com.frankding.android.ridetj;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by frankding on 1/30/17.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
class User {

    private String Name;
    private String region;
    private String username;
    private String password;
    private String phonenumber;
    private boolean driving;
    private String time;

    public User(String fullName, String region, String password, String username, String phonenumber){
        this.Name = fullName;
        this.password = password;
        this.username = username;
        this.password = password;
        this.region = region;
        this.phonenumber = phonenumber;
    }

    public User(){

    }

    public String getName() {
        return Name;
    }

    public String getRegion() {
        return region;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public boolean isDriving() {
        return driving;
    }

    public void setDriving(boolean driving) {
        this.driving = driving;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
