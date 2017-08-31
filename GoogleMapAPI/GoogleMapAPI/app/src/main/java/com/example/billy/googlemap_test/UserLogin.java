package com.example.billy.googlemap_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class UserLogin extends AppCompatActivity {

    ListView lsvLoginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        lsvLoginUser=findViewById(R.id.lsvLoginUser);

    }
}
