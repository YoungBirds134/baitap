package com.example.baitap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_List_Songs extends ArrayAdapter<Song> {
    public Adapter_List_Songs(@NonNull Context context, @NonNull List<Song> objects) {
        super(context,0, objects);
    }
    String url="https://huychimnonblog.000webhostapp.com/image/";
    @NonNull
    @Override
    public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent) {
        Song song=getItem(position);

        converView= LayoutInflater.from(getContext()).inflate(R.layout.item_songs_list,parent,false);

        ImageView imageView=converView.findViewById(R.id.item_album_view_image);
        TextView txt_title=converView.findViewById(R.id.item_album_view_title);
        TextView txt_artist=converView.findViewById(R.id.item_album_view_aritst);

        Picasso.with(getContext()).load(url+ song.getImage_Song()).placeholder(R.drawable.music_empty).into(imageView);
txt_title.setText(song.getName_Song());
txt_artist.setText(song.getName_Artist());

        return converView;
    }
}
