package com.example.billy.googlemap_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.adapter_room;
import model.room;

public class listroom extends AppCompatActivity {

    ListView lsvroom;
    ArrayList<room> listroom;
    adapter_room adapter;
    Button btnloadmore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listroom);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Room");
//        setSupportActionBar(toolbar);
        setTitle("Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        lsvroom=findViewById(R.id.lsvroom);
        btnloadmore=findViewById(R.id.btnloadmore);
    }

    private void addEvent() {
        listroom=new ArrayList<room>();
        listroom.add(new room(R.drawable.room));
        listroom.add(new room(R.drawable.room2));

        adapter=new adapter_room(this,R.layout.monan_item,listroom);
       lsvroom.setAdapter(adapter);
        btnloadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listroom.add(new room(R.drawable.room));
                listroom.add(new room(R.drawable.room2));
                listroom.add(new room(R.drawable.room));
                listroom.add(new room(R.drawable.room2));
                listroom.add(new room(R.drawable.room));
                listroom.add(new room(R.drawable.room2));
                adapter.notifyDataSetChanged();
                btnloadmore.setVisibility(View.INVISIBLE);
            }
        });
    }


}
