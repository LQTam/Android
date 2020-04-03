package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    Button btnSignIn,btnLogin,btnCancel;
    EditText etEmail,etPassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =  new Database(this,"qltk.sqlite",null,1);
        db.query("create table if not exists account (" +
                "email varchar(50)," +
                "password varchar(256)," +
                "unique(email)" +
                ")");
        btnSignIn = findViewById(R.id.btnSignIn);
//        db.query("insert into account values (" +
//                "'laquyettam1995@gmail.com'," +
//                "'laquyettam1995@gmail.com'" +
//                ")");
//        db.query("insert into account values (" +
//                "'lqtam1@gmail.com'," +
//                "'lqtam1@gmail.com'" +
//                ")");
//        db.query("insert into account values (" +
//                "'lqtam2@gmail.com'," +
//                "'lqtam2@gmail.com'" +
//                ")");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogin();
            }
        });
    }

    private void dialogLogin() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_dialog);

        etEmail = dialog.findViewById(R.id.etEmail);
        etPassword = dialog.findViewById(R.id.etPassword);
        btnLogin = dialog.findViewById(R.id.btnLogin);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String psw = etPassword.getText().toString();
                Cursor cs = db.getAllData("select * from account where " +
                        "email = '" +email+
                        "' and " +
                        "password = '" +psw+
                        "'");
                int result = cs.getCount();
                if(result > 0){
                    Intent intent = new Intent(MainActivity.this,Dashboard.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Email or Password invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.setText("");
                etPassword.setText("");
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
