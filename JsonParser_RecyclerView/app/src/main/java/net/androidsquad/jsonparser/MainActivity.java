package net.androidsquad.jsonparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1- Widgets
    RecyclerView recyclerView;


    // Let's See first the JSON FILE
    // 2- Copy the JSON File to our assets folder


    // 3- Arraylists for persons, names, email, id & mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        // 4- RecyclerView COnfiguration
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        // 5- Getting JSON

        try {
            // Getting JSON OBJECT from JSON FIle
            JSONObject obj = new JSONObject(loadJSONfromAssets());

            // 7- Fetch JSONArray named USERS
            JSONArray userArray = obj.getJSONArray("users");

            // 8- Implementation of loop for getting users list data
            for (int i=0 ; i<userArray.length() ; i++){

                // 9- Creating a JSONOBJECT for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);

                // 10- Fetching name & email an storing them in arraylist
                personNames.add(userDetail.getString("name"));
                emailIds.add(userDetail.getString("email"));


                // 11- Create an object for getting contact data from JSONObject
                JSONObject contact = userDetail.getJSONObject("contact");

                // 12- Fetching Mobile number & storing ir in arraylist
                mobileNumbers.add(contact.getString("mobile"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 13- Calling the CustomAdapter to send the reference and data to adapter

        CustomAdapter customAdapter = new CustomAdapter( personNames,emailIds,mobileNumbers, MainActivity.this);
        recyclerView.setAdapter(customAdapter);

    }

    // 6- Method for loading JSON from Assets
    private String loadJSONfromAssets() {
        String json = null;

        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;


    }
}