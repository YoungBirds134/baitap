package com.example.baitap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.Fragment.Fragment_LoveSongs;
import com.example.baitap.Fragment.Fragment_PlayList;
import com.example.baitap.Fragment.Fragment_Search;
import com.example.baitap.Fragment.Fragment_ThuVien;
import com.example.baitap.Fragment.Fragment_XuHuong;
import com.example.baitap.Model.Song;
import com.example.baitap.util.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity {
    BottomNavigationView navView;
    ImageView menu_button;
    Button search;
    Button button_NowPlaying;

    public static ArrayList<Song> arrayList_lovesong ;
    @Override
    public void onContentChanged() {
        super.onContentChanged();

    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     if (arrayList_lovesong!=null){}else {
         arrayList_lovesong = new ArrayList<>();
     }
        setContentView(R.layout.activity_main_home);

        menu_button = findViewById(R.id.menu_button);

        navView = findViewById(R.id.nav_view_thuvien);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.nav_thuVien);

//        menu_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity_Main.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_hot:
                    fragment = new Fragment_XuHuong();
                    loadFragment(fragment);

                    return true;

                case R.id.nav_star:

                    fragment = new Fragment_LoveSongs();
                    loadFragment(fragment);

                    return true;
                case R.id.nav_thuVien:

                    fragment = new Fragment_ThuVien();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_playList:
                    fragment = new Fragment_PlayList();
                    loadFragment(fragment);

                    return true;

                case R.id.nav_timKiem:

                    fragment = new Fragment_Search();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_fragment_thuvien, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }


}
