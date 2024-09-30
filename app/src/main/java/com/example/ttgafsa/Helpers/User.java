package com.example.ttgafsa.Helpers;

public class User {
    private String userId;
    private String phoneNumber;
    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public User() {
        // Default constructor required for Firestore
    }

    public User(String userId, String phoneNumber, String username, String email) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
    }

    // Getters and setters
}
