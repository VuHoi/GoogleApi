package com.example.billy.googlemap_test;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Index extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cửa hàng");

        //ready map
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sushi1 = new LatLng(10.780407,106.6916509);
        map=googleMap;


        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI & SAKE PUB 1")
                .snippet("15A Lê Quý Đôn, P.6, Q.3, HCM\n" +
                        "Điện thoại: 028 39 330 167\n")
                .position(sushi1));

        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI & SAKE PUB 2")
                .snippet(" 92 Nam Kì Khởi Nghĩa, P. Bến Nghé, Q.1, HCM\n" +
                        "Điện thoại: 028 38 218 586\n")
                .position(new LatLng(10.7721,106.701)));

        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI & SAKE PUB 3")
                .snippet(" 185 Nguyễn Đức Cảnh, Q.7, HCM\n" +
                        "Điện thoại: 028 54 125 316\n")
                .position(new LatLng(10.7214484,106.7122184)));

        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI PREMIUM ")
                .snippet("123 Bà Huyện Thanh Quan, Q.3, HCM\n" +
                        "Điện thoại:  028 39 318 828\n")
                .position(new LatLng(10.781213,106.682021)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sushi1,14));
    }
}
