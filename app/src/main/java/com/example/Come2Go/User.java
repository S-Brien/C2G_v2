package com.example.Come2Go;

public class User {

    private String username;
    private String password;
    private String email;
    private String biography;
    private double balance;
    private byte[] profilePicture;
    private int id;

    public User() {
    }

    public User(int id, String username, String email, String password, Double balance, String biography, byte[] profilePicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.biography = biography;
        this.profilePicture = profilePicture;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
