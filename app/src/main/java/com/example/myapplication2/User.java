package com.example.myapplication2;

public class User {

    private String username;
    private String password;
    private String email;
    private double balance;
    private int id;

    public User() {
    }

    public User(int id, String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = 0.0;
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
}
