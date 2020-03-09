package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {
    TextView tvOrderDetails;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        btnBack = findViewById(R.id.btnBack);
        tvOrderDetails = findViewById(R.id.tvOrderDetails);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Food> foodsOrder = (ArrayList<Food>) args.getSerializable("foodsOrder");

        String show = "Your foods order: \n";
        int total = 0;
        for(int i = 0; i<foodsOrder.size(); i++){
            Food f = foodsOrder.get(i);
            show+=(i+1)+"."+"Name: "+f.getName()+" | Price: "+f.getPrice()+"\n";
            total+= f.getPrice();
        }
        show+="\nTotal order is: "+total;
        tvOrderDetails.setText(show);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
