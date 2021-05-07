package com.javalec.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    // This class is acting as repositories

    private static MovieRepository instance;
//    // Live Data
//    private MutableLiveData<List<MovieModel>> mMovies;
    // request -> MovieApiClient로 아동.

    private MovieApiClient movieApiClient;

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

    // 2 - Calling the method in repository. 02
    public void searchMovieApi(String query, int pageNumber){
        movieApiClient.searchMoviesApi(query, pageNumber);
    }


}// END
