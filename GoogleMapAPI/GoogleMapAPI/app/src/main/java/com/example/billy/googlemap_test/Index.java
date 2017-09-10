package com.example.billy.googlemap_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
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
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Support_Json.ParserTask;
import Support_Json.ReadTask;
import adapter.adapterlocation;
import model.Location;
import sqlite.Databasehelper;

public  class Index extends AppCompatActivity implements OnMapReadyCallback {
    public  static  GoogleMap map;
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

    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cửa hàng");
        ActivityCompat.requestPermissions(Index.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        // ActivityCompat.requestPermissions(Index.this,new String[]{Manifest.permission.,Manifest.permission.ACCESS_COARSE_LOCATION},1);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar_search);
        View view =getSupportActionBar().getCustomView();
        editText7 = view.findViewById(R.id.edt);
        spinner = view.findViewById(R.id.spinner);


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
            return;

        }  map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        android.location.Location location = service.getLastKnownLocation(provider);
        userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        map.addMarker(new MarkerOptions()
                .title("YEN SUSHI PREMIUM ")
                .snippet("123 Bà Huyện Thanh Quan, Q.3, HCM\n" +
                        "Điện thoại:  028 39 318 828\n")
                .position(new LatLng(10.781213, 106.682021)));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                sushi1, 15));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Lỗi permission", Toast.LENGTH_LONG).show();
            return;
        }
        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener(){
            @Override
            public boolean onMyLocationButtonClick()
            {
              UpdateRes();


                return false;
            }
        });
        final int[] i = {0};
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(android.location.Location location) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }


        });

    }

    private String  getMapsApiDirectionsUrl(LatLng origin,LatLng dest) {
        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
        return url;

    }
    void UpdateRes()
    {
        map.clear();


        restaurent(null);


    }
    //Add restaurant
    public void AddMakerCustom(View view) {
        Geocoder geoCoder = new Geocoder(Index.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geoCoder.getFromLocationName(editText7.getText() + "", 5);
            if (addresses.size() > 0) {
                Double lat = (double) (addresses.get(0).getLatitude());


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
                map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

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

        googlePlaceUrl.append("&key=" + "AIzaSyAafUK3_rCTM6esCaZKIj7DNTu8ZkQ6QCw");


        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());


        return googlePlaceUrl.toString();

    }

    View bottomSheet;
    Button button;

    void Addcontrol() {

         bottomSheet = findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);



//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetBehavior.setPeekHeight(80);
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                200f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(2000);  // animation duration
        animation.setRepeatCount(0);  // animation repeat count
        animation.setRepeatMode(0);   // repeat animation (left to right, right to left )
        animation.setFillAfter(true);
        bottomSheet.startAnimation(animation);
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                bottomSheet.animate().translationY(200).setDuration(0).start();
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                bottomSheet.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
//            }
//
//        });

        arrayList = new ArrayList<>();

        arrayAdapter = new adapterlocation(this, R.layout.index_location, arrayList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);

        distance = new ArrayList<>();
        distance.add("1km");
        distance.add("2km");
        distance.add("5km");
        arrayAdapterdis = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, distance);
        spinner.setAdapter(arrayAdapterdis);
    }


    int Posi;
    //search
    void AddEvent() {


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Index.this, Info.class);
                intent.putExtra("object", arrayList.get(i));
                Posi=i;
                startActivityForResult(intent,123);
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
        Object dataTransfer[] = new Object[4];

        GetNearbyBanksData getNearbyPlacesData = new GetNearbyBanksData();

        map.clear();

        String hospital = "restaurant";


        String url = getUrl(latitude, longitude, hospital);

        dataTransfer[0] = map;

        dataTransfer[1] = url;
        dataTransfer[2]=arrayAdapter;
        dataTransfer[3]=arrayList;
        Circle circle = map.addCircle(new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(PROXIMITY_RADIUS)
                .fillColor(0x550000FF).strokeColor(0x550000FF));


        getNearbyPlacesData.execute(dataTransfer);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==123 && resultCode== RESULT_OK)
        {

                for (Polyline polyline : ParserTask.polylines) {
                    polyline.remove();
                }
                double lati=arrayList.get(Posi).getLati();
                double longti=arrayList.get(Posi).getLongti();
                LatLng latLng=new LatLng(lati,longti);
                String url = getMapsApiDirectionsUrl(Index.userLocation, latLng);
                ReadTask downloadTask = new ReadTask();
                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
                Toast.makeText(this,"Tìm đường thành công",Toast.LENGTH_LONG).show();
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


        }
        else Toast.makeText(this,"What??",Toast.LENGTH_LONG).show();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
