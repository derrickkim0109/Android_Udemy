package com.example.recyclerview.multiviewtype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class MultipleViewTypeActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view_type);


        recyclerView =findViewById(R.id.multiple_view_rv);


        InsertDatatoRV();


    }

    private void InsertDatatoRV() {
        ArrayList<MultiEmployee> employees = new ArrayList<>();


        // Adding Data to it
        MultiEmployee employee1 = new MultiEmployee();

        // A phone item
        employee1.setName("Mohammad");
        employee1.setAddress("New York");
        employee1.setPhone("+012398234234");
        employees.add(employee1);


        // An email item
        MultiEmployee employee2 = new MultiEmployee();
        employee2.setName("Ali");
        employee2.setAddress("London");
        employee2.setEmail("ali@gmail.com");
        employees.add(employee2);

        MultiEmployee employee3 = new MultiEmployee();
        employee3.setName("Jack");
        employee3.setAddress("Philadelphia");
        employee3.setEmail("jack@gmail.com");
        employees.add(employee3);

        MultiEmployee employee4 = new MultiEmployee();
        employee4.setName("Ryan");
        employee4.setAddress("Canada");
        employee4.setPhone("+123423453456");
        employees.add(employee4);


        // set adapter
        MultipleTypeAdapter adapter = new MultipleTypeAdapter(this, employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


}