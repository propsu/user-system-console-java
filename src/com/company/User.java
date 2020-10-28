package com.company;

public class User {
    private final String username;
    private String password;

    public User(String line){
        String[] user = line.split(":");
        this.username = user[0];
        this.password = user[1];
    }

    public User(String login, String password) {
        this.username = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + ":" + password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
}
