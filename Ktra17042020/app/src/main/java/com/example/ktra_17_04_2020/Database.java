package com.example.ktra_17_04_2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void query(String query){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }

    public Cursor getAllData(String query){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery(query,null);
    }

    public Cursor getOne(String query){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery(query,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
