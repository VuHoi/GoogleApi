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
    String cate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdish);

        cate = getIntent().getStringExtra("cate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(cate);
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


        adapter=new adapter_dish(this,R.layout.monan_item,listmonan);
        lsvdish.setAdapter(adapter);

        switch (cate)
        {
            case "Zensai": listmonan.add(new dish(R.drawable.zensai_demo,"Ika shiokara"));
                listmonan.add(new dish(R.drawable.zensai_ikura_hiya_yakko,"Ikura hiya yakko"));
                break;

            case "Chawanmushi": listmonan.add(new dish(R.drawable.chawa_ikura,"Ikura Chawanmushi"));
                listmonan.add(new dish(R.drawable.chawa_demo,"Eihire"));
                break;

        }
        adapter.notifyDataSetChanged();
        btnloadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cate)
                {
                    case "Zensai": listmonan.add(new dish(R.drawable.zensai_ginnan,"Ginnan"));
                        listmonan.add(new dish(R.drawable.zensai_edamame,"Edamame"));
                        break;

                    case "Chawanmushi": listmonan.add(new dish(R.drawable.chawa_demo,"Eihire"));
                        listmonan.add(new dish(R.drawable.chawa_uni,"Uni Chawanmushi"));
                        break;
                }
                adapter.notifyDataSetChanged();
                //btnloadmore.setVisibility(View.INVISIBLE);
            }
        });
    }



}
