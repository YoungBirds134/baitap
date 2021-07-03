package com.example.baitap.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Main;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_LoveSongs;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.Model.Song;
import com.example.baitap.R;

import java.util.ArrayList;

public class Fragment_LoveSongs extends Fragment {
    RecyclerView recyclerView;
    Adapter_RecycleView_Song_LoveSongs adapter_recycleView_song_loveSongs;
    ArrayList<Song> arrayList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_thuvien);
        adapter_recycleView_song_loveSongs = new Adapter_RecycleView_Song_LoveSongs(Activity_Main.arrayList_lovesong, getContext());
        recyclerView.setAdapter(adapter_recycleView_song_loveSongs);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)); return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
