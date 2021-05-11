package com.javalec.movieapp.viewmodels;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    // This class is used for VIEWMODEL.

//    // Live Data
//    private MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();
    // Live Data ->  Repository로 옮김
    // -------------------------------------------

    private MovieRepository movieRepository;

    // Constructor
    public MovieListViewModel(){
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
//        return mMovies;
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getMoviesPopular(){
//        return mMovies;
        return movieRepository.getMoviesPopular();
    }

    // 3 - Calling method in view-model 03
    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }
    public void searchMoviePopular( int pageNumber){
        movieRepository.searchMoviePopular(pageNumber);
    }

    // Page number
    public void searchNextPage(){
        movieRepository.searchNextPage();
    }


}
