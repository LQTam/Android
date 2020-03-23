package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnShow,btnAdd,btnDeleteAll;
    SQLite sqLite;
    EditText etName,etMaSV,etDTB;
    TextView tvKQ;
    ListView lvSinhVien;
    ArrayList<SinhVien> arrayListSinhVien;
    SinhVienAdapter adapterSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        arrayListSinhVien = new ArrayList<>();
        sqLite = new SQLite(this,"qlhs.sqlite",null,1);
        sqLite.query("create table if not exists hs (" +
                "name nvarchar(50)," +
                "masv nvarchar(30)," +
                "dtb double," +
                "primary key (masv))");
        etName = findViewById(R.id.etName);
        etMaSV = findViewById(R.id.etMaSV);
        etDTB = findViewById(R.id.etDTB);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String masv = etMaSV.getText().toString();
                double dtb = Double.parseDouble(etDTB.getText().toString());

                    sqLite.query("insert into hs values ('" +name+
                            "','" +masv+
                            "'," +dtb+
                            ")");
                    adapterSinhVien.notifyDataSetChanged();
                    clearInput();
                    loadData();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLite.query("delete from hs");
                loadData();
            }
        });
        adapterSinhVien = new SinhVienAdapter(R.layout.hs_row,this,arrayListSinhVien);
        lvSinhVien.setAdapter(adapterSinhVien);
    }

    private void clearInput() {
        etName.setText("");
        etMaSV.setText("");
        etDTB.setText("");
    }

    private void loadData() {
        Cursor cs = sqLite.getData("select * from hs");
        arrayListSinhVien.clear();
        while(cs.moveToNext()){
            String name = cs.getString(0);
            String masv = cs.getString(1);
            double dtb = cs.getDouble(2);
            arrayListSinhVien.add(new SinhVien(masv,name,dtb));
        }
        adapterSinhVien.notifyDataSetChanged();
    }
}
