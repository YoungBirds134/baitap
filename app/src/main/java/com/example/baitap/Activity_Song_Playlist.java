package com.example.baitap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.Adapter.Adapter_ListView_PlayList_Song;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_Playlist;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song_Playlist;

import java.util.ArrayList;

public class Activity_Song_Playlist extends AppCompatActivity {
    Adapter_ListView_PlayList_Song adapter_listView_playList_song;
    public static ArrayList<Song_Playlist> arrayList_song_playlist = new ArrayList<>();
    ListView lv;
    int maPlaylist;
    public static DB_Sqlite dataBase;
    Song_Playlist song_playlist = new Song_Playlist();


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_song);
        dataBase = new DB_Sqlite(this, "music.sqlite", null, 1);

        dataBase.QueryData("CREATE TABLE IF NOT EXISTS playlist (id_playlist INTEGER" +
                " PRIMARY KEY AUTOINCREMENT, name_playlist TEXT);");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS \"songs\" (\n" +
                "\t\"id_song\"\tinteger,\n" +
                "\t\"name_song\"\tvarchar(20)\t,\n" +
                "\t\"image_song\"\tvarchar(24)\t,\n" +
                "\t\"duration\"\tTIME,\n" +


                "\t\"link\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id_song\" AUTOINCREMENT)\n" +
                ");");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS song_playlist (id_song_playlist INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_song INTEGER REFERENCES songs (id_song), id_playlist INTEGER REFERENCES playlist (id_playlist));");

        Intent intent = getIntent();
        maPlaylist = intent.getIntExtra("MaPlayList", 1);


        lv = findViewById(R.id.lv_song_playlist);
        adapter_listView_playList_song = new Adapter_ListView_PlayList_Song(this, arrayList_song_playlist);

        lv.setAdapter(adapter_listView_playList_song);
        try {
            GetSongs_PlayList(maPlaylist);
        } catch (Exception e) {
            Toast.makeText(this, "Loi Load", Toast.LENGTH_SHORT).show();
        }


    }

    public void GetSongs_PlayList(int maPlaylist) {
        Cursor data_playlist = Activity_Song_Playlist.dataBase.GetData("SELECT songs.id_song,songs.name_song,songs.image_song,songs.duration,songs.link FROM song_playlist,songs,playlist\n" +
                "WHERE song_playlist.id_song=songs.id_song and playlist.id_playlist=playlist.id_playlist and playlist.id_playlist='" + maPlaylist + "'");
        arrayList_song_playlist.clear();
        while (data_playlist.moveToNext()) {
            int id_song = data_playlist.getInt(0);
            String name_song = data_playlist.getString(1);
            String image_song = data_playlist.getString(2);
            String duration = data_playlist.getString(3);
            String link = data_playlist.getString(4);
            Activity_Song_Playlist.arrayList_song_playlist.add(new Song_Playlist(id_song, name_song, image_song, duration, link));
        }
        adapter_listView_playList_song.notifyDataSetChanged();
    }

    public void Dialog_Xoa(int position) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa_song_playlist);


        Button btnTem = (Button) dialog.findViewById(R.id.btn_xoa_playlist_song);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy_playlist_song);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnTem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.QueryData("DELETE FROM songs  WHERE id_song='" + position + "'");

              //  Toast.makeText(this, "Đã Xóa Thành Công", Toast.LENGTH_SHORT).show();
                GetSongs_PlayList(maPlaylist);
                dialog.dismiss();

            }
        });
        dialog.show();
    }

}
