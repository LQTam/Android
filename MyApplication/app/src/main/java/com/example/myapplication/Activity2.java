package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ListView lvAccount;
    AccountAdapter adapterAccount;
    ArrayList<Account> arrayListAccount;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        db = new Database(this,"qltk.sqlite",null,1);
        db.query("create table if not exists account (" +
                "email varchar(50)," +
                "password varchar(256)," +
                "unique(email)" +
                ")");
//        db.query("insert into account (email,password) values (" +
//                "'laquyettam1995@gmail.com','laquyettam1995@gmail.com'" +
//                ")");
//        db.query("insert into account (email,password) values (" +
//                "'lqtam1@gmail.com','lqtam1@gmail.com'" +
//                ")");
//        db.query("insert into account (email,password) values (" +
//                "'lqtam2@gmail.com','lqtam2@gmail.com'" +
//                ")");
        lvAccount = findViewById(R.id.lvAccount);
        arrayListAccount = new ArrayList<>();

        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account account = arrayListAccount.get(position);
                Intent intent = new Intent(Activity2.this,MainActivity.class);
                intent.putExtra("account",account);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        adapterAccount = new AccountAdapter(arrayListAccount,R.layout.account_row,this);
        lvAccount.setAdapter(adapterAccount);
        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getAllData("select * from account");
        arrayListAccount.clear();
        while(cursor.moveToNext()){
            String email = cursor.getString(0);
            String password = cursor.getString(1);
            arrayListAccount.add(new Account(email,password));
        }
        adapterAccount.notifyDataSetChanged();
    }
}
