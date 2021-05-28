package com.example.recyclerview.cardviewy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.R;
import com.example.recyclerview.normalrecycler.Planet;

import java.util.ArrayList;

public class CardViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<PlanetsCards> planetsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);


        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.recyclerViewCardy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        planetsArrayList = new ArrayList<>();

        adapter = new CardAdapter(this, planetsArrayList);
        recyclerView.setAdapter(adapter);

        CreateDataForCards();


    }

    private void CreateDataForCards() {
        // This Method is for adding the data to recyclerView
        // let's make adapter and planet model class

        PlanetsCards planet = new PlanetsCards("Earth", 150,10,12750);
        planetsArrayList.add(planet);

        // Adding some other info
        planet = new PlanetsCards("Jupiter", 778, 26, 143000);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Mars", 228, 4, 6800);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Pluto", 5900, 1, 2320);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Venus", 108, 9, 12750);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Saturn", 1429, 11, 120000);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Mercury", 58, 4, 4900);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Neptune", 4500, 12, 50500);
        planetsArrayList.add(planet);
        planet = new PlanetsCards("Uranus", 2870, 9, 52400);
        planetsArrayList.add(planet);

        adapter.notifyDataSetChanged();



    }
}