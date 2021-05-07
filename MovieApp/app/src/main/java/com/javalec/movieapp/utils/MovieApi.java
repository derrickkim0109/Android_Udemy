package com.javalec.movieapp.utils;

import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    // -------------- //
    //  Properties   //
    // -------------- //

    //  https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

    // Search for movies
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query, // Search Items
            @Query("page") int page
    );

    // Making Search with ID.
    // https://api.themoviedb.org/3/movie/550?api_key=52a18783ed514602a5facb15a0177e61
    // Remember that movie_id= 550 is for Fight Club movie.
    // Let's Test Jack Reachher id

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );


}
