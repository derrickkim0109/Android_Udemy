package com.javalec.jsonparser_me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser02 extends AppCompatActivity {

    // Widgets
    ListView listView;

    // JSON String :
    // This is the format of JSON
    // Check the description below for Source Code
    String json_string = "{\n" +
            " \"title\":\"JSONParserTutorail\",\n" +
            " \"array\":[\n" +
            "   {\n " +
            " \"company\" : \"Google\"\n" +
            "},\n" +
            "{\n" +
            "   \"company\":\"Facebook\"\n" +
            "},\n" +
            "{\n" +
            "\"company\":\"LinkedIn\"\n" +
            "},\n" +
            "{\n" +
            "   \"company\":\"Microsoft\"\n" +
            "},\n" +
            "{\n" +
            "   \"company\":\"Apple\"\n" +
            "}\n" +
            "],\n" +
            "\"nested\":{\n" +
            "\"flag\":true,\n" +
            "\"random_number\":1\n" +
            "}\n" +
            "}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n_parser02);

        // Getting Json Objects
        try {

            listView = findViewById(R.id.listView);

            // 1 - Strong Items in a list
            List<String> items = new ArrayList<>();

            // 2 - Creating a JSON Object
            JSONObject root = new JSONObject(json_string);

            // 3 - Getting data from array
            JSONArray array = root.getJSONArray("array");

            // 4 - Setting the title
            this.setTitle(root.getString("title"));

            // 5 - Loop to get all company details / objects

            for (int i = 0; i < array.length(); i ++ ){

                JSONObject object = array.getJSONObject(i);
                items.add(object.getString("company"));
            }

            // 6 - Creating adapter for the listview items
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, items);

            if (listView != null){

                listView.setAdapter(adapter);
            }

            // Getting Nested Objects from the root
            JSONObject nested = root.getJSONObject("nested");
            Log.v("TAG", "flag value" + nested.getBoolean("flag"));

        }catch (JSONException e ){
            e.printStackTrace();

        }
    }




}