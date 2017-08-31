package com.example.billy.googlemap_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import adapter.ImageAdapter;

public class listimage extends AppCompatActivity {
    ArrayList<Object> list;
//    Databasehelper myDatabase = new Databasehelper(this);
//    SQLiteDatabase database;
    GridView grdimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listimage);
//        myDatabase.Khoitai();
//        database=myDatabase.getMyDatabase();
        grdimage=findViewById(R.id.grdimage);
//        Cursor cursor=database.rawQuery("select * from saveImage",null);
//        cursor.moveToFirst();

//
//        while (!cursor.isAfterLast())
//        {
//           Object a=cursor.getBlob(1);
//
//            list.add( a);
//
//            cursor.moveToNext();
//        }
//        cursor.close();
//        Uri selectedImage = data.getData();
//        String[] filePathColumn = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String picturePath = cursor.getString(columnIndex);
//        cursor.close();
//        ImageView imageView = (ImageView) findViewById(R.id.imghinhanh);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
        list.add(1);
        ImageAdapter adapter=new ImageAdapter(this,R.layout.itemimage,list);
        grdimage.setAdapter(adapter);

    }



}
