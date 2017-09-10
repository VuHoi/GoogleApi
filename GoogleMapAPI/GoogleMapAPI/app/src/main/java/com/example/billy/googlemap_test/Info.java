package com.example.billy.googlemap_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.adapter_catefood;
import model.CateFood;

public class Info extends AppCompatActivity {

    GridView gridView;
    ArrayList<CateFood> arrayList;
    adapter_catefood adapter_catefood;
    TextView textView1;
    ImageView imageView2;
    ImageView imageView4;
    Button btnRoom;
    android.support.v7.widget.Toolbar toolbar;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Addcontrol();
        final Intent intent=getIntent();
        final model.Location location= (model.Location) intent.getSerializableExtra("object");

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
        ImageView imageView5=findViewById(R.id.imageView5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(view.getContext(),MakeaReservation.class);
                startActivity(in);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(view.getContext(),listdish.class);
                in.putExtra("cate",arrayList.get(i).getName().toString());
                startActivity(in);
                //Toast.makeText(Info.this,i+"",Toast.LENGTH_LONG).show();
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

    private BottomSheetBehavior mBottomSheetBehavior;
    void Addcontrol()
    {

        View bottomSheet = findViewById( R.id.sheet2);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);





        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetBehavior.setPeekHeight(0);

        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                200f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(2000);  // animation duration
        animation.setRepeatCount(0);  // animation repeat count
        animation.setRepeatMode(0);   // repeat animation (left to right, right to left )
        animation.setFillAfter(true);

        bottomSheet.startAnimation(animation);  // start animation


        toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.direct_select);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.yes:ReturnDirect(); break;
                    case R.id.no:mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); break;
                }
                return true;
            }
        });

        gridView=findViewById(R.id.gridView);
        arrayList=new ArrayList<>();
        arrayList.add(new CateFood("Zensai",R.drawable.res_icon));
        arrayList.add(new CateFood("Chawanmushi",R.drawable.res_icon));
//
        textView1=findViewById(R.id.textView);
        imageView2=findViewById(R.id.imageView2);
        imageView4=findViewById(R.id.imageView4);
        adapter_catefood=new adapter_catefood(this,R.layout.item_cate_food,arrayList);
        gridView.setAdapter(adapter_catefood);
       btnRoom=findViewById(R.id.btnRoom);

    }

    private void ReturnDirect() {
        setResult(RESULT_OK);
        finish();
    }
}
