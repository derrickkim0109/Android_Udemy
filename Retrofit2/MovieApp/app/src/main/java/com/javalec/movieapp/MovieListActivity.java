package com.javalec.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javalec.movieapp.adapters.MovieRecyclerView;
import com.javalec.movieapp.adapters.OnMovieListener;
import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.request.Servicey;
import com.javalec.movieapp.response.MovieSearchResponse;
import com.javalec.movieapp.utils.Credentials;
import com.javalec.movieapp.utils.MovieApi;
import com.javalec.movieapp.viewmodels.MovieListViewModel;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {
    // Before we run our app, we need to add the Network Security config.

    // -------------- //
    //  Properties   //
    // -------------- //

    // RecyclerView
    private RecyclerView recyclerView;
    // Adapter
    private MovieRecyclerView movieRecyclerAdapter;

    // ViewModel
    private MovieListViewModel movieListViewModel;


    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);// ActionBar 타이틀(앱이름) 감추기

        // SearchView
        SetupSearchhView();

        recyclerView = findViewById(R.id.recyclerView);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();

        // Getting popular movies.
        movieListViewModel.searchMoviePopular(1);


//        searchMovieApi("fast", 1)
        // Testing the Method.
//        btn.setOnClickListener(onClickListener);



    }

    private void ObservePopularMovies() {
        movieListViewModel.getMoviesPopular().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null){
                    for (MovieModel movieModel : movieModels){
                        // Get the data in the log
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
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

                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
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
    // 4 - Calling method in Main Activity.
//    private void searchMovieApi(String query, int pageNumber){
//        movieListViewModel.searchMovieApi(query,pageNumber);
//    }

    // 5- Initializing RecyclerView & Adding data to it
    private void ConfigureRecyclerView(){
        // Live Data can't be passed via the constructor.
        movieRecyclerAdapter = new MovieRecyclerView( this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // RecyclerView Pagination
        // Loading next page of api response.
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    // Here we need to display the next search results on the next page of api.
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this,"The Position " + position , Toast.LENGTH_SHORT).show();

        // We don't need position of the movie in recyclerview
        // We need THE ID OF THE MOVIE IN ORDER TO GET ALL IT'S DETAIL

        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }

    // Get data from searchvview & query the api to get thhe results.
    private void SetupSearchhView() {
        final androidx.appcompat.widget.SearchView searchView = findViewById(R.id.search_view);


        // Detect Search
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do what you want when search view expended
                isPopular = false;
                Log.v("Tagy", "ispop: " +isPopular);

            }
        });
        searchView.setOnCloseListener(new androidx.appcompat.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //do what you want  searchview is not expanded
                return false;
            }
        });




        // Make search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        // The search string getted from searchview
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
//   View.OnClickListener onClickListener = new View.OnClickListener() {
//       @Override
//       public void onClick(View v) {
//
//           // Displaying only the results of page 1
//           searchMovieApi("fast", 2);
//       }
//   };




//    private void GetRetrofitResponseAccordingToID(){
//
//        MovieApi movieApi = Servicey.getMovieApi();
//        Call<MovieModel> responseCall = movieApi.getMovie(
//                343611,
//                Credentials.API_KEY
//                );
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//
//                if (response.code() == 200){
//                    MovieModel movie = response.body();
//                    Log.v("TAG", "The Response " + movie.getTitle());
//
//                }
//                else
//                {
//                    try {
//                        Log.v("TAG", "Error" + response.errorBody().toString());
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }

}// END

