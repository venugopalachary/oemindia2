package com.rotomaker.oemindia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText username,emailid,mobilenumber,password,state,city;

  // EditText username,emailid,mobilenumber,password,state,city;
   String Username,Emailid,Mobilenumber,Password,State,City;
   ImageView Register;
   TextView Login;
    private String Registerurl="Registerurl";
    private static final int PERMISSION_REQUEST_CODE = 200;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.editTextName);
        emailid=findViewById(R.id.editTextEmail);
        mobilenumber=findViewById(R.id.editTextMobile);
        password=findViewById(R.id.editTextPassword);
        state=findViewById(R.id.textstate);
        city=findViewById(R.id.textcity);
        Login=findViewById(R.id.loginpage);
        Register=findViewById(R.id.signup);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                Username=username.getText().toString();
                Emailid=emailid.getText().toString().trim();
                Mobilenumber=mobilenumber.getText().toString().trim();
                Password=password.getText().toString().trim();
                State=state.getText().toString();
                City=city.getText().toString().trim();

                if (Username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Name",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(Mobilenumber.equals("") || Mobilenumber.equals(null)||Mobilenumber.length()<10) {
                    Toast.makeText(getApplicationContext(), "Enter Your Mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Emailid.isEmpty()&& !Emailid.matches(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Enter Valid Emailid", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(City.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your City",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(State.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your State",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    if (!checkPermission()) {

                        requestPermission();

                    } else {



                        Register();
                    }


                }

            }
        });



    }

    private boolean checkPermission() {

        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);


        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED&& result3 == PackageManager.PERMISSION_GRANTED&& result4 == PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermission () {

        ActivityCompat.requestPermissions(this, new String[]{ CAMERA,  CALL_PHONE, READ_PHONE_STATE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {


                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean callPhone = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readPhoneState = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = grantResults[3] == PackageManager.PERMISSION_GRANTED;


                    if (cameraAccepted&&callPhone&&readPhoneState&&writeExternalStorage) {


                        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            // requestPermission();
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }


                        Register();

                        //   Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access location data and camera.", Toast.LENGTH_LONG).show();

                    }

                    else {
                        //  Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data and camera.", Toast.LENGTH_LONG).show();


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow all  permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ CAMERA,CALL_PHONE,READ_PHONE_STATE,WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }



    private void Register() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Registerurl, new Response.Listener <String>() {
            @Override
            public void onResponse(String response) {

                if(response.equalsIgnoreCase("success")){

                   // Toast.makeText(getApplicationContext(),"Check Your Mobilenumber For Password",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);


                }
                else{
                    Toast.makeText(getApplicationContext(),"Mobilenumber or Emailid Already Registered",Toast.LENGTH_LONG).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", Username);
                params.put("mobilenumber",Mobilenumber);
                 params.put("emailid", Emailid);
                 params.put("password", Password);
                params.put("city", City);
                params.put("state", State);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}