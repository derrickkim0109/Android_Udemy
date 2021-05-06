package com.javalec.retrofit_02_mypractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  MainActivity extends AppCompatActivity {


    // --------- //
    // Properties //
    // --------- //
    RecyclerView recyclerView;
    List<MovieModel> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        movieList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieAPI movieAPI = retrofit.create(MovieAPI.class);

        Call<List<MovieModel>> call = movieAPI.getMovives();

        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {


                List<MovieModel> movies = response.body();

                for (MovieModel movie : movies){
//                    String responseTest = "";
//                    responseTest +=  movie.getId();
//                    textView.append("" + responseTest);

//                int id = movie.getId();
//                String name = movie.getName();
//                String img = movie.getImage();

                movieList.add(movie);
                }

                PutDataIntoRecyclerView(movieList);
            }



            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<MovieModel> movieList) {
        Adaptery adaptery = new Adaptery(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}

