package com.javalec.movieapp_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // 1 - Json Link on Internet
//    https://run.mocky.io/v3/7978a57a-4776-4a30-bb4e-7a1a96a58c72

    private static String JSON_URL = "https://run.mocky.io/v3/7978a57a-4776-4a30-bb4e-7a1a96a58c72";

    List<MovieModelClass> movieList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);


        // AsyncTask
        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject root = new JSONObject(s);
                JSONArray jsonArray = root.getJSONArray("moviz");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(object.getString("id"));
                    model.setName(object.getString("name"));
                    model.setImg(object.getString("image"));

                    movieList.add(model);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerVView(movieList);
        }
    }
    private void PutDataIntoRecyclerVView(List<MovieModelClass> movieList){

        Adaptery adaptery = new Adaptery(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adaptery);
    }

}