package com.example.baitap.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class Fragment_ThuVien extends Fragment {
    RecyclerView   recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    ArrayList<Song> arrayList= new ArrayList<>();
    Context context;
    String url="http://192.168.1.9:8080/MusicPlayer/index.php";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_thuvien,container,false);


        get_Songs();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        get_Songs();
        adapter_recycleView_song_thuVien= new Adapter_RecycleView_Song_ThuVien(getContext(),arrayList);
        recyclerView=view.findViewById(R.id.recycler_view_thuvien);

        recyclerView.setAdapter(adapter_recycleView_song_thuVien);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public ArrayList<Song> initSong(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for (int i = 1; i<10;i++)
        {
            arrayList.add(new Song(0+i,"Song"+i,"Image"+i,12+i,"Path"+i,0,"date"+i,"Genre"+i,"Album"+i,"Artist"+i));

        }
        return  arrayList;
    }
    public void get_Songs(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0; i< response.length();i++)
                {
                    try {
                        JSONObject jsonObject =response.getJSONObject(i);
                        arrayList.add(new Song(jsonObject.getInt("id_song"),jsonObject.getString("name_song"),jsonObject.getString("image_song"),jsonObject.getLong("duration"),jsonObject.getString("path"),jsonObject.getInt("like"),jsonObject.getString("date"),jsonObject.getString("name_genre"),jsonObject.getString("name_album"),jsonObject.getString("name_artist")));
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                adapter_recycleView_song_thuVien.notifyDataSetChanged();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, (String) null,success,errorListener);
        requestQueue.add(jsonArrayRequest);
    }
    }

