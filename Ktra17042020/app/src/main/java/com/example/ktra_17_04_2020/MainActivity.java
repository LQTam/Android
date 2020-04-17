package com.example.ktra_17_04_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database db;
    ListView lvWeather;
    Button btnAdd,btnView;
    TextView tvCity,tvTemporary,tvTypeWeather;
    EditText etCity,etTemporary;
    ImageView imgViewWeather;
    ArrayList<Weather> arrayListWeather;
    WeatherAdapter adapterWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this,"weatherDB.sqlite",null,1);
        db.query("create table if not exists weather(" +
                "city varchar(32)," +
                "temporary int (12)," +
                "type varchar(32)" +
                ")");
        db.query("DELETE FROM weather");
        db.query("insert into weather (city,temporary,type) values (" +
                "'London'," +
                "5," +
                "'Rainy'" +
                ")");
        db.query("insert into weather (city,temporary,type) values (" +
                "'New York'," +
                "25," +
                "'Cloudy'" +
                ")");
        db.query("insert into weather (city,temporary,type) values (" +
                "'Sydney'," +
                "32," +
                "'Sunny'" +
                ")");
        lvWeather = findViewById(R.id.lvWeather);
        tvCity = findViewById(R.id.tvCity);
        etCity = findViewById(R.id.etCity);
        etTemporary = findViewById(R.id.etTemporary);

        tvTemporary = findViewById(R.id.tvTemporary);
        tvTypeWeather = findViewById(R.id.tvTypeWeather);
        imgViewWeather = findViewById(R.id.imgViewWeather);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        arrayListWeather = new ArrayList<>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString();
                int temporary = Integer.parseInt(etTemporary.getText().toString());
                if(city.length() > 0 && (temporary > 0)){
                    String typeWeather = temporary > 30 ? "Sunny" : ((temporary >= 20 && temporary <= 30) ? "Cloudy" : "Rainy");
                    db.query("INSERT INTO weather(city,temporary,type) values (" +
                            "'" +city+"'"+
                            "," +temporary+
                            ",'" +typeWeather+"'"+
                            ")");
                    int image = typeWeather == "Sunny" ? R.drawable.sunny : (typeWeather == "Cloudy" ? R.drawable.cloudy : R.drawable.rainy);
                    Weather weather = new Weather(city,temporary,image,typeWeather);
                    arrayListWeather.add(weather);
                    adapterWeather.notifyDataSetChanged();
                    etTemporary.setText("");
                    etCity.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "All field need to be fill.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,showCityWithSmallestTemporary.class);
                Cursor cursor = db.getOne("select min(temporary) from weather");
                while(cursor.moveToNext()){
                    int min = Integer.parseInt(cursor.getString(0));
                    Cursor cursor1 = db.getOne("select w.city from weather as w where w.temporary = "+min);
                    while (cursor1.moveToNext()){
                        intent.putExtra("cityName",cursor1.getString(0));
                    }
                }
                startActivity(intent);

            }
        });
        adapterWeather = new WeatherAdapter(arrayListWeather,R.layout.weather_row,this);
        lvWeather.setAdapter(adapterWeather);
        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getAllData("select * from weather");
        arrayListWeather.clear();
        while(cursor.moveToNext()){
            String city = cursor.getString(0);
            int temporary = cursor.getInt(1);
            String type = cursor.getString(2);
            int image = temporary > 30 ? R.drawable.sunny : ((temporary >= 20 && temporary <= 30) ? R.drawable.cloudy : R.drawable.rainy);
            arrayListWeather.add(new Weather(city,temporary,image,type));
        }
        adapterWeather.notifyDataSetChanged();
    }
}
