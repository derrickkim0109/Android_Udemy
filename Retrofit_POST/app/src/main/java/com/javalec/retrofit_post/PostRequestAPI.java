package com.javalec.retrofit_post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostRequestAPI {

    // http://httpbin.org/ post


    @POST("post")
    Call<PostModel> PostDataIntoServer(@Body PostModel postModel);


}
