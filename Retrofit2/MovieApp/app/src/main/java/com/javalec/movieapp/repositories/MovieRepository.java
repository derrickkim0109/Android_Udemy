package com.javalec.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.request.MovieApiClient;

import java.util.List;

public class  MovieRepository {

    // ----------- //
    // Properties  //
    // ----------- //
    // This class is acting as repositories
    //  Live Data
    // private MutableLiveData<List<MovieModel>> mMovies;
    // request -> MovieApiClient로 아동.
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance(){

        if (instance  == null){
            instance = new MovieRepository();
        }
        return instance;
    }

//    private MovieRepository(){
//         mMovies = new MutableLiveData<>();
//    }
//    public LiveData<List<MovieModel>> getMovies(){
//        return mMovies;
//    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
            return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getMoviesPopular(){
        return movieApiClient.getMoviesPopular();
    }

    // 2 - Calling the method in repository. 02
    public void searchMovieApi(String query, int pageNumber){
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchMoviePopular( int pageNumber){
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPopular(pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery, mPageNumber+1);
    }

}// END
