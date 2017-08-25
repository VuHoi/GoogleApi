package com.example.billy.googlemap_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity {
 Button btntim,btnLichsu,btnmatkhau,btnvi,btnmesg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
Addcontrol();
    }

    private void Addcontrol() {

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
