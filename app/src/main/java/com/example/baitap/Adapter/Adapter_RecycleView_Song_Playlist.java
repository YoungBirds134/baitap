package com.example.baitap.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Playlist;
import com.example.baitap.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter_RecycleView_Song_Playlist extends RecyclerView.Adapter<Adapter_RecycleView_Song_Playlist.SongHolder> {


    ArrayList<Playlist> arrayList;
    Context context;
    private Adapter_RecycleView_Song_Playlist.OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;

    public Adapter_RecycleView_Song_Playlist(ArrayList<Playlist> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter_RecycleView_Song_Playlist.SongHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_playlist_child, parent, false);
        return new Adapter_RecycleView_Song_Playlist.SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_RecycleView_Song_Playlist.SongHolder holder, int position) {
       Playlist song = arrayList.get(position);
holder.imageView.setImageResource(R.drawable.default_image2);
holder.textView_tiltle.setText(song.name_Playlist);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Adapter_RecycleView_Song_Playlist.OnItemClickListener Listener) {
        this.mOnItemClickListener = Listener;
    }



    public class SongHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView textView_tiltle;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
textView_tiltle=itemView.findViewById(R.id.title_Name_Top_playlist);
imageView=itemView.findViewById(R.id.txt_image_playlist);
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
