package com.example.retrofitpost;

import com.google.gson.annotations.SerializedName;

public class PostModel {


    String title;


    @SerializedName("data")
    String bodyPost;

    json json;

    public PostModel(String title, String bodyPost) {
        this.title = title;
        this.bodyPost = bodyPost;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyPost() {
        return bodyPost;
    }

    public json getJson() {
        return json;
    }
}
