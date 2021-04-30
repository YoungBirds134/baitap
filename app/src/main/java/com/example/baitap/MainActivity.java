package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler handler = new Handler();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(chuyenCanh, 3000);
    }

        Runnable chuyenCanh = new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this, Activity_Main.class);
                startActivity(i);
                finish();

            }
        };
    }
