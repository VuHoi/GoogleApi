package com.example.billy.googlemap_test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import sqlite.Databasehelper;

public class Camera extends AppCompatActivity {

    public int KEY_CODE=1;
    Button btnchup,btnmo;
    ImageView imghinhanh;

    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();

        Addcontrol();
        AddEvent();


    }

    private void AddEvent() {
        btnchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,KEY_CODE);
            }
        });

        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),listimage.class);
                startActivity(intent);


            }
        });
    }

int index=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==KEY_CODE&& resultCode==RESULT_OK&& data!=null)
        {
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");

            imghinhanh.setImageBitmap(bitmap);

index++;
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imghinhanh.getDrawable();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
//             byte[] hinhanh=byteArrayOutputStream.toByteArray();
//            ContentValues values=new ContentValues();
//            values.put("name","vu khac hoi "+ index);
//            values.put("image",hinhanh);
//            database.insertWithOnConflict("saveImage",null,   values,SQLiteDatabase.CONFLICT_REPLACE );


                MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "fafaf" , "Aefaefe");


        }
    }

    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }
    private void Addcontrol() {
        btnchup=findViewById(R.id.btnchup);
        btnmo =findViewById(R.id.btnmo);

        imghinhanh=findViewById(R.id.imghinhanh);


    }
}
