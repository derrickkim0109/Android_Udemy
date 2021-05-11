package com.javalec.movieapp.request;

import com.javalec.movieapp.utils.Credentials;
import com.javalec.movieapp.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

                        // --------------------   //
                        //  Singleton Pattern     //
                        // --------------------  //
public class Servicey {

                    // -------------- //
                    //  Properties   //
                    // -------------- //

    private static Retrofit.Builder retrofitBulider =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBulider.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }


}
