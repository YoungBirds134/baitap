package com.example.baitap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Main;
import com.example.baitap.Activity_NowPlaying;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_LoveSongs;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.Model.Song;
import com.example.baitap.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Fragment_LoveSongs extends Fragment implements Adapter_RecycleView_Song_ThuVien.OnItemClickListener, Adapter_RecycleView_Song_LoveSongs.OnItemClickListener {
    RecyclerView recyclerView;
    Adapter_RecycleView_Song_LoveSongs adapter_recycleView_song_loveSongs;
Button button;
    public static Song song = new Song();
    ArrayList<Song> arrayList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);
        button=view.findViewById(R.id.btn_playing_all_lovetsong);
        recyclerView = view.findViewById(R.id.recycler_view_thuvien);
        adapter_recycleView_song_loveSongs = new Adapter_RecycleView_Song_LoveSongs(Activity_Main.arrayList_lovesong, getContext());
        recyclerView.setAdapter(adapter_recycleView_song_loveSongs);
        adapter_recycleView_song_loveSongs.setOnItemClickListener(Fragment_LoveSongs.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)); return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), Activity_NowPlaying.class);
                    Bundle  bundle = new Bundle();
                    bundle.putSerializable("array_thuvien",(Serializable) Activity_Main.arrayList_lovesong);
                    try {
                        if (Activity_NowPlaying.musicPlayer != null) {
                            Activity_NowPlaying.musicPlayer.stop();

                        }
                    } catch (Exception e) {

                    }
                    intent.putExtra("bundle",bundle);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            e.getMessage();
        }

    }

    public void onItemClick(int position) {

      song =  Activity_Main.arrayList_lovesong.get(position);


        Intent i = new Intent(getContext(), Activity_NowPlaying.class);
        Bundle  bundle = new Bundle();
        bundle.putSerializable("array_thuvien",(Serializable)Activity_Main.arrayList_lovesong);
        i.putExtra("Position", position);
        i.putExtra("MaBaiHat", Activity_Main.arrayList_lovesong.get(position).getId_Song());
        i.putExtra("TenBaiHat", Activity_Main.arrayList_lovesong.get(position).getName_Song());
        i.putExtra("TenCaSi", Activity_Main.arrayList_lovesong.get(position).getName_Artist());
        i.putExtra("ThoiGian", Activity_Main.arrayList_lovesong.get(position).getDuration());
        i.putExtra("HinhAnh", Activity_Main.arrayList_lovesong.get(position).getImage_Song());
        i.putExtra("Link", Activity_Main.arrayList_lovesong.get(position).getPath());
        try {
            if (Activity_NowPlaying.musicPlayer != null) {
                Activity_NowPlaying.musicPlayer.stop();

            }
        } catch (Exception e) {
        }
        i.putExtra("bundle",bundle);
        getContext().startActivity(i);

    }
}
