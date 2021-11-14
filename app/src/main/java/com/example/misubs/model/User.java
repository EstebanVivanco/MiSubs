package com.example.misubs.model;

import org.w3c.dom.Text;

public class User {

    private int id;
    private String username;
    private String mail;
    private String password;

    public User(int id, String username, String mail, String password) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", pass='" + password + '\'' +
                '}';
    }
}
