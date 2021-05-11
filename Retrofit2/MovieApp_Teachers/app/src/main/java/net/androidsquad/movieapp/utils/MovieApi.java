package net.androidsquad.movieapp.utils;

import net.androidsquad.movieapp.models.MovieModel;
import net.androidsquad.movieapp.response.MovieResponse;
import net.androidsquad.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //Search for movies
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );




    // https://api.themoviedb.org/3/movie/popular?api_key=52a18783ed514602a5facb15a0177e61&language=en-US&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page

    );


    // Making Search with ID
    // https://api.themoviedb.org/3/movie/550?api_key=52a18783ed514602a5facb15a0177e61
    // Remember that movie_id= 550 is for Fight Club movie
    // Let's Test Jack Reacher id

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );





}
