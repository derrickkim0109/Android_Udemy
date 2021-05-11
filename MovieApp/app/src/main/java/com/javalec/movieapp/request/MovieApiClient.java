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

    // Live Data for search.
    private MutableLiveData< List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    // making Global Runnable request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    // ----------------------------------------------------
    // Live Data for popular movies.
    private MutableLiveData<List<MovieModel>> mMoviesPopular;

    // making popular runnable
    private RetrieveMoviesRunnablePopular retrieveMoviesRunnablePopular;
    //------------------------------------------------------

    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
        mMoviesPopular = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }
    public LiveData<List<MovieModel>> getMoviesPopular(){
        return mMoviesPopular;
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


    public void searchMoviesPopular(int pageNumber){

        if (retrieveMoviesRunnablePopular != null){
            retrieveMoviesRunnablePopular = null;
        }

        retrieveMoviesRunnablePopular = new RetrieveMoviesRunnablePopular(pageNumber);


        // Thread 1
        final Future myPopularHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnablePopular);

        // Thread 2
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myPopularHandler.cancel(true);
            }
        },1000, TimeUnit.MILLISECONDS);
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

    private class RetrieveMoviesRunnablePopular implements Runnable {

//        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePopular(int pageNumber) {
//            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        @Override
        public void run() {
            // Getting the response objects
            try {
                Response responsePopular = getMoviesPopular(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (responsePopular.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)responsePopular.body()).getMovies());
                    if (pageNumber == 1){
                        // Sending data to livve data
                        // PostValue : used for background thread
                        // setValue : not for background thread.
                        mMoviesPopular.postValue(list);
                    }else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMoviesPopular.postValue(currentMovies);


                    }
                    //
                }else {
                    String error = responsePopular.errorBody().string();
                    Log.v("TAG", "Error" + error);
                    mMoviesPopular.postValue(null);

                }
            } catch (IOException e){
                e.printStackTrace();
                mMoviesPopular .postValue(null);
            }


        }
        // Search Method / Query
        private Call<MovieSearchResponse> getMoviesPopular( int pageNumber){
            return Servicey.getMovieApi().getPopular(
                    Credentials.API_KEY,
                    pageNumber
            );
        }
        private void cancelRequest(){
            Log.v("TAG", "Cancelling Search Request");
            cancelRequest = true;
        }


    } // END


}// END



