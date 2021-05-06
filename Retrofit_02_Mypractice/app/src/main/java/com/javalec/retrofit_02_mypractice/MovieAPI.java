package com.javalec.retrofit_02_mypractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {

    // http://run.mocky.io/v3/c38ef967-0c43-4cbb-b4a0-1f330e2d33b7

//    @GET("v3/c38ef967-0c43-4cbb-b4a0-1f330e2d33b7")
//    Call<List<MovieModel>> getMovives();

//    @GET("v3/b0b08dd4-9086-4e05-bb01-a2344545369f")
//    Call<JSONResponse> getMovives();

    @GET("v3/1fece7f2-d865-44ae-844c-209e33ff543b")
    Call<JSONResponse> getMovives();
}
