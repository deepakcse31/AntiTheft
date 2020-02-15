package com.example.programmabletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Timer1 extends AppCompatActivity {
    EditText start1,end1,start2,end2;
    Calendar calendar;
    DatePickerDialog picker;
    int currentHour;
    int currentMinute;
    String name1;
    String amPm;
    TimePickerDialog timePickerDialog;
    Button submit2,Resett1,Resett2,Devicetime;
    RequestQueue requestQueue;
    TextView Device1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer1);
        start1=findViewById(R.id.start);
        end1=findViewById(R.id.end);

        Resett1=findViewById(R.id.reset1);
        Resett2=findViewById(R.id.reset2);

        start1.setInputType(InputType.TYPE_NULL);
        start1.setText("00:00");
        start2=findViewById(R.id.starttimer2);
        end2=findViewById(R.id.endtimer2);
        submit2=findViewById(R.id.submittimer2);
        Devicetime=findViewById(R.id.devicetime);
        Device1=findViewById(R.id.device);
        Resett1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start1.setText("00:00");
                end1.setText("00:00");
            }
        });
        Resett2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start2.setText("00:00");
                end2.setText("00:00");
            }
        });
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                timePickerDialog = new TimePickerDialog(Timer1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        start1.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
        end1.setInputType(InputType.TYPE_NULL);
        end1.setText("00:00");
        end1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Timer1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        end1.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
        start2.setInputType(InputType.TYPE_NULL);
        start2.setText("00:00");
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Timer1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        start2.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();

            }
        });
        end2.setInputType(InputType.TYPE_NULL);
        end2.setText("00:00");
        end2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Timer1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        end2.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String starttime1=start2.getText().toString();
                Log.e("start time","End Time"+starttime1);
                String endtime1=end2.getText().toString();
                String starttime=start1.getText().toString();
                Log.e("start time","End Time"+starttime);
                String endtime=end1.getText().toString();
                Log.e("start time","End Time"+endtime);
                Log.e("start time","End Time"+endtime1);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                try {
                    Date inTime = sdf.parse(starttime);
                    Date outTime = sdf.parse(endtime);
                    Date inTime1 = sdf.parse(starttime1);
                    Date outTime1 = sdf.parse(endtime1);
                    if (outTime1.before(inTime1))
                    {
                        Toast.makeText(Timer1.this, "Please Enter correct Time!", Toast.LENGTH_SHORT).show();
                    }
                    else if (outTime.before(inTime))
                    {
                        Toast.makeText(Timer1.this, "Please Enter correct Time!", Toast.LENGTH_SHORT).show();

                    }

                    else {
                        String x="http://192.168.4.1/@T1_S"+starttime+",E"+endtime+"T2_S"+starttime1+",E"+endtime1+"$";
                        x = x.replace("AM","");
                        x = x.replace("PM","");
                        Log.e("Link1",x);
                        StringRequest request=new StringRequest(Request.Method.GET, x,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                                        Log.e("Link1","Link2"+response);
                                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params=new HashMap<>();

                                return params;
                            }
                        };
                        Volley.newRequestQueue(getApplicationContext()).add(request);

                        Toast.makeText(getApplicationContext(),"Time is correct",Toast.LENGTH_LONG).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }


        });
        Devicetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Device1.setText("12:05");
            }
        });
    }

}
