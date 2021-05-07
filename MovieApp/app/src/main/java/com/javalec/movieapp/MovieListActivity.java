package com.javalec.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.request.Servicey;
import com.javalec.movieapp.response.MovieSearchResponse;
import com.javalec.movieapp.utils.Credentials;
import com.javalec.movieapp.utils.MovieApi;
import com.javalec.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
    // Before we run our app, we need to add the Network Security config.

    // -------------- //
    //  Properties   //
    // -------------- //
    Button btn;

    // ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        // Calling the observers.
        ObserveAnyChange();

        // Testing the Method.
        btn.setOnClickListener(onClickListener);

    }

    // Observing any data change
    private void ObserveAnyChange(){

        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null){
                    for (MovieModel movieModel : movieModels){
                        // Get the data in the log
                        Log.v("TAG" ,"onChanged:" + movieModel.getTitle());
                    }
                }
            }
        });
    }



   View.OnClickListener onClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           // Displaying only the results of page 1
           searchMovieApi("fast", 2);
       }
   };

    // 4 - Calling method in Main Activity.
    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }



    // 1. Movie single id
    // 2. movie search responses

    private void GetRetrofitResponse() {
        MovieApi movieApi = Servicey.getMovieApi();

        // First Call
        // MovieApiClient에서 runnable 시킴.
        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "Jack Reacher",
                        1
                );



        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200){
                    Log.v("TAG", "the response" + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for (MovieModel movie: movies){
                        Log.v("TAG" , "The List" + movie.getRelease_date());
                    }
                }
                else
                {

                    try {
                        Log.v("TAG", "Error" + response.errorBody().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }
    private void GetRetrofitResponseAccordingToID(){

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                343611,
                Credentials.API_KEY
                );

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                if (response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("TAG", "The Response " + movie.getTitle());

                }
                else
                {
                    try {
                        Log.v("TAG", "Error" + response.errorBody().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

}// END

