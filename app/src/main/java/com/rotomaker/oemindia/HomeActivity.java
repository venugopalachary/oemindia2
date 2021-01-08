package com.rotomaker.oemindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {
     ChipNavigationBar bottomNav;
     FragmentManager fragmentManager;
     public static final String TAG=HomeActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNav=findViewById(R.id.bottom_nav);

        if (savedInstanceState==null){
            bottomNav.setItemSelected(R.id.home,true);
            fragmentManager=getSupportFragmentManager();
            CategoryFragment categoryFragment= new CategoryFragment();
            fragmentManager.beginTransaction().replace(R.id.frame_Container,categoryFragment).commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment =null;
                switch (id){
                    case R.id.home:
                        fragment=new CategoryFragment();
                        break;
                    case R.id.activity:
                        fragment=new ChatFragment();
                        break;
                    case R.id.favorites:
                        fragment=new ContactFragment();
                        break;
                    case R.id.settings:
                        fragment=new ProfileFragment();
                        break;
                }
                if(fragment!=null){
                    fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_Container,fragment).commit();
                }
                else {
                    Log.e(TAG,"Error in creating Fragmment");
                }

            }
        });
    }
}