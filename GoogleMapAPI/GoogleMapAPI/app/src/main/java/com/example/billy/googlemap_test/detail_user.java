package com.example.billy.googlemap_test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import adapter.adapter_inforlogin;
import model.inforlogin;
import sqlite.Databasehelper;

public class detail_user extends AppCompatActivity {
    TabHost tabHost;
    ListView lsv;
    adapter_inforlogin adapter;
    ArrayList<inforlogin> arrayList;
    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        tabHost= (TabHost) findViewById(R.id.tabhost);
        AddTabhost();

        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        lsv=findViewById(R.id.lsvLoginUser);
        arrayList=new ArrayList<>();
        adapter=new adapter_inforlogin(this,R.layout.item_login,arrayList);
        lsv.setAdapter(adapter);


        Cursor cursor=database.rawQuery("select * from user",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {

            String name=cursor.getString(1);
            byte[] hinhanh = cursor.getBlob(2);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);

            inforlogin info=new inforlogin(name,bitmap1);
           // Toast.makeText(this, cursor.getString(0), Toast.LENGTH_LONG).show();

            arrayList.add(info);
            adapter.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();


    }

    private void AddTabhost()
    {

        tabHost.setup();
        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setIndicator("Infor Book");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setIndicator("Infor User");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

//        tabHost.setCurrentTab(0);
    }
}
