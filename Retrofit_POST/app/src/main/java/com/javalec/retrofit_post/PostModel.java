package com.javalec.retrofit_post;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    // ----------- //
    // Properties //
    // ----------- //
    String title;

    @SerializedName("data")
    String bodyPost;

    JSON json;

    // ----------- //
    // Constructor //
    // ----------- //

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

    public JSON getJson() {
        return json;
    }
}

