package com.example.billy.googlemap_test;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sqlite.Databasehelper;

/**
 * Created by Vu Khac Hoi on 8/31/2017.
 */

public class fragment_list_user extends Fragment
{


    ArrayList<String> listuser;
    Databasehelper myDatabase = new Databasehelper(getActivity());
    SQLiteDatabase database;
    ArrayAdapter<String> arrayAdapter;
    ListView lsvuser;
    TextView txtYourname,txttime,txtPhoneNumber,txtroom;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
View view =inflater.inflate(R.layout.list_user,container,false);
        lsvuser=view.findViewById(R.id.lsvuser);
        listuser=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,listuser);
        lsvuser.setAdapter(arrayAdapter);

        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        Cursor cursor=database.rawQuery("select * from orderroom",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            listuser.add(cursor.getString(1));
            arrayAdapter.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
addEvent();
        return view ;


    }

   public static String Name;
    private void addEvent() {

        lsvuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                txtYourname= getActivity().findViewById(R.id.txtyourname1);
//                txttime= getActivity().findViewById(R.id.txttime1);
//                txtPhoneNumber= getActivity().findViewById(R.id.txtPhoneNumber1);
//                txtroom= getActivity().findViewById(R.id.txtroom1);
//                Cursor cursor=database.rawQuery("select * from orderroom where FullName= ?",new String[]{listuser.get(i).toString()});
//                cursor.moveToFirst();
                Toast.makeText(getActivity(), "vu khac hoi", Toast.LENGTH_SHORT).show();

//                    txtYourname.setText(cursor.getString(1)+"");
//                    txttime.setText(cursor.getString(3)+"");
//                    txtPhoneNumber.setText(cursor.getString(2)+"");
//                    txtroom.setText(cursor.getString(4)+"");
Fragment_detail_user fragment_detail_user= (Fragment_detail_user) getFragmentManager().findFragmentById(R.id.fragmentdetail);
                   fragment_detail_user.convert(listuser.get(i).toString());

            }
        });
    }


}
