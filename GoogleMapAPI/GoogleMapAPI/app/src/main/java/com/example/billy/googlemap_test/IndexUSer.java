package com.example.billy.googlemap_test;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sqlite.Databasehelper;

public class IndexUSer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_user);
        Button button = findViewById(R.id.button2);
        Button btnlogin = findViewById(R.id.button);
        setTitle("MyLife");

        Databasehelper myDatabase = new Databasehelper(this);
        SQLiteDatabase database;
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);





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
