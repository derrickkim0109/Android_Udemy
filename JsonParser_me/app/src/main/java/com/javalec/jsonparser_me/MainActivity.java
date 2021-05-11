package com.javalec.jsonparser_me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // ------------- //
    // Properties  //
    // ------------- //

    // Widgets
    TextView nameTextView, salaryTextView;

    // JSON STRING
    String JSON_STRING = "{\"employee\":{\"name\":\"Master coding\",\"salary\": 5000}}";

    String name, salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference for textview.
        nameTextView = findViewById(R.id.name);
        salaryTextView = findViewById(R.id.salary);

        // Getting JSON Objects.
        try {

            // Get JSON Object from json file
            JSONObject obj = new JSONObject(JSON_STRING);

            // FetJSON Object name employee
            JSONObject employee = obj.getJSONObject("employee");

            // Getting Employee name & Salary inside json object(employee)
            name = employee.getString("name");
            salary = employee.getString("name");

            // Setting textviews
            nameTextView.setText("Name : " + name);
            salaryTextView.setText("Salary : " + salary );


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}