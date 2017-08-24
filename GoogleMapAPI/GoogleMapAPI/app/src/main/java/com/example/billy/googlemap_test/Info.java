package com.example.billy.googlemap_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.adapter_catefood;
import model.CateFood;
import model.Location;

public class Info extends AppCompatActivity {

    GridView gridView;
    ArrayList<CateFood> arrayList;
    adapter_catefood adapter_catefood;
    TextView textView1;
    ImageView imageView2;
    ImageView imageView4;
    Button btnRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Addcontrol();
        final Intent intent=getIntent();
        final Location location= (Location) intent.getSerializableExtra("object");
        textView1.setText(location.getLocate());
        setTitle(location.getName().toString());

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", location.getPhone().toString(), null));
                startActivity(in);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent in=new Intent(view.getContext(),danhsachphong.class);
                startActivity(in);
            }
        });
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(view.getContext(),listroom.class);
                startActivity(in);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(view.getContext(),listdish.class);
                startActivity(in);
            }
        });
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

    void Addcontrol()
    {

        gridView=findViewById(R.id.gridView);
        arrayList=new ArrayList<>();
        arrayList.add(new CateFood("SASHIMI",R.drawable.sashimi));
        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));
        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));
        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));
        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));
        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));

        arrayList.add(new CateFood("GUNKAN",R.drawable.gunkan));
        textView1=findViewById(R.id.textView);
        imageView2=findViewById(R.id.imageView2);
        imageView4=findViewById(R.id.imageView4);
        adapter_catefood=new adapter_catefood(this,R.layout.item_cate_food,arrayList);
        gridView.setAdapter(adapter_catefood);
        btnRoom=findViewById(R.id.btnRoom);

    }
}
