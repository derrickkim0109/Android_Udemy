package com.example.retrofitpost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PostReqeustApi {

    // https://httpbin.org/post

    @POST("post")
    Call<PostModel> PostDataIntoServer(@Body PostModel postModel);



    // PUT : http://bin.org/put

    @PUT("put")
    Call<PostModel> UpdateData(@Body PostModel postModel);

    // PATCH : http://bin.org/patch
    @PATCH("patch")
    Call<PostModel> PatchData(@Body PostModel postModel);
}
