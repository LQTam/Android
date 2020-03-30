package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnShow,btnAdd,btnEdit,btnDelete;
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
        sqLite = new SQLite(this, "qlhs.sqlite", null, 1);
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
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String masv = etMaSV.getText().toString();
                double dtb = Double.parseDouble(etDTB.getText().toString());

                try {
                    sqLite.query("insert into hs values ('" + name +
                            "','" + masv +
                            "'," + dtb +
                            ")");
                    Toast.makeText(MainActivity.this, "Student created successfully.", Toast.LENGTH_SHORT).show();
                    clearInput();
                    loadData();
                } catch (SQLException e) {
                    Toast.makeText(MainActivity.this, "Masv is the unique field!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLite.query("DELETE FROM hs WHERE masv = 'dtc001'");

                Toast.makeText(MainActivity.this, "Student deleted successfully.", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = etMaSV.getText().toString();
                String name = etName.getText().toString();
                double dtb = Double.parseDouble(etDTB.getText().toString());
                try {
                    sqLite.query("UPDATE hs set name = '" + name + "'" +
                            ",dtb=" + dtb +
                            " where masv = '" + masv + "'");
                    Toast.makeText(MainActivity.this, "Student updated masv " + masv + " successfully.", Toast.LENGTH_SHORT).show();
                    loadData();
                    clearInput();
                } catch (SQLException e) {
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = sqLite.getData("select * from hs where dtb < 4");
                arrayListSinhVien.clear();
                while(cs.moveToNext()){
                    String name = cs.getString(0);
                    String masv = cs.getString(1);
                    double dtb = cs.getDouble(2);
                    arrayListSinhVien.add(new SinhVien(masv,name,dtb));
                }
                adapterSinhVien.notifyDataSetChanged();
            }
        });
        adapterSinhVien = new SinhVienAdapter(R.layout.hs_row, this, arrayListSinhVien);
        lvSinhVien.setAdapter(adapterSinhVien);
        loadData();
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
