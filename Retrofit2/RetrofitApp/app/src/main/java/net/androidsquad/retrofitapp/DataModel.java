package net.androidsquad.retrofitapp;


public class DataModel {

    // Data Model을 먼저 만든다.

    // This class will be as a template for the data that we are going to parse

    private int userId;

    private int id;

    private String title;

    private boolean completed;



    // Getters
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
