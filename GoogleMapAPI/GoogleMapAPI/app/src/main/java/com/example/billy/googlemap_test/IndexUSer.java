package com.example.billy.googlemap_test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import sqlite.Databasehelper;

public class IndexUSer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_user);
        Button button = findViewById(R.id.button2);
        Button btnlogin = findViewById(R.id.button);
        Databasehelper myDatabase = new Databasehelper(this);
        SQLiteDatabase database;
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        //myDatabase.db_delete();
        Cursor cursor=database.rawQuery("select * from USER",null);
        cursor.moveToLast();


        ImageView imageView=findViewById(R.id.imageView);
        try {
            byte[] hinhanh = cursor.getBlob(2);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            imageView.setImageBitmap(bitmap1);
            Toast.makeText(this, cursor.getString(0), Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {

        }
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
