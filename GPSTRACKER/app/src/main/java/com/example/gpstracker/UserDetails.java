package com.example.gpstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDetails extends AppCompatActivity {
    Button submit1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText Firestname, Lastname, Email, Alternativemobileno, Address, City, State;
    String HttpURL = "http://192.168.0.9/test/user_details.php";
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String Firstname1,Lastname1,Email1,Alternativemobileno1,Address1,City1,State1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        submit1 = findViewById(R.id.submit);
        sharedPreferences = getSharedPreferences("save_data", MODE_PRIVATE);
        final String name1 = sharedPreferences.getString("AD_Login", "");
        Firestname=findViewById(R.id.firstname);
        Lastname=findViewById(R.id.lastname);
        Email=findViewById(R.id.email);
        Alternativemobileno=findViewById(R.id.alternativemobile);
        Address=findViewById(R.id.address);
        City=findViewById(R.id.city);
        State=findViewById(R.id.state);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firstname1 = Firestname.getText().toString();
                Lastname1 = Lastname.getText().toString();
                Email1 = Email.getText().toString();
                Alternativemobileno1=Alternativemobileno.getText().toString();
                Address1=Address.getText().toString();
                City1=City.getText().toString();
                State1=State.getText().toString();
                Log.e("saturday1","saturday1"+name1);
                GetDataFromEditText();
                if (Firstname1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"please Enter First Name",Toast.LENGTH_LONG).show();
                }
                else if (Lastname1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Plase Enter LastName",Toast.LENGTH_LONG).show();
                }
                else if (Email1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Email",Toast.LENGTH_LONG).show();
                }
                else if (Alternativemobileno1.matches("")||Alternativemobileno1.length()==10)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Alternative Mobile No",Toast.LENGTH_LONG).show();
                }
                else if (Address1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Address",Toast.LENGTH_LONG).show();
                }
                else if (City1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the City",Toast.LENGTH_LONG).show();
                }
                else if (State1.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the State",Toast.LENGTH_LONG).show();
                }

                // Sending Student Name, Phone Number, Class to method to update on server.
                else {
                    StudentRecordUpdate(name1, Firstname1, Lastname1, Email1, Alternativemobileno1, Address1, City1, State1);
                }
            }
        });
    }
    // Method to get existing data from EditText.
    public void GetDataFromEditText(){


    }
    public void StudentRecordUpdate(final String name, final String f_name, final String l_name, final String e_mail,final String a_mobno,final String a_ddress,final String c_ity,final String s_tate){

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
                final AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);

                builder.setMessage("Your Registration was succesful.......");
                builder.setTitle("          Congrulations");
                AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert.show();

                Toast.makeText(UserDetails.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("phone",params[0]);

                hashMap.put("fname",params[1]);

                hashMap.put("lname",params[2]);

                hashMap.put("email",params[3]);

                hashMap.put("alt_num",params[4]);

                hashMap.put("address",params[5]);

                hashMap.put("city",params[6]);


                hashMap.put("state",params[7]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                Log.e("Result1->","Result1->"+finalResult);
                Log.e("Result1->","Result1->"+HttpURL);
                Log.e("Result1->","Result1->"+hashMap);
                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(name,f_name,l_name,e_mail,a_mobno,a_ddress,c_ity,s_tate);
    }

}