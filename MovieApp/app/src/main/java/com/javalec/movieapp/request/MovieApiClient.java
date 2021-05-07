package com.javalec.movieapp.request;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.javalec.movieapp.AppExecutors;
import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.response.MovieSearchResponse;
import com.javalec.movieapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    // Live Data
    private MutableLiveData< List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    // making Global Runnable request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;




    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }



    // This method that we are going to call through the classes.
    public void searchMoviesApi(String query, int pageNumber){

        if (retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);


        // Thread 1
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        // Thread 2
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);
    }

    // Retreiving data from Restful API by runnable class.
    // We have 2 types of Queries : the ID & search Queries.
    //  https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
        // Getting the response objects
            try {
                Response response = getMovies(query,pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if (pageNumber == 1){
                        // Sending data to livve data
                        // PostValue : used for background thread
                        // setValue : not for background thread.
                        mMovies.postValue(list);
                    }else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);


                    }
                    //
                }else {
                    String error = response.errorBody().string();
                    Log.v("TAG", "Error" + error);
                    mMovies.postValue(null);

                }
            } catch (IOException e){
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }
            // Search Method / Query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Servicey.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }
        private void cancelRequest(){
             Log.v("TAG", "Cancelling Search Request");
             cancelRequest = true;
         }


        } // END


}// END



