package com.example.map1;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference rootref;
    InputStream inputStream;
    Geocoder geocoder;

    String[] ids;
    ArrayList<LatLng> coordinates = new ArrayList<>();
    PolylineOptions lineOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        inputStream = getResources().openRawResource(R.raw.gps);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;

            while ((csvLine = reader.readLine()) != null) {
                ids=csvLine.split(",");
                try{
                    geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                    lineOptions = new PolylineOptions();

                    coordinates.add(new LatLng(Double.parseDouble(ids[2]),Double.parseDouble(ids[3])));
                    Log.e("Collumn 1 ",""+ids[2]) ;
                    Log.e("Collumn 3 ",""+ids[3]) ;
                    //Log.e("Collumn 4 ",""+coordinates) ;
                    // Log.e("Collumn 2 ",""+ids[2]) ;
                }catch (Exception e){
                    Log.e("Unknown ",e.toString());
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Marker> markers = new ArrayList<>();
        List<Address> addresses = null ;
        Log.e("Collumn 4 ", "" + coordinates);
        //LatLng sydney = new LatLng(coordinates.,cor.latitude);
        //mMap.addMarker(new MarkerOptions().position(coordinates).title("Hello"));
        for (LatLng cor : coordinates) {
            Log.e("coordinates ",""+cor) ;
                LatLng sydney = cor;
            //Log.e("latitude and longitude","latitude"+sydney);
            lineOptions.addAll(Collections.singleton(cor));
            lineOptions.width(10);
            lineOptions.color(Color.RED);

            try {
                addresses = geocoder. getFromLocation(cor.latitude, cor.longitude,1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            String address = addresses.get(0).getAddressLine(0);


            Marker marker = mMap.addMarker(new MarkerOptions().position(cor).title(address));
            markers.add(marker);
            mMap.addPolyline(lineOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
             //mMap.addMarker(new MarkerOptions().position(cor).title("Hello"));

        }
    }
}
