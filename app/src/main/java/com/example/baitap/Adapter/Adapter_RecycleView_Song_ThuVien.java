package com.example.baitap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public Adapter_RecycleView_Song_ThuVien(@NonNull Context context, ArrayList<Song> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list, parent, false);
        return new SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = arrayList.get(position);

        holder.textView_Name.setText(song.getName_Song());
        holder.textView_Des.setText(song.image_Song);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des;



        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name=(TextView)itemView.findViewById(R.id.item_album_view_title);
            textView_Des=(TextView)itemView.findViewById(R.id.item_album_view_aritst);
        }
    }
}
