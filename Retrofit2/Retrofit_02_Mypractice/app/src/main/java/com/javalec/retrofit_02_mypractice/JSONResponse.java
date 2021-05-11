package com.javalec.retrofit_02_mypractice;

import com.google.gson.annotations.SerializedName;

public class JSONResponse {

    // ---------- //
    // Properties //
    // ---------- //



    // ---------- //
    //  Either A  //
    // ---------- //

//    @SerializedName("moviz")
//    private MovieModel[] movieArray;
//
//    public MovieModel[] getMovieArray() {
//        return movieArray;
//    }
//
//    public void setMovieArray(MovieModel[] movieArray) {
//        this.movieArray = movieArray;
//    }



    // ---------- //
    //    Or B   //
    // ---------- //
    private MovieModel[] moviz;

    public MovieModel[] getMoviz() {
        return moviz;
    }

    public void setMoviz(MovieModel[] moviz) {
        this.moviz = moviz;
    }
}
