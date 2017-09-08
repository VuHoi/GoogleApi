package com.example.billy.googlemap_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adapter.adapterlocation;
import model.Location;
import sqlite.Databasehelper;

public  class Index extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    ListView listView;
    ArrayList<Location> arrayList;
    TextView editText7;
    double latitude, longitude;
    adapterlocation arrayAdapter;
    public  static int PROXIMITY_RADIUS = 1000;
    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;
    public static LatLng userLocation;


    ArrayList<String> distance;
    ArrayAdapter<String> arrayAdapterdis;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cửa hàng");
        ActivityCompat.requestPermissions(Index.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
        // ActivityCompat.requestPermissions(Index.this,new String[]{Manifest.permission.,Manifest.permission.ACCESS_COARSE_LOCATION},1);
        editText7 = findViewById(R.id.edt);
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            map.setMyLocationEnabled(true);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }  map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        android.location.Location location = service.getLastKnownLocation(provider);
        userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        latitude = location.getLatitude();
//
        longitude = location.getLongitude();
////
//       lastlocation = location;



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
        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener(){
            @Override
            public boolean onMyLocationButtonClick()
            {
                //Toast.makeText(this,"X",Toast.LENGTH_LONG).show();
               // onMapReady(map);
                map.clear();
                onMapReady(map);
                AddMakerCustom(null);
                restaurent(null);
                return false;
            }
        });

    }

    //Add restaurant
    public void AddMakerCustom(View view) {
        Geocoder geoCoder = new Geocoder(Index.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geoCoder.getFromLocationName(editText7.getText() + "", 5);
            Toast.makeText(Index.this, addresses.size() + "", Toast.LENGTH_SHORT).show();
            if (addresses.size() > 0) {
                Double lat = (double) (addresses.get(0).getLatitude());

                Toast.makeText(Index.this, addresses.get(0).getLatitude() + "", Toast.LENGTH_SHORT).show();
                Double lon = (double) (addresses.get(0).getLongitude());

                Log.d("lat-long", "" + lat + "......." + lon);
                final LatLng user = new LatLng(lat, lon);
        /*used marker for show the location */
                Marker hamburg = map.addMarker(new MarkerOptions()
                        .position(user)
                        .title(editText7.getText().toString())
                );
                // Move the camera instantly to hamburg with a zoom of 15.
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));

                // Zoom in, animating the camera.
                map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

        {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)

            {


                map.setMyLocationEnabled(true);
            }

        } else

        {

            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();

        }


    }

    private String getUrl(double latitude, double longitude, String nearbyPlace)

    {


        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

        googlePlaceUrl.append("location=" + latitude + "," + longitude);

        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);

        googlePlaceUrl.append("&type=" + nearbyPlace);

        googlePlaceUrl.append("&sensor=true");

        googlePlaceUrl.append("&key=" + "AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");

        Toast.makeText(this, googlePlaceUrl, Toast.LENGTH_SHORT).show();

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());


        return googlePlaceUrl.toString();

    }


    Button button;

    void Addcontrol() {


        arrayList = new ArrayList<>();

        arrayAdapter = new adapterlocation(this, R.layout.index_location, arrayList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        spinner = findViewById(R.id.spinner);
        distance = new ArrayList<>();
        distance.add("1km");
        distance.add("2km");
        distance.add("5km");
        arrayAdapterdis = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, distance);
        spinner.setAdapter(arrayAdapterdis);
    }


    //search
    void AddEvent() {
        //  myDatabase.db_delete();
        KeyboardVisibilityEvent.setEventListener(
                this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        BottomNavigationView bottomNavigationView=findViewById(R.id.navi);

                        if(isOpen)
                        {
                            //listView.setVisibility(View.INVISIBLE);
                            bottomNavigationView.setVisibility(View.INVISIBLE);
                        }else {
                            bottomNavigationView.setVisibility(View.VISIBLE);
                            //listView.setVisibility(View.VISIBLE);
                        }
                    }
                });

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



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        PROXIMITY_RADIUS = 1000;
                        restaurent(view);
                        break;
                    case 1:
                        PROXIMITY_RADIUS = 2000;
                        restaurent(view);
                        break;
                    case 2:
                        PROXIMITY_RADIUS = 5000;
                        restaurent(view);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void restaurent(View view) {
        Object dataTransfer[] = new Object[2];

        GetNearbyBanksData getNearbyPlacesData = new GetNearbyBanksData();

        map.clear();

        String hospital = "restaurant";


        String url = getUrl(latitude, longitude, hospital);

        dataTransfer[0] = map;

        dataTransfer[1] = url;
        Circle circle = map.addCircle(new CircleOptions()
                .center(userLocation)
                .radius(PROXIMITY_RADIUS)
                .fillColor(0x550000FF).strokeColor(0x550000FF));


        getNearbyPlacesData.execute(dataTransfer);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 20));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }



}
