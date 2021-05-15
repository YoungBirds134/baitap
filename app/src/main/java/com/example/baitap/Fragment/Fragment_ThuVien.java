package com.example.baitap.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.Model.Song;
import com.example.baitap.R;

import java.util.ArrayList;

public class Fragment_ThuVien extends Fragment {
    RecyclerView   recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    ArrayList<Song> arrayList;
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_thuvien,container,false);





        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList= initSong();
        adapter_recycleView_song_thuVien= new Adapter_RecycleView_Song_ThuVien(getContext(),arrayList);
        recyclerView=view.findViewById(R.id.recycler_view_thuvien);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter_recycleView_song_thuVien);
    }
    public ArrayList<Song> initSong(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for (int i = 1; i<10;i++)
        {
            arrayList.add(new Song(0+i,"Song"+i,"Image"+i,12+i,"Path"+i,0,"date"+i,"Genre"+i,"Album"+i,"Artist"+i));

        }
        return  arrayList;
    }
    }

