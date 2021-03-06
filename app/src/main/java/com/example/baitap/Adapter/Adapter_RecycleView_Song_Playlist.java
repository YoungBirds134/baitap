package com.example.baitap.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Song_Playlist;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song_Playlist;
import com.example.baitap.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_RecycleView_Song_Playlist extends BaseAdapter {
    Context context;
    public static ArrayList<Playlist> arrayList;
    ViewHolder viewHolder = null;
    DB_Sqlite dataBase;

    public Adapter_RecycleView_Song_Playlist(Context context, ArrayList<Playlist> arrayList) {
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
        ImageView imageView_giohang;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_playlist_child, null);

            viewHolder.txt_ten = view.findViewById(R.id.title_Name_Top_playlist);

            viewHolder.imageView_giohang = view.findViewById(R.id.txt_image_playlist);

            Playlist playlist = (Playlist) getItem(position);
            viewHolder.txt_ten.setText(playlist.name_Playlist);
            viewHolder.imageView_giohang.setImageResource(R.drawable.default_image2);


        }
        return view;
    }


}
