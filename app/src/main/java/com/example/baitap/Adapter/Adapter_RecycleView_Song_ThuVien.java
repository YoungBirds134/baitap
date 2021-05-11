package com.example.baitap.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Song;
import com.example.baitap.R;

import java.util.ArrayList;

public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull

Context context;
    ArrayList<Song> arrayList;

    public Adapter_RecycleView_Song_ThuVien(ArrayList<Song> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songs_list, parent, false);
        return new SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = arrayList.get(position);

        holder.textView_Name.setText(song.getTitle());
        holder.textView_Des.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des;

        public SongHolder(@NonNull View itemView, TextView textView_Name, TextView textView_Des) {
            super(itemView);
            this.textView_Name = itemView.findViewById(R.id.item_album_view_title);
            this.textView_Des = itemView.findViewById(R.id.item_album_view_aritst);
        }

        public SongHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
