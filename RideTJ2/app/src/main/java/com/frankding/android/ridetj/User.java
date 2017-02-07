package com.frankding.android.ridetj;

/**
 * Created by frankding on 1/30/17.
 */
class User {

    private String name;
    private String phonenumber;
    private String username;
    private String password;
    private String region;
    private String time;
    private boolean driving;

    public User(String fullName, String phonenumber, String username, String password, String region){
        this.name = fullName;
        this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
        this.region = region;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRegion() {
        return region;
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
