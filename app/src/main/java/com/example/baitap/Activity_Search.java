package com.example.baitap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Adapter.Adapter_RecycleView_Song_LoveSongs;
import com.example.baitap.Fragment.Fragment_ThuVien;
import com.example.baitap.Model.Song;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.baitap.R.layout.activity_search;

public class Activity_Search extends AppCompatActivity implements Adapter_RecycleView_Song_LoveSongs.OnItemClickListener{

    ArrayList<Song> arrayList;
    SearchView searchView;
RecyclerView recyclerView;
    Adapter_RecycleView_Song_LoveSongs adapter_recycleView_song_loveSongs;
    Song song;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_search);

        searchView=findViewById(R.id.inputSearch);
        recyclerView=findViewById(R.id.rv_search);

        adapter_recycleView_song_loveSongs=new Adapter_RecycleView_Song_LoveSongs(Fragment_ThuVien.arrayList,this);
        recyclerView.setAdapter(adapter_recycleView_song_loveSongs);
        adapter_recycleView_song_loveSongs.setOnItemClickListener(Activity_Search.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                Filler(newText);
                return true;
            }
        });
    }
    public void Filler(String text){
        ArrayList<Song> tam = new ArrayList<>();
        if (text.isEmpty()){
            tam=Fragment_ThuVien.arrayList;
        }else {
            for (Song song :Fragment_ThuVien.arrayList)
            {
                if (song.getName_Song().toLowerCase().contains(text.toLowerCase()))
                {
                    tam.add(song);
                }
            }
        }
        adapter_recycleView_song_loveSongs.UpdateList(tam);
    }
    @Override
    public void onItemClick(int position) {



        Intent i = new Intent(Activity_Search.this, Activity_NowPlaying.class);
        Bundle  bundle = new Bundle();
        bundle.putSerializable("array_thuvien",(Serializable)Fragment_ThuVien.arrayList);
        i.putExtra("Position", position);
        i.putExtra("MaBaiHat", Fragment_ThuVien.arrayList.get(position).getId_Song());
        i.putExtra("TenBaiHat", Fragment_ThuVien.arrayList.get(position).getName_Song());
        i.putExtra("TenCaSi", Fragment_ThuVien.arrayList.get(position).getName_Artist());
        i.putExtra("ThoiGian", Fragment_ThuVien.arrayList.get(position).getDuration());
        i.putExtra("HinhAnh", Fragment_ThuVien.arrayList.get(position).getImage_Song());
        i.putExtra("Link", Fragment_ThuVien.arrayList.get(position).getPath());
        try {
            if (Activity_NowPlaying.musicPlayer != null) {
                Activity_NowPlaying.musicPlayer.stop();

            }
        } catch (Exception e) {
        }
        i.putExtra("bundle",bundle);
        startActivity(i);
    }
}
