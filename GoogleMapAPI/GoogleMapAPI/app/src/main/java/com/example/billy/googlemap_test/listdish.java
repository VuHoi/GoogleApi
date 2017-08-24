package com.example.billy.googlemap_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.adapter_dish;
import model.dish;

public class listdish extends AppCompatActivity {

    ListView lsvdish;
    ArrayList<dish> listmonan;
    adapter_dish  adapter;
    Button btnloadmore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdish);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Zensai");
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Zensai");
        addControl();
addEvent();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void addControl() {
        lsvdish=findViewById(R.id.lsvdish);
btnloadmore=findViewById(R.id.btnloadmore);
    }

    private void addEvent() {
        listmonan=new ArrayList<dish>();
        listmonan.add(new dish(R.drawable.sashimi));
        listmonan.add(new dish(R.drawable.sashimi));

         adapter=new adapter_dish(this,R.layout.monan_item,listmonan);
        lsvdish.setAdapter(adapter);
        btnloadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listmonan.add(new dish(R.drawable.sashimi));
                listmonan.add(new dish(R.drawable.gunkan));
                listmonan.add(new dish(R.drawable.sashimi));
                listmonan.add(new dish(R.drawable.gunkan));
                listmonan.add(new dish(R.drawable.sashimi));
                listmonan.add(new dish(R.drawable.gunkan));
                adapter.notifyDataSetChanged();
                btnloadmore.setVisibility(View.INVISIBLE);
            }
        });
    }



}
