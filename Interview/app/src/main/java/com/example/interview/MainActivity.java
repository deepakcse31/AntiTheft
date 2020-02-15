package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String e1;
    String e2;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.e1);
        t2=findViewById(R.id.e2);
        e1=t1.getText().toString();
        e2=t2.getText().toString();
    }
}
