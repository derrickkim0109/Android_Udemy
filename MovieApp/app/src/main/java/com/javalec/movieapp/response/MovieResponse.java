package com.javalec.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.javalec.movieapp.models.MovieModel;

// This class is for single movie request.
public class MovieResponse {

    // 1. Finding the Movie Object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    // toString() : 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
    // The object that has some information or values converts to the String for returning.
    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
