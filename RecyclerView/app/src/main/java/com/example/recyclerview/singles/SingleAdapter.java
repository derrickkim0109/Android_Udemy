package com.example.recyclerview.singles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.SingleViewHolder> {


    // Same as previous videos
    // 2 Classes we need to configure


    // 1- Single Adapter Class
    private Context context;
    private ArrayList<Employee> employees;
    private int checkedPosition = 0; // -1: no default selection  0: 1st item selected


    public SingleAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    public void SetEmployees(ArrayList<Employee> employees){
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }


    // Let's implement the methods
    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee,parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder holder, int position) {
        holder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }









    // 2- View Holder: SingleViewHolder
    class SingleViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.emp_name);
            imageView = itemView.findViewById(R.id.imageviewy);
        }

        void bind(final Employee employee){
            if (checkedPosition == -1){
                imageView.setVisibility(View.GONE);
            }else{
                if (checkedPosition == getAdapterPosition()){
                    imageView.setVisibility(View.VISIBLE);
                }
                else{
                    imageView.setVisibility(View.GONE);
                }
            }

            textView.setText(employee.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }






                }
            });
        }
    }


    public Employee getSelected(){
        if (checkedPosition != -1){
            return employees.get(checkedPosition);
        }
        return null;
    }



}
