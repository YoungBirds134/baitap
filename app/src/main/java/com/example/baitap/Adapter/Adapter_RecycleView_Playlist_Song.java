package com.example.baitap.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter_RecycleView_Playlist_Song extends RecyclerView.Adapter<Adapter_RecycleView_Playlist_Song.SongHolder> {
    String url = "https://huychimnonblog.000webhostapp.com/image/";

    ArrayList<Song> arrayList;
    Context context;
    private Adapter_RecycleView_Playlist_Song.OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;
    public Song song = new Song();

    public Adapter_RecycleView_Playlist_Song(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter_RecycleView_Playlist_Song.SongHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list, parent, false);
        return new Adapter_RecycleView_Playlist_Song.SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_RecycleView_Playlist_Song.SongHolder holder, int position) {
        Song song_playlist = arrayList.get(position);

        holder.txt_ten.setText(song_playlist.name_Song);
        Picasso.with(context).load(url + arrayList.get(position).getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_hinh);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Adapter_RecycleView_Playlist_Song.OnItemClickListener Listener) {
        this.mOnItemClickListener = Listener;
    }



    public class SongHolder extends RecyclerView.ViewHolder {

        TextView txt_ten;
        ImageView imageView_hinh;
        Button button;
        public SongHolder(@NonNull View itemView) {
            super(itemView);
           txt_ten =itemView.findViewById(R.id.item_album_view_title);
           imageView_hinh = itemView.findViewById(R.id.item_album_view_image);


            //add to fav btn

            try {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                mOnItemClickListener.onItemClick(position);
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }

        }


    }
}
