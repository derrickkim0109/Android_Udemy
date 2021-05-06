package com.example.retrofitpost;

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

    EditText ed1;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.editText1);

        btn = findViewById(R.id.button);
        txt = findViewById(R.id.textView);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRetrofit();
            }
        });

    }

    private void CallRetrofit() {

        String postBody = ed1.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PostReqeustApi postReqeustApi = retrofit.create(PostReqeustApi.class);

        // Mock Data to test
        PostModel postModel = new PostModel("post1", postBody);

        Call<PostModel> call = postReqeustApi.PostDataIntoServer(postModel);


        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                // Display the results
                txt.setText(response.body().getJson().getData());

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });








    }
}