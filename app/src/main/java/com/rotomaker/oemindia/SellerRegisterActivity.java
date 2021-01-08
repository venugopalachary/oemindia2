package com.rotomaker.oemindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SellerRegisterActivity extends AppCompatActivity {

    String  phonenumberv,otpv,emailidv,serverotp;
    EditText phonenumber,otp,emailid;
    Button submitbutton,getotp;
    private RequestQueue mQueue,mQueue2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        // intilization
        phonenumber=findViewById(R.id.phonenumber);
        otp=findViewById(R.id.otp);
        emailid=findViewById(R.id.emailid);

        submitbutton=findViewById(R.id.submitbutton);
        getotp=findViewById(R.id.getotp);


        // volley
        mQueue = Volley.newRequestQueue(getApplicationContext());
        mQueue2 = Volley.newRequestQueue(getApplicationContext());



        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phonenumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the mobilenumber", Toast.LENGTH_SHORT).show();
                }else{
                    phonenumberv    = phonenumber.getText().toString();

                    sendSms(phonenumberv);
                }


            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                phonenumberv    = phonenumber.getText().toString();
                otpv             = otp.getText().toString();
                emailidv           = emailid.getText().toString();

                if (phonenumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the mobilenumber", Toast.LENGTH_SHORT).show();
                }
                if (otp.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the otp", Toast.LENGTH_SHORT).show();
                }

                if (emailid.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the email", Toast.LENGTH_SHORT).show();
                }





                if(emailid.getText().toString().isEmpty()&&phonenumber.getText().toString().isEmpty()&&otp.getText().toString().isEmpty()&&otp.getText().toString().isEmpty()
                 )

                {
                    // here are fields are important
                }
                else{



                    if(serverotp.equals(otpv)){
                        jsonParse();
                    }else{
                        Toast.makeText(getApplicationContext(), "wrongotp", Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });

    }



    private void jsonParse() {


        String url="https://android.oemindia.com/mobileversion/users/verifyuser.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Log.i("response", response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        // here we are checking for response cases

                        if(response.equalsIgnoreCase("success")) {
                            //here we are first deleting past progress dialog to show new dialog

                            Intent i = new Intent(getApplicationContext(), SellerGstActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("mobilenumber",phonenumberv );
                            i.putExtra("email", emailidv);
                            startActivity(i);



                            // Display the response string.

                        }else{
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i("error",error+"");
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("mobilenumber",phonenumberv );
                params.put("email",emailidv );
                return params;
            }
        };
        // Add the request to the RequestQueue.
        mQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


    private void sendSms(String mobilenumber){


        String url="https://android.oemindia.com/sendsms.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverotp=response;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i("error",error+"");
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobilenumber",phonenumberv );
                return params;
            }
        };
        // Add the request to the RequestQueue.
        mQueue.add(stringRequest2);

        stringRequest2.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
}