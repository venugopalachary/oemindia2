package com.rotomaker.oemindia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    SharedPreferences preferences;
    Boolean firstTime;
   // ActionBar actionBar = getSupportActionBar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  actionBar.hide();
        SharedPreferences preferences;
        preferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        firstTime=preferences.getBoolean("firstTime",true);
        if (firstTime){
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    SharedPreferences.Editor editor=preferences.edit();
                    firstTime=false;
                    editor.putBoolean("firstTime",firstTime);
                    editor.apply();
                    Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

        }
        else{
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}