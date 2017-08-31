package com.example.billy.googlemap_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {
 Button btntim,btnLichsu,btnmatkhau,btnvi,btnmesg;
 ImageView imageView;
 TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Addcontrol();
        Intent intent=getIntent();
        String imagetemp=intent.getStringExtra("image");
        try
        {
            Picasso.with(this).load(Uri.parse(imagetemp)).into(imageView);
        }catch (Exception e){};
        textView.setText(intent.getStringExtra("Name"));
    }

    private void Addcontrol() {
        imageView=findViewById(R.id.imageView15);
        textView=findViewById(R.id.textView4);
        btnLichsu=findViewById(R.id.btnLichsu);
        btntim=findViewById(R.id.btntim);
        btnmatkhau=findViewById(R.id.btnmatkhau);
        btnvi=findViewById(R.id.btnvi);
        btnmesg=findViewById(R.id.btnmesg);
      btntim.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(view.getContext(),update_member.class);
              startActivity(intent);
          }
      });
        btnmesg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),changepass.class);

                startActivity(intent);
            }
        });

        btnmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),Index.class);
                startActivity(intent);
            }
        });
    }
}
