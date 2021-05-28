package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recyclerview.cardviewy.CardViewActivity;
import com.example.recyclerview.multiselection.MultipleSelectionActivity;
import com.example.recyclerview.multiviewtype.MultipleViewTypeActivity;
import com.example.recyclerview.normalrecycler.NormalRecyclerView;
import com.example.recyclerview.singles.SingleSelectionRV;
import com.example.recyclerview.swipe.SwipeSelectionActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NormalRecyclerView.class);
                startActivity(i);
            }
        });

        btn2 = findViewById(R.id.button_cardview);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CardViewActivity.class);
                startActivity(i);
            }
        });


        btn3 = findViewById(R.id.button_single_selection);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SingleSelectionRV.class);
                startActivity(i);
            }
        });

        btn4 = findViewById(R.id.button_multi_selction);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MultipleSelectionActivity.class);
                startActivity(i);
            }
        });

        btn5 = findViewById(R.id.button_swipe);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SwipeSelectionActivity.class);
                startActivity(i);
            }
        });

        btn6 = findViewById(R.id.button_multiview);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MultipleViewTypeActivity.class);
                startActivity(i);
            }
        });

    }
}