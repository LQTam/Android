package com.example.ktra_17_04_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class showCityWithSmallestTemporary extends AppCompatActivity {
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_city_with_smallest_temporary);
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("City Name: "+getIntent().getStringExtra("cityName"));
    }
}
