package com.example.recyclerview.cardviewy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.normalrecycler.Planet;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.PlanetHolder2>{

    // CardAdapter Class
    private Context context2;
    private ArrayList<PlanetsCards> planets;


    // Constructor

    public CardAdapter(Context context2, ArrayList<PlanetsCards> planets) {
        this.context2 = context2;
        this.planets = planets;
    }

    @NonNull
    @Override
    public PlanetHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context2).inflate(R.layout.item_card,parent,false);
        return new PlanetHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetHolder2 holder, int position) {

        PlanetsCards planet = planets.get(position);
        holder.SetDetails(planet);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }


    // View Holder: PlanetHolder2

    class PlanetHolder2 extends RecyclerView.ViewHolder{
        private TextView txtNameCard, txtDistanceCard, txtGravityCard, txtDiameterCard;



        PlanetHolder2(View itemView){
            super(itemView);
            txtNameCard = itemView.findViewById(R.id.txtNameCard);
            txtDistanceCard = itemView.findViewById(R.id.txtDistanceCard);
            txtGravityCard = itemView.findViewById(R.id.txtGravityCard);
            txtDiameterCard = itemView.findViewById(R.id.txtDiameterCard);

        }


        void SetDetails(PlanetsCards planet){
            txtNameCard.setText(planet.getPlanetName2());
            txtDistanceCard.setText(String.format(Locale.US,
                    "Distance from sun : %d Million KM", planet.getDistanceFromSun2()));
            txtGravityCard.setText(String.format(Locale.US,
                    "Surface Gravity : %d N/Kg", planet.getGravity2()));
            txtDiameterCard.setText(String.format(Locale.US,
                    "Diamter : %d KM", planet.getDiameter2()));
        }

    }



}
