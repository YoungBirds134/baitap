package com.example.baitap.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull
String url="http://192.168.1.7:8080/MusicPlayer/image/";

    ArrayList<Song> arrayList;
    Context context;
MediaPlayer mediaPlayer;
public  Song song = new Song();
    public Adapter_RecycleView_Song_ThuVien(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list, parent, false);
        return new SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
       song = arrayList.get(position);

        holder.textView_Name.setText(song.getName_Song());
        holder.textView_Des.setText(song.getName_Artist());
        Picasso.with(context).load(url+ song.getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_Song);
//Load audio bằng setDataSrout ("hthth"+ sdong.getpath)
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
MediaPlayer mediaPlayer;
        TextView textView_Name;
        TextView textView_Des, likeCountTextView;
ToggleButton favBtn;
ImageView imageView_Song;
TextView number;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name=(TextView)itemView.findViewById(R.id.item_album_view_title);
            textView_Des=(TextView)itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song=itemView.findViewById(R.id.item_album_view_image);
            favBtn = itemView.findViewById(R.id.favBtn);
            number=itemView.findViewById(R.id.number);
favBtn.setChecked(false);


imageView_Song.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        try {
//            String a="http://localhost:8080/MusicPlayer/audio/"+song.getPath();
//            mediaPlayer.setDataSource(context, Uri.parse(a));
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//            Toast.makeText(context,"Success"+song.getPath(),Toast.LENGTH_LONG).show();
//        }catch (Exception exception){
//            Toast.makeText(context,exception.getMessage()+""+song.getPath(),Toast.LENGTH_LONG).show();
//        }
        PreparePlaying();
    }
});

            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();


PreparePlaying();
                    Toast.makeText(view.getContext(), "You clicked " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public  void PreparePlaying(){
            try {
                mediaPlayer.setDataSource("http://192.168.1.7:8080/MusicPlayer/audio/BigCityBoi.mp3");
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            }catch (Exception exception){
                Toast.makeText(context,exception.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
       }}
