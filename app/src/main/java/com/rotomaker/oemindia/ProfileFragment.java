package com.rotomaker.oemindia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {

    View view;
    TextInputEditText username,emailid,password;
    String Username,Emailid,Password;
    Button update,logout;
    TextView cityname,statename,mobilenumber;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_profile, container, false);
        username=view.findViewById(R.id.username);
        emailid=view.findViewById(R.id.emailid);
        password=view.findViewById(R.id.password);
        cityname=view.findViewById(R.id.city_label);
        statename=view.findViewById(R.id.state_label);
        mobilenumber=view.findViewById(R.id.mobilenumber_field);
        update=view.findViewById(R.id.update);
        logout=view.findViewById(R.id.logout);
        Username=username.getText().toString().trim();
        Emailid=emailid.getText().toString().trim();
//        Mobilenumber=phonenumber.getText().toString().trim();
        Password=password.getText().toString().trim();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}