package com.example.root.androidworkshop;

/**
 * Created by root on 9/6/17.
 */

public class ToDo {

    private String name;
    private Date date;

    public ToDo(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
