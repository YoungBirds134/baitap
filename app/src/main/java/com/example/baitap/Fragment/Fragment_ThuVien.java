package com.example.baitap.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.baitap.Activity_NowPlaying;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class Fragment_ThuVien extends Fragment implements Adapter_RecycleView_Song_ThuVien.OnItemClickListener {

    RecyclerView recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    public static ArrayList<Song> arrayList = new ArrayList<>();

    public static String url = "https://huychimnonblog.000webhostapp.com/getSongs.php";
    public static String url_image = "https://huychimnonblog.000webhostapp.com/image/";

    public static Song song = new Song();

    Button btnPlay, btnSkip_Top, btn_PlayAll, btn_PlayRandom;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);

//ínert

        btn_PlayAll = view.findViewById(R.id.btn_playing_all_thuvien);
        btnPlay = view.findViewById(R.id.btnPlay_Top);
        btn_PlayRandom = view.findViewById(R.id.btn_playing_random_thuvien);
        btnSkip_Top = view.findViewById(R.id.btnSkip_Top);

        btnPlay = view.findViewById(R.id.btnPlay_Top);

        recyclerView = view.findViewById(R.id.recycler_view_thuvien);
        adapter_recycleView_song_thuVien = new Adapter_RecycleView_Song_ThuVien(arrayList, getContext());
        recyclerView.setAdapter(adapter_recycleView_song_thuVien);
        adapter_recycleView_song_thuVien.setOnItemClickListener(Fragment_ThuVien.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        get_Songs();


        btn_PlayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_NowPlaying.class);
                try {
                    if (Activity_NowPlaying.musicPlayer != null) {
                        Activity_NowPlaying.musicPlayer.stop();

                    }
                } catch (Exception e) {

                }

                startActivity(intent);
            }
        });

        btn_PlayRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_NowPlaying.class);
                try {
                    if (Activity_NowPlaying.musicPlayer != null) {
                        Activity_NowPlaying.musicPlayer.stop();

                    }
                } catch (Exception e) {

                }
                Random rd = new Random();

                int position = rd.nextInt(arrayList.size());
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void Dialog_Them_Playlist() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_song_playlist);

        Spinner spinner = (Spinner) dialog.findViewById(R.id.spiner_playlist);
        Button btnTem = (Button) dialog.findViewById(R.id.btn_Them_playlist_song);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Xoa_playlist_song);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

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


        Intent i = new Intent(getContext(), Activity_NowPlaying.class);
        i.putExtra("Position", position);
        i.putExtra("MaBaiHat", arrayList.get(position).getId_Song());
        i.putExtra("TenBaiHat", arrayList.get(position).getName_Song());
        i.putExtra("TenCaSi", arrayList.get(position).getName_Artist());
        i.putExtra("ThoiGian", arrayList.get(position).getDuration());
        i.putExtra("HinhAnh", arrayList.get(position).getImage_Song());
        i.putExtra("Link", arrayList.get(position).getPath());
        try {
            if (Activity_NowPlaying.musicPlayer != null) {
                Activity_NowPlaying.musicPlayer.stop();

            }
        } catch (Exception e) {
        }

        getContext().startActivity(i);




    }
}

