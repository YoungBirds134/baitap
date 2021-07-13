package com.example.baitap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitap.Activity_Song_Playlist;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song;
import com.example.baitap.Model.Song_Playlist;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ListView_PlayList_Song extends BaseAdapter {
    Activity_Song_Playlist context;

    ArrayList<Song> arrayList;
    Adapter_ListView_PlayList_Song.ViewHolder viewHolder = null;
    String url = "https://huychimnonblog.000webhostapp.com/image/";

    public Adapter_ListView_PlayList_Song(Activity_Song_Playlist context, ArrayList<Song> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txt_ten;
        ImageView imageView_hinh;
        Button button;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_songs_list_love, null);

            viewHolder.txt_ten = view.findViewById(R.id.item_album_view_title);
            viewHolder.imageView_hinh = view.findViewById(R.id.item_album_view_image);
            Song song_playlist = (Song) getItem(position);
            viewHolder.txt_ten.setText(song_playlist.name_Song);
            Picasso.with(context).load(url + arrayList.get(position).getImage_Song()).placeholder(R.drawable.music_empty).into(viewHolder.imageView_hinh);
            viewHolder.button = view.findViewById(R.id.favBtn_Remove);
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.Dialog_Xoa(song_playlist.getId_Song());
                }
            });

        }
        return view;
    }


}