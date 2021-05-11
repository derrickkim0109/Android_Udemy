package com.javalec.retrofit_02_mypractice;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    // Model Class


    // ----------- //
    // Properties //
    // ----------- //

    private int id;
//    private String name;
//    private String image;

    // ------- //
    // ADD
    // ------- //
    private String title;
    private float rating;
    private String poster;

    // we need to make object of class details.
    // Different name from json, so we need to serialize it.
    @SerializedName("Details")
    private MoreDetails moreDetails;

    // Constructor


    public MovieModel(int id, String title, float rating, String poster, MoreDetails moreDetails) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.poster = poster;
        this.moreDetails = moreDetails;
    }

    // Getters & Setters
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }



    public float getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }


    public MoreDetails getMoreDetails() {
        return moreDetails;
    }

}
