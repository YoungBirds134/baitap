package com.example.baitap.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull
String url="http://192.168.1.9:8080/MusicPlayer/image/big_city_boi.jpg";
Context context;
   public static ArrayList<Song> arrayList;

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
        Picasso.with(context).load(url+ song.getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_Song);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des, likeCountTextView;
ToggleButton favBtn;
ImageView imageView_Song;


        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name=(TextView)itemView.findViewById(R.id.item_album_view_title);
            textView_Des=(TextView)itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song=itemView.findViewById(R.id.item_album_view_image);
            favBtn = itemView.findViewById(R.id.favBtn);
favBtn.setChecked(false);

            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();



                    Toast.makeText(view.getContext(), "You clicked " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }


       }}
