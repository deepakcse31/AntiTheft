package com.example.csvreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    InputStream inputStream;

    String[] ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputStream = getResources().openRawResource(R.raw.gps);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {



                ids=csvLine.split(",");
                try{

                    Log.e("Collumn 1 ",""+ids[2]) ;
                    Log.e("Collumn 3 ",""+ids[3]) ;
                   // Log.e("Collumn 2 ",""+ids[2]) ;



                }catch (Exception e){
                    Log.e("Unknown fuck",e.toString());
                }
            }




        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }



    }
    }

