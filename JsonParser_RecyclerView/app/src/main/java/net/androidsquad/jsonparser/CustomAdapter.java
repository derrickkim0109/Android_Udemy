package net.androidsquad.jsonparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    // 1- Creating ArrayLists
    ArrayList<String> personNames;
    ArrayList<String> emailIds;
    ArrayList<String> mobileNumbers;
    Context ctx;


    // 2- Constructor:
    public CustomAdapter(ArrayList<String> personNames, ArrayList<String> emailIds, ArrayList<String> mobileNumbers, Context ctx) {
        this.personNames = personNames;
        this.emailIds = emailIds;
        this.mobileNumbers = mobileNumbers;
        this.ctx = ctx;
    }

    // 3- ViewHolders
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        // Set The data in items
        holder.name.setText(personNames.get(position));
        holder.email.setText(emailIds.get(position));
        holder.mobileNo.setText(mobileNumbers.get(position));

        // Adding onclicklistener event on item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Displaying a msg with person name
                Toast.makeText(ctx, personNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }


    // 4- MyHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder{

        //Widgets
        TextView name, email, mobileNo;

        // Constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            mobileNo = itemView.findViewById(R.id.mobileNO);


        }
    }


}
