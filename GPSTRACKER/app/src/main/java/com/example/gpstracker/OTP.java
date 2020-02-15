package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTP extends AppCompatActivity {
    Button otp1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Otp2;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp1=findViewById(R.id.generate_otp);
        e1=findViewById(R.id.edit_otp);
        sharedPreferences = getSharedPreferences("save_data", MODE_PRIVATE);
        String name = sharedPreferences.getString("AD_Login", "");
        final String otp = sharedPreferences.getString("otp_", "");

        Log.e("Result->","Result->"+otp);


        otp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String otp2=e1.getText().toString();
                Log.e("Result->","Result->"+otp2);
                if(otp.equals(otp2)) {
                    Intent i1 = new Intent(getApplicationContext(), UserDetails.class);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Otp",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
