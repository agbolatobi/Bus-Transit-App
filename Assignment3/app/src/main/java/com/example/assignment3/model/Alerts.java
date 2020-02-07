package com.example.assignment3.model;

//this is the Alerts class for the Alert object
public class Alerts {
    private int id;
    private  String message;
    private String Effect;
    private  String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEffect() {
        return Effect;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
