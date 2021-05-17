package com.example.baitap;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.baitap.R.layout.activity_search;
import static com.example.baitap.R.layout.demo;

public class Demo extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_RecycleView_Song_ThuVien adapter_recycleView_song_thuVien;
    ArrayList<Song> data= new ArrayList();
    Context context;
    String url="http://192.168.1.7:8080/MusicPlayer/getSongs.php";
    Button button_Play;
    MediaPlayer musicPlayer;
    Handler handler  = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        get_Songs();
        setContentView(demo);
      //  arrayList=initSong();
        recyclerView=findViewById(R.id.recycler_view_thuvien);
        adapter_recycleView_song_thuVien= new Adapter_RecycleView_Song_ThuVien(data,this);
        recyclerView.setAdapter(adapter_recycleView_song_thuVien);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



        musicPlayer = new MediaPlayer();
        button_Play=findViewById(R.id.button_Play);
        button_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreparePlaying();
            }
        });

    }

public  void PreparePlaying(){
        try {
            musicPlayer.setDataSource("http://192.168.1.7:8080/MusicPlayer/audio/BigCityBoi.mp3");
            musicPlayer.prepare();
            musicPlayer.start();
            Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
        }catch (Exception exception){
             Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
        }
}

    public ArrayList<Song> initSong(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for (int i = 1; i<2;i++)
        {
            arrayList.add(new Song(0+i,"Song"+i,"Image"+i,"12"+i,"Path"+i,0,"date"+i,"Genre"+i,"Album"+i,"Artist"+i));

        }
        return  arrayList;
    }

    public void get_Songs(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0; i< response.length();i++)
                {
                    try {
                        JSONObject jsonObject =response.getJSONObject(i);
                        data.add(new Song(jsonObject.getInt("iid_Song"),jsonObject.getString("name_Song"),jsonObject.getString("image_Song"),jsonObject.getString("duration"),jsonObject.getString("path"),jsonObject.getInt("like"),jsonObject.getString("date"),jsonObject.getString("name_Genre"),jsonObject.getString("name_Album"),jsonObject.getString("name_Artist")));

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }


                adapter_recycleView_song_thuVien.notifyDataSetChanged();
                for (int i=0; i < 4; i++)
                {
                    Toast.makeText(getApplicationContext(),"messeage"+data,Toast.LENGTH_LONG).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage()+"Loi",Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,  null,success,errorListener);
        requestQueue.add(jsonArrayRequest);

    }
}
