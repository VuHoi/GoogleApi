package com.example.vukhachoi.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import fragmenta.datadelete;
import fragmenta.fragment_a;
import fragmenta.fragment_b;

public class MainActivity extends AppCompatActivity implements datadelete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manage=getFragmentManager();
        FragmentTransaction transaction= manage.beginTransaction();

    }

    public  void onclick(View view)
    {
        FragmentManager manage=getFragmentManager();
        FragmentTransaction transaction= manage.beginTransaction();
        Fragment fragment =null;
        fragment_a  fragmentA= (fragment_a) getFragmentManager().findFragmentById(R.id.fragmentA);
        if(R.id.btn==view.getId())
        {
          fragmentA.settext("vu khac hoi dai ca hahahaha");
        }
        else fragment=new fragment_b();

    }

    @Override
    public void IsDelete(boolean coxoakhong) {
        if(coxoakhong)
        {
            Toast.makeText(this, "may xoa ngay cho tao", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "dung xoa cai nay nghe", Toast.LENGTH_SHORT).show();
        }
    }
}
