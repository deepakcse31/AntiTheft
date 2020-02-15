package com.example.gpstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
        TextView links,register,forgetpassword;
        EditText username1,password1;
        Button login;
    ProgressDialog progressDialog;
    String finalResult ;
    String HttpURL = "http://192.168.0.11/test/Login.php";
    Boolean CheckEditText ;
    HashMap<String,String> hashMap = new HashMap<>();
    RequestQueue requestQueue;
    String HttpUrl = "http://192.168.0.11/test/Login.php";
    HttpParse httpParse = new HttpParse();
    String PasswordHolder, EmailHolder;
    public static final String UserEmail = "";
    String phone,pass;

    RequestQueue rq5;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Boolean saveLogin;
    CheckBox mCheckBoxRegular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCheckBoxRegular = findViewById(R.id.terms);
        register = findViewById(R.id.new_user_resigtration);
        links = findViewById(R.id.link);
        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        login = findViewById(R.id.login);
        forgetpassword = findViewById(R.id.forgetpassword);
        links.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        requestQueue = Volley.newRequestQueue(Login.this);
        progressDialog = new ProgressDialog(Login.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    login();
               /* if (name.equals("deepak")&&password.equals("1234")) {
                    Intent i1=new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(i1);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "please enter right username and password", Toast.LENGTH_LONG).show();
                }*/
            }

        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forget = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(forget);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(getApplicationContext(), Register.class);
                startActivity(reg);

                Toast.makeText(getApplicationContext(), "register ho gya tu", Toast.LENGTH_LONG).show();
            }
        });
        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                builder.setMessage("this is message");
                builder.setTitle("Terms And Condition");

                //Setting message manually and performing action on button click
                builder.setMessage("\"I agree to the Terms and Conditions\" or \"I agree to the Privacy Policy\"\n" +
                        "\"I have read and agree to the Terms\" or \"I have read and agree to the Privacy Policy\"\n" +
                        "\"I accept the Terms of Service\" or \"I accept the Privacy Statement\"\n" +
                        "\"Click here to indicate that you have read and agree to the terms presented in the Terms and Conditions agreement\"");
                //This will not allow to close dialogbox until user selects an option
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mCheckBoxRegular.setChecked(true);
                        Toast.makeText(getApplicationContext(), "positive button", Toast.LENGTH_SHORT).show();
                        //builder.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        mCheckBoxRegular.setChecked(false);
                        Toast.makeText(getApplicationContext(), "negative button", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert.show();
            }
        });

    }
        public void login()
        {
            StringRequest request=new StringRequest(Request.Method.POST, "http://192.168.0.9/test/Login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jobj = new JSONObject(response);
                                String status = jobj.getString("st");
//                                String result=jobj.getString("data");
                                JSONObject songsObject = jobj.getJSONObject("data");
                                JSONArray songsArray = songsObject.toJSONArray(songsObject.names());
//                                JSONArray jsonArray=jobj.getJSONArray("data");
                                Log.e("Result->","Result->"+songsArray);
//                                Log.e("Result->","Result->"+jsonArray);

                                //String phone1=jobj.getString("phone");
                                if (status.equals("true")) {
                                    String id = songsObject.getString("id");
                                    String name = songsObject.getString("name");
                                    String phone = songsObject.getString("phone");
                                    String email = songsObject.getString("email");
                                    String password = songsObject.getString("password");
                                    String imei = songsObject.getString("imei");
                                    String vehicle = songsObject.getString("vehicle_no");
                                    String status1 = songsObject.getString("status");
                                    Intent i2=new Intent(getApplicationContext(),MapsActivity.class);
                                    i2.putExtra("AnyKeyName",phone);
                                    startActivity(i2);
                                   Toast.makeText(getApplicationContext(), "id->"+id+"name->"+name+"phone->"+phone+"email"+email+"password"+password+"imei"+imei+"vehicle"+vehicle, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "this is response" + response, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"There is some problem in link",Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("phone",username1.getText().toString());
                    params.put("pass",password1.getText().toString());
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(request);
        }
}
