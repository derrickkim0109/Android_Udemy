package com.example.recyclerview.normalrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class NormalRecyclerView extends AppCompatActivity {

    RecyclerView normal_recycler;

    private PlanetAdapter adapter;
    private ArrayList<Planet> planetArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_recycler_view);

        normal_recycler = findViewById(R.id.normal_reyc);

        normal_recycler.setLayoutManager(new LinearLayoutManager(this));
        planetArrayList = new ArrayList<>();
        adapter = new PlanetAdapter(this, planetArrayList);
        normal_recycler.setAdapter(adapter);
        normal_recycler.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        createListData();


    }

    private void createListData() {

        // This Method is for adding the data to recyclerView
        // let's make adapter and planet model class

        Planet planet = new Planet("Earth", 150,10,12750);
        planetArrayList.add(planet);

        // Adding some other info
        planet = new Planet("Jupiter", 778, 26, 143000);
        planetArrayList.add(planet);
        planet = new Planet("Mars", 228, 4, 6800);
        planetArrayList.add(planet);
        planet = new Planet("Pluto", 5900, 1, 2320);
        planetArrayList.add(planet);
        planet = new Planet("Venus", 108, 9, 12750);
        planetArrayList.add(planet);
        planet = new Planet("Saturn", 1429, 11, 120000);
        planetArrayList.add(planet);
        planet = new Planet("Mercury", 58, 4, 4900);
        planetArrayList.add(planet);
        planet = new Planet("Neptune", 4500, 12, 50500);
        planetArrayList.add(planet);
        planet = new Planet("Uranus", 2870, 9, 52400);
        planetArrayList.add(planet);

        adapter.notifyDataSetChanged();


    }
}