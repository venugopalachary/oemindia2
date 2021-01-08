package com.rotomaker.oemindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

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

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mobilenumber,password;
    ImageView Loginpage;
    String Mobilenumber,Password;
    TextView Signup;
    private String loginurl="Loginurl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobilenumber=findViewById(R.id.editTextMobile);
        password=findViewById(R.id.editTextPassword);
        Signup=findViewById(R.id.register);
        Loginpage=(ImageView) findViewById(R.id.login);
        Mobilenumber=mobilenumber.getText().toString().trim();
        Password=password.getText().toString().trim();
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
                //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
                if (Mobilenumber.equals("") || Mobilenumber.equals(null)||Mobilenumber.length()<10) {
                    Toast.makeText(getApplicationContext(), "Enter Your Mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Password.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    Login();

                }
        }
    });

    }

    private void Login() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,loginurl, new Response.Listener <String>() {
            @Override
            public void onResponse(String response) {

                if(response.equalsIgnoreCase("success")){

                  //  Toast.makeText(getApplicationContext(),"Check Your Mobilenumber For Password",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Mobilenumber Not Registered",Toast.LENGTH_LONG).show();
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

                // params.put("emailid", emailid);
                params.put("mobilenumber",Mobilenumber);
                params.put("password", Password);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}