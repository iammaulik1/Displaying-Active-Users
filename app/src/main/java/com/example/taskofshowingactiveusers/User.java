package com.example.taskofshowingactiveusers;

public class User {

    private String userId;
    private String username;
    private boolean isStatus;

    public User() {
    }

    public User(String userId, String username, boolean isStatus) {
        this.userId = userId;
        this.username = username;
        this.isStatus = isStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

   /* private String userid;
    private String username;
    private boolean isActive;


    public User() {}

    public User(String userid ,String username , boolean isActive ) {

        this.userid = userid;
        this.username = username;
        this.isActive = isActive;

    }


    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

*/
}
