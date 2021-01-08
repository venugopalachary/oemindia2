package com.rotomaker.oemindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class SellerGstActivity extends AppCompatActivity {


     EditText gstno,companyname;
     Button submitbutton;
     String  gstnovalue,companynamevalue,mobilenumber,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_gst);
        gstno=findViewById(R.id.gstno);
        companyname=findViewById(R.id.companyname);
        submitbutton=findViewById(R.id.submitbutton);

        Intent intent = getIntent();
        mobilenumber = intent.getExtras().getString("mobilenumber");
        email = intent.getExtras().getString("email");

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstnovalue=gstno.getText().toString();
                companynamevalue=companyname.getText().toString();
                Intent i = new Intent(getApplicationContext(), SellerUploadProducts.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("mobilenumber",mobilenumber );
                i.putExtra("email", email);
                i.putExtra("gstnovalue", gstnovalue);
                i.putExtra("companynamevalue", companynamevalue);
                startActivity(i);
            }
        });

    }
}