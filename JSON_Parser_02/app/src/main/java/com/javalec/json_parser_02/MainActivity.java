package com.javalec.json_parser_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;

    String namey, agey;

    private static String JSON_URL = "https://run.mocky.io/v3/a4a3ae66-c77b-4172-8667-e1238b145195";


    ArrayList<HashMap<String, String>> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendsList = new ArrayList<>();
        listView = findViewById(R.id.listView);

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

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(inputStream);

                    int data = isr.read();
                    while (data != -1){
                        current += (char)data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = jsonObject.getJSONArray("Friends");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject obj1 = jsonArray.getJSONObject(i);

                    namey = obj1.getString("name");
                    agey = obj1.getString("age");

                    // Hashmap
                    HashMap<String, String> friends = new HashMap<>();

                    friends.put("name", namey);
                    friends.put("age", agey);

                    friendsList.add(friends);
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
            // Display the results
            // All source codes are available on UDEMY Course Resources under each lesson
            // Also on Master Coding App found on playstore.

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    friendsList,
                    R.layout.row_layout,
                    new String[] {"name", "age"},
                    new int[]{R.id.textView, R.id.textView2}
            );
            listView.setAdapter(adapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }




    } // END





}// END