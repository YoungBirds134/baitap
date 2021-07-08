package com.example.baitap.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_ThuVien extends Fragment implements Adapter_RecycleView_Song_ThuVien.OnItemClickListener {
    TextView textView_Name_Top, textView_Artist_Top;
    RecyclerView recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    public static ArrayList<Song> arrayList = new ArrayList<>();
    String tenBaiHat_Top, tenCaSi_Top,hinhAnh_Top;
    Context context;
    public static String url = "https://huychimnonblog.000webhostapp.com/getSongs.php";
    public static String url_image = "https://huychimnonblog.000webhostapp.com/image/";
    ImageView imageView, imageView_Top;
    public static Song song = new Song();

    Button btnPlay, btnSkip_Top, btn_PlayAll;

    Uri uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuvien, container, false);

        btn_PlayAll = view.findViewById(R.id.btn_playing_all_thuvien);
        btnPlay = view.findViewById(R.id.btnPlay_Top);
        imageView_Top = view.findViewById(R.id.txt_image);
        textView_Name_Top = view.findViewById(R.id.title_Name_Top);
        textView_Artist_Top = view.findViewById(R.id.title_Artist_Top);
        btnSkip_Top = view.findViewById(R.id.btnSkip_Top);

        btnPlay = view.findViewById(R.id.btnPlay_Top);
//Top Thông Tin Bài Hát Now Playing
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tenBaiHat_Top = bundle.getString("TenBaiHat_Top");
            tenCaSi_Top = bundle.getString("TenCaSix_Top");
            hinhAnh_Top = bundle.getString(" HinhAnh_Top ");
        }
        if (tenCaSi_Top==null && tenBaiHat_Top==null && hinhAnh_Top==null){
        Toast.makeText(getContext(),"Loi load"+tenBaiHat_Top + " " + tenCaSi_Top,Toast.LENGTH_LONG).show();
        }else {
        textView_Name_Top.setText(tenBaiHat_Top);
        textView_Artist_Top.setText(tenCaSi_Top);
        Picasso.with(getContext()).load(url_image + hinhAnh_Top).placeholder(R.drawable.music_empty).into(imageView);
        }


        //Top Thông Tin Bài Hát Now Playing

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
        textView_Name_Top.setText(arrayList.get(position).getName_Song());
        textView_Artist_Top.setText(arrayList.get(position).getName_Artist());
        getContext().startActivity(i);


//


//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource(getContext(), Uri.parse(song.getPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mediaPlayer.start();
//        btnPlay.setBackgroundResource(R.drawable.ic_pause);
//
//

    }
}

