package com.example.gpstracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar t1;
    private GoogleMap mMap;
    InputStream inputStream;
    Geocoder geocoder;
    EditText fromdate,todate1,fromtime1,totime1,date1,time1;
    String[] ids;
    ArrayList<LatLng> coordinates = new ArrayList<>();
    PolylineOptions lineOptions = null;
    DrawerLayout drawerLayout;
    Calendar calendar;
    DatePickerDialog picker;
    int currentHour;
    int currentMinute;
     String name1;
    String amPm;
    String HttpURL = "http://192.168.0.9/test/user_details1.php";
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    TimePickerDialog timePickerDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name2,emailid,Address1,city1,state1;
    EditText name3,email3,address3,city3,state3;
    Button update1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        drawerLayout=findViewById(R.id.drawerlayout);
        t1=findViewById(R.id.toolbar1);
        setSupportActionBar(t1);
        sharedPreferences = getSharedPreferences("save_data", MODE_PRIVATE);
            name1 = sharedPreferences.getString("AD_Login", "");
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
                    // Log.e("Collumn 2 ",""+ids[2]);
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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e("id->","id->"+id);
        if (id == R.id.home1) {
            Log.e("id1->","id1->"+R.id.lockvehicle);
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.real_time_tracking)
        {
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.lockvehicle)
        {
            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.lock_vehicle,null);
            alert.setView(mview);
            alert.show();

            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.unlockvehicle)
        {
            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.unlock_vehicle,null);
            alert.setView(mview);
            alert.show();
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.drive_time)
        {
            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.drive_time_report,null);
         //   Log.e("","",+R.id.deepak);
            alert.setView(mview);
            alert.show();
            fromdate=(EditText) mview.findViewById(R.id.fromdate1);
           fromdate.setInputType(InputType.TYPE_NULL);

            fromdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(MapsActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    fromdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                    Toast.makeText(MapsActivity.this, "edittext is clicked", Toast.LENGTH_SHORT).show();
                }
            });
            todate1=(EditText) mview.findViewById(R.id.todate);
            todate1.setInputType(InputType.TYPE_NULL);
            todate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(MapsActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    fromdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                    Toast.makeText(MapsActivity.this, "edittext is clicked", Toast.LENGTH_SHORT).show();
                }
            });
                fromtime1=mview.findViewById(R.id.fromtime);
            fromtime1.setInputType(InputType.TYPE_NULL);
                fromtime1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendar = Calendar.getInstance();
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                        currentMinute = calendar.get(Calendar.MINUTE);

                        timePickerDialog = new TimePickerDialog(MapsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                if (hourOfDay >= 12) {
                                    amPm = "PM";
                                } else {
                                    amPm = "AM";
                                }
                                fromtime1.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                            }
                        }, currentHour, currentMinute, false);

                        timePickerDialog.show();
                    }


                });
                totime1=mview.findViewById(R.id.totime);
            totime1.setInputType(InputType.TYPE_NULL);
            totime1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendar = Calendar.getInstance();
                    currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calendar.get(Calendar.MINUTE);

                    timePickerDialog = new TimePickerDialog(MapsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            if (hourOfDay >= 12) {
                                amPm = "PM";
                            } else {
                                amPm = "AM";
                            }
                            totime1.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        }
                    }, currentHour, currentMinute, false);

                    timePickerDialog.show();

                }
            });
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.route_replay)
        {
            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.route_replay,null);
            //   Log.e("","",+R.id.deepak);
            alert.setView(mview);
            alert.show();
            time1=mview.findViewById(R.id.time2);
           time1.setInputType(InputType.TYPE_NULL);
            time1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendar = Calendar.getInstance();
                    currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calendar.get(Calendar.MINUTE);

                    timePickerDialog = new TimePickerDialog(MapsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            if (hourOfDay >= 12) {
                                amPm = "PM";
                            } else {
                                amPm = "AM";
                            }
                            time1.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        }
                    }, currentHour, currentMinute, false);

                    timePickerDialog.show();

                }
            });
            date1=mview.findViewById(R.id.date);
            date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(MapsActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.profile)
        {

            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.profile,null);
            //EditText editText=mview.findViewById(R.id.name);
            name3=mview.findViewById(R.id.name);
            email3=mview.findViewById(R.id.email);
            address3=mview.findViewById(R.id.Addressn);
            city3=mview.findViewById(R.id.city);
            state3=mview.findViewById(R.id.state);
            update1=mview.findViewById(R.id.update);

           // Log.e("Error Dekho aaj->","->"+data);
            //editText.setText(name1);
            //   Log.e("","",+R.id.deepak);
            update1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = getIntent().getExtras().getString("AnyKeyName");
                    name2=name3.getText().toString();
                    emailid=email3.getText().toString();
                    Address1=address3.getText().toString();
                    state1=state3.getText().toString();
                    city1=city3.getText().toString();
                    StudentRecordUpdate(data,name2,emailid,Address1,state1,city1);
                }
            });
            alert.setView(mview);
            alert.show();
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }

        else if (id==R.id.savebattery)
        {
            final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            View mview=getLayoutInflater().inflate(R.layout.savebattery,null);
            //   Log.e("","",+R.id.deepak);
            alert.setView(mview);
            alert.show();
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.logout)
        {
            //final AlertDialog.Builder alert=new AlertDialog.Builder(MapsActivity.this);
            //View mview=getLayoutInflater().inflate(R.layout.logout,null);
            //   Log.e("","",+R.id.deepak);
            //alert.setView(mview);
            //alert.show();
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            Intent i1=new Intent(getApplicationContext(),Login.class);
            startActivity(i1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Marker> markers = new ArrayList<>();
        List<Address> addresses=null;
        Log.e("Collumn 4 ", "" + coordinates);
       // Geocoder geocoder = new Geocoder(this,Locale.getDefault());
        //LatLng sydney = new LatLng(coordinates.,cor.latitude);
        //mMap.addMarker(new MarkerOptions().position(coordinates).title("Hello"));
        for (LatLng cor : coordinates) {
            Log.e("coordinates ",""+cor) ;
            LatLng sydney = cor;
            //Log.e("latitude and longitude","latitude"+sydney);
            lineOptions.addAll(Collections.singleton(cor));
            lineOptions.width(10);
            lineOptions.color(Color.RED);
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            try {
                Log.e("Address","Address"+cor.latitude);

                addresses = geocoder.getFromLocation(cor.latitude, cor.longitude,1);
                Log.e("Address","Address"+addresses);
            } catch (IOException e) {
                e.printStackTrace();
            }
  //          String address="" ;
//            address = addresses.get(0).getAddressLine(0);
   //         Log.e("dekho","dekho"+address);
     //       Marker marker = mMap.addMarker(new MarkerOptions().position(cor).title(address));
       //     markers.add(marker);
            mMap.addPolyline(lineOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            //mMap.addMarker(new MarkerOptions().position(cor).title("Hello"));

        }
    }
    public void StudentRecordUpdate(final String data_, final String name_2, final String email_id, final String address_1,final String state_1,final String city_1){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //progressDialog = ProgressDialog.show(UserDetails.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                //progressDialog.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);

                builder.setMessage("Your Registration was succesful.......");
                builder.setTitle("          Congrulations");
                AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert.show();

                Toast.makeText(MapsActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("phone",params[0]);

                hashMap.put("fname",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("address",params[3]);

                hashMap.put("city",params[4]);

                hashMap.put("state",params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                Log.e("Result1->","Result1->"+finalResult);
                Log.e("Result1->","Result1->"+HttpURL);
                Log.e("Result1->","Result1->"+hashMap);
                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(data_,name_2,email_id,address_1,state_1,city_1);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Toast.makeText(getApplicationContext(), "back press",
                    Toast.LENGTH_LONG).show();

        return false;
        // Disable back button..............
    }
}

