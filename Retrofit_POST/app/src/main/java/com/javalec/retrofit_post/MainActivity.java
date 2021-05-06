package com.javalec.retrofit_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    // --------- //
    // Propeties //
    // --------- //
    EditText edit1;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editTextName);
        textView = findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRetrofit();
            }
        });


    }

    private void CallRetrofit() {

        String postBody = edit1.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostRequestAPI postRequestAPI = retrofit.create(PostRequestAPI.class);
        
        // Mock Data to test
        PostModel postModel = new PostModel("post1", postBody);

        Call<PostModel> call = postRequestAPI.PostDataIntoServer(postModel);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                // Display the results.
                textView.setText(response.body().getJson().getData());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });
    }



}