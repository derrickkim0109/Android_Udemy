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



    // PUT: https://httpbin.org/put
    @PUT("put")
    Call<PostModel> UpdateData(@Body PostModel postModel);

   // Patch https://httpbin.org/patch
    @PATCH("patch")
    Call<PostModel> PatchData(@Body PostModel postModel);


}
