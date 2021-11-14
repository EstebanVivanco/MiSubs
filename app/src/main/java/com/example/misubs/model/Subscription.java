package com.example.misubs.model;

public class Subscription {

    private int id;
    private String name;
    private int value;
    private int user_id;


    public Subscription(int id, String name, int value, int user_id) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", user_id=" + user_id +
                '}';
    }
}
