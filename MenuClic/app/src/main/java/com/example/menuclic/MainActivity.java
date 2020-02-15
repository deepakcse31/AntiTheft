package com.example.menuclic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar1=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        MenuInflater inf  later=getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home2) {
            //Log.e("id1->","id1->"+R.id.lockvehicle);
            Toast.makeText(this,"my cart button is click1",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id==R.id.home1)
        {
            Toast.makeText(this,"my cart button is click",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
