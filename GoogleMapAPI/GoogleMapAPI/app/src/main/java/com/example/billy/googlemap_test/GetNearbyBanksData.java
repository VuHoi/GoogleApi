package com.example.billy.googlemap_test;

/**
 * Created by Vu Khac Hoi on 9/7/2017.
 */


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Support_Json.ParserTask;
import Support_Json.ReadTask;
import model.Location;

/**
 * Created by Vu Khac Hoi on 9/6/2017.
 */

public class GetNearbyBanksData extends AsyncTask<Object, String, String> {
    private String googlePlacesData;

    private GoogleMap mMap;

    String url;
    ArrayAdapter arrayAdapter;
    ArrayList<Location> locations;

    @Override

    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap) objects[0];

        url = (String) objects[1];
        arrayAdapter= (ArrayAdapter) objects[2];
        locations= (ArrayList<Location>) objects[3];



        DownloadURL downloadURL = new DownloadURL();

        try {

            googlePlacesData = downloadURL.readUrl(url);

        } catch (IOException e) {

            e.printStackTrace();

        }


        return googlePlacesData;

    }


    @Override

    protected void onPostExecute(String s) {


        List<HashMap<String, String>> nearbyPlaceList;

        DataParser parser = new DataParser();

        nearbyPlaceList = parser.parse(s);

        Log.d("nearbyplacesdata", "called parse method");

        showNearbyPlaces(nearbyPlaceList);

    }


    private void showNearbyPlaces(final List<HashMap<String, String>> nearbyPlaceList)

    {
        locations.clear();

        for (int i = 0; i < nearbyPlaceList.size(); i++)

        {

            final MarkerOptions markerOptions = new MarkerOptions();

            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);


            String placeName = googlePlace.get("place_name");

            String vicinity = googlePlace.get("vicinity");

            String photo_ref=googlePlace.get("photo_reference");


            locations.add(new Location(placeName,vicinity,"123",photo_ref));
            arrayAdapter.notifyDataSetChanged();

            double lat = Double.parseDouble(googlePlace.get("lat"));

            double lng = Double.parseDouble(googlePlace.get("lng"));


            LatLng latLng = new LatLng(lat, lng);

            markerOptions.position(latLng);

            markerOptions.title(placeName + " : " + vicinity);

            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


            mMap.addMarker(markerOptions);


        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                for (Polyline polyline : ParserTask.polylines) {
                    polyline.remove();
                }

                String url = getMapsApiDirectionsUrl(Index.userLocation, marker.getPosition());
                ReadTask downloadTask = new ReadTask();
                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
                return false;

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
}
