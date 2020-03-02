package com.example.customlistviewsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    ArrayList<SinhVien> sinhVienArrayList;
    SinhVienAdapter svAdapter;
    EditText etName,etAge;
    Button btnAdd,btnEdit;
    private int positionGbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        sinhVienArrayList = new ArrayList<>();
        sinhVienArrayList.add(new SinhVien("Apple",1));
        sinhVienArrayList.add(new SinhVien("Orange",2));
        sinhVienArrayList.add(new SinhVien("LQTam",2));
        svAdapter = new SinhVienAdapter(this,R.layout.list_item,sinhVienArrayList);
        lvSinhVien.setAdapter(svAdapter);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().length() > 0 && etAge.getText().length() > 0){
                    String name = etName.getText().toString();
                    int age = Integer.parseInt(etAge.getText().toString());
                    SinhVien sv = new SinhVien(name,age);
                    sinhVienArrayList.add(sv);
                    svAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Successfully create student.", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etAge.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setPositionGbl(position);
                btnEdit.setEnabled(true);
                btnAdd.setEnabled(false);
                etName.setText(sinhVienArrayList.get(position).getName());
                etAge.setText(""+sinhVienArrayList.get(position).getAge());
            }
        });

        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sinhVienArrayList.remove(position);
                svAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Successfully deleted student.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().length() > 0 && etAge.getText().length() > 0){
                    String name = etName.getText().toString();
                    int age = Integer.parseInt(etAge.getText().toString());
                    SinhVien sv = new SinhVien(name,age);
                    sinhVienArrayList.set(positionGbl,sv);
                    svAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Successfully create student.", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etAge.setText("");
                    btnAdd.setEnabled(true);
                    btnEdit.setEnabled(false);;
                }else{
                    Toast.makeText(MainActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setPositionGbl(int position) {
        this.positionGbl = position;
    }
}
