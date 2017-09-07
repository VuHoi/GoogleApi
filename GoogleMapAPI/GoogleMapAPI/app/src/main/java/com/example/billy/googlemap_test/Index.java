package com.example.billy.googlemap_test;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.adapterlocation;
import model.Location;
import sqlite.Databasehelper;

public class Index extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    ListView listView;
    ArrayList<Location> arrayList;


    adapterlocation arrayAdapter;

    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cửa hàng");
       // ActivityCompat.requestPermissions(Index.this,new String[]{Manifest.permission.,Manifest.permission.ACCESS_COARSE_LOCATION},1);

        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        //ready map
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);

        Addcontrol();
        AddEvent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sushi1 = new LatLng(10.780407, 106.6916509);
        map = googleMap;


//        map.addMarker(new MarkerOptions()
//                .title("YEN SUSHI & SAKE PUB 1")
//                .snippet("15A Lê Quý Đôn, P.6, Q.3, HCM\n" +
//                        "Điện thoại: 028 39 330 167\n")
//                .position(sushi1));
//
//        map.addMarker(new MarkerOptions()
//                .title("YEN SUSHI & SAKE PUB 2")
//                .snippet(" 92 Nam Kì Khởi Nghĩa, P. Bến Nghé, Q.1, HCM\n" +
//                        "Điện thoại: 028 38 218 586\n")
//                .position(new LatLng(10.7721, 106.701)));
//
//        map.addMarker(new MarkerOptions()
//                .title("YEN SUSHI & SAKE PUB 3")
//                .snippet(" 185 Nguyễn Đức Cảnh, Q.7, HCM\n" +
//                        "Điện thoại: 028 54 125 316\n")
//                .position(new LatLng(10.7214484, 106.7122184)));
//
        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI PREMIUM ")
                .snippet("123 Bà Huyện Thanh Quan, Q.3, HCM\n" +
                        "Điện thoại:  028 39 318 828\n")
                .position(new LatLng(10.781213, 106.682021)));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                sushi1, 15));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "xx", Toast.LENGTH_LONG).show();
            return;
        }

        map.setMyLocationEnabled(true);
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        android.location.Location location = service.getLastKnownLocation(provider);
        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
        Toast.makeText(this, location.getLatitude()+" ", Toast.LENGTH_SHORT).show();
        Circle circle = map.addCircle(new CircleOptions()
                .center(userLocation)
                .radius(1000)
                .fillColor(0x550000FF));

    }
    void AddMakerCustom()
    {
        List<Address> addresses=null;
        Geocoder geocoder =new Geocoder(this);
        try {
            addresses= geocoder.getFromLocationName("Paris",1);
            while (addresses.size()==0) {
                addresses = geocoder.getFromLocationName("Paris", 1);

            }
            if(addresses.size()>0)
            {
                Address address=addresses.get(0);
                LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                map.addMarker(new MarkerOptions().position(latLng).title("Temp"));
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    Button button;
    void Addcontrol() {
        button=findViewById(R.id.button5);

        arrayList = new ArrayList<>();

        arrayAdapter = new adapterlocation(this, R.layout.index_location, arrayList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
    }

    void AddEvent() {
        //  myDatabase.db_delete();
        Cursor cursor = database.rawQuery("select * from storeon", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            arrayList.add(new Location(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            arrayAdapter.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Index.this, Info.class);
                intent.putExtra("object", arrayList.get(i));
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMakerCustom();
            }
        });

    }



}
