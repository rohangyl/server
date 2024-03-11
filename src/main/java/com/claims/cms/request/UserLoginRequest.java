package com.claims.cms.request;

public class UserLoginRequest {

    private String username;
    private String password;

    // Constructors, getters, and setters

    public UserLoginRequest() {
        // Default constructor
    }

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
