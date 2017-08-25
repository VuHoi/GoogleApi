package com.example.billy.googlemap_test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sqlite.Databasehelper;

public class IndexUSer extends AppCompatActivity {
    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_user);
        Button button = findViewById(R.id.button2);
        Button btnlogin = findViewById(R.id.button);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        Cursor cursor=database.rawQuery("select * from orderroom",null);
        cursor.moveToFirst();
        Toast.makeText(this,cursor.getString(1),Toast.LENGTH_LONG).show();

        //cursor.close();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IndexUSer.this,register.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IndexUSer.this,login.class);
                startActivity(intent);
            }
        });
    }


}
