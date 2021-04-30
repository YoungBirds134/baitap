package com.example.baitap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_Main extends AppCompatActivity {
    ImageView menu_button;
    Button search;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        menu_button=findViewById(R.id.menu_button);


        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Main.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId())
            {
                case R.id.nav_hot:


                    return true;

                case R.id.nav_star:


                    return true;
                case R.id.nav_thuVien:


                    return true;
                case R.id.nav_playList:


                    return true;

                case R.id.nav_timKiem:

                    fragment=new Fragment_Search();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }};


    private  void loadFragment(Fragment fragment)
    {
        //load fragment
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lay,fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}
