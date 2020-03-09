package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFood;
    ArrayList<Food> foodArrayList;
    FoodAdapter foodAdapter;
    Button btnEdit,btnBook,btnCancel;
    EditText etName,etPrice;
    CheckBox cbBook;
    int posGbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        btnBook = (Button) findViewById(R.id.btnBook);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        cbBook = (CheckBox) findViewById(R.id.cbBook);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setPosGbl(position);
                etName.setText(foodArrayList.get(position).getName());
                etPrice.setText(foodArrayList.get(position).getPrice()+"");
          }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                Food old = foodArrayList.get(posGbl);
                Food newFood = new Food(name,price,old.getImage(),old.isBook());
                foodArrayList.set(posGbl,newFood);
                foodAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, old.getName() +" Successfully updated.", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etPrice.setText("");
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Food> foodsOrder = new ArrayList<>();
                int total = 0;
                for (int i = 0; i< foodArrayList.size();i++){
                    if(foodArrayList.get(i).isBook()){
                        Food f = foodArrayList.get(i);
                        foodsOrder.add(f);
                        total+=f.getPrice();
                    }
                }
                if(foodsOrder.size() > 0){
                    Intent intent = new Intent(MainActivity.this,OrderDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("foodsOrder",foodsOrder);
                    intent.putExtra("BUNDLE",bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Would you like some tea?", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setMessage("Do you really want to delete "+foodArrayList.get(position).getName()+" food?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(MainActivity.this, foodArrayList.get(position).getName()+" Successfully deleted.", Toast.LENGTH_SHORT).show();
                                foodArrayList.remove(position);
                                foodAdapter.notifyDataSetChanged();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });
        foodAdapter = new FoodAdapter(this,R.layout.food_row,foodArrayList);
        lvFood.setAdapter(foodAdapter);
    }

    private void setPosGbl(int position) {
        posGbl = position;
    }

    public void AnhXa() {
        lvFood = (ListView) findViewById(R.id.lvFood);
        foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("banana",30,R.drawable.banana,false));
        foodArrayList.add(new Food("apple",30,R.drawable.apple,false));
        foodArrayList.add(new Food("strawberry",30,R.drawable.strawberry,false));
    }
}
