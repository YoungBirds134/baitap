package com.example.baitap.Fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.Model.Song;
import com.example.baitap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.util.ArrayList;

public class Fragment_ThuVien extends Fragment implements Adapter_RecycleView_Song_ThuVien.OnItemClickListener {

    RecyclerView recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    ArrayList<Song> arrayList = new ArrayList<>();
    Context context;
    String url = "https://huychimnonblog.000webhostapp.com/getSongs.php";
    ImageView imageView;
    Song song = new Song();
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);

//arrayList=initSong();
        recyclerView = view.findViewById(R.id.recycler_view_thuvien);
        adapter_recycleView_song_thuVien = new Adapter_RecycleView_Song_ThuVien(arrayList, getContext());
        recyclerView.setAdapter(adapter_recycleView_song_thuVien);
        adapter_recycleView_song_thuVien.setOnItemClickListener(Fragment_ThuVien.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        get_Songs();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public ArrayList<Song> initSong() {
        ArrayList<Song> arrayList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            arrayList.add(new Song(0 + i, "Song" + i, "Image" + i, "12" + i, "Path" + i, 0, "date" + i, "Genre" + i, "Album" + i, "Artist" + i));

        }
        return arrayList;
    }

    public void get_Songs() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new Song(jsonObject.getInt("iid_Song"), jsonObject.getString("name_Song"), jsonObject.getString("image_Song"), jsonObject.getString("duration"), jsonObject.getString("path"), jsonObject.getInt("like"), jsonObject.getString("date"), jsonObject.getString("name_Genre"), jsonObject.getString("name_Album"), jsonObject.getString("name_Artist")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                adapter_recycleView_song_thuVien.notifyDataSetChanged();
                for (int i = 0; i < 1; i++) {
                    Toast.makeText(getContext(), "Load Success", Toast.LENGTH_LONG).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, success, errorListener);
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onItemClick(int position) {
        song = arrayList.get(position);
        MediaPlayer player = MediaPlayer.create(getContext(), Uri.parse(song.getPath()));
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                if (mp.isPlaying()) {
                    mp.stop();

                    mp.release();
                } else {
                    mp.start();
                }


            }
        });
        Toast.makeText(getContext(), "" + position + song.getPath(), Toast.LENGTH_LONG).show();


    }


}

