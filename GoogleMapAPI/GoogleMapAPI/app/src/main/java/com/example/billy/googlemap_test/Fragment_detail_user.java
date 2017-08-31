package com.example.billy.googlemap_test;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import sqlite.Databasehelper;

/**
 * Created by Vu Khac Hoi on 8/31/2017.
 */

public class Fragment_detail_user extends Fragment {

    SQLiteDatabase database;
    ArrayAdapter arrayAdapter;
    TextView txtYourname,txttime,txtPhoneNumber,txtroom;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.detail_user,container,false);
        addControl(view);
AddEvent();

        return  view;


    }

    public void  convert ( String Name)
    {
        Cursor cursor=database.rawQuery("select * from orderroom where FullName =?" ,new String[]{Name});
        cursor.moveToFirst();

        txtYourname= getActivity().findViewById(R.id.txtNameBook111);
        txttime= getActivity().findViewById(R.id.txttime1);
        txtPhoneNumber= getActivity().findViewById(R.id.txtPhoneNumber1);
        txtroom= getActivity().findViewById(R.id.txtroom1);
        txtYourname.setText(cursor.getString(1));
        txttime.setText(cursor.getString(3));
        txtPhoneNumber.setText(cursor.getString(2));
        txtroom.setText(cursor.getString(4));



    }
    private void AddEvent() {
        Databasehelper myDatabase = new Databasehelper(getActivity());
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();






    }

    private void addControl( View view) {
        txtYourname= view.findViewById(R.id.txtNameBook111);
        txttime= view.findViewById(R.id.txttime1);
        txtPhoneNumber= view.findViewById(R.id.txtPhoneNumber1);
        txtroom= view.findViewById(R.id.txtroom1);

    }
}
