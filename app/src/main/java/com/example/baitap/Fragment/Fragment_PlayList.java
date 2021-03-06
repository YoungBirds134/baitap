package com.example.baitap.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.baitap.Activity_Main;
import com.example.baitap.Activity_NowPlaying;
import com.example.baitap.Activity_Song_Playlist;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_Playlist;
import com.example.baitap.Adapter.Adapter_RecycleView_Song_ThuVien;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Demo;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song;
import com.example.baitap.Model.Song_Playlist;
import com.example.baitap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class Fragment_PlayList extends Fragment {
    ArrayList<Playlist> arrayList = new ArrayList<>();
    Button button;
    Adapter_RecycleView_Song_Playlist adapter_recycleView_song_playlist;
    ListView listView;
    ImageView imageView;
  public DB_Sqlite dataBase;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        button = view.findViewById(R.id.btn_Playlist);
        listView = view.findViewById(R.id.recycler_view_playlist);
        adapter_recycleView_song_playlist = new Adapter_RecycleView_Song_Playlist(getContext(), arrayList);
        listView.setAdapter(adapter_recycleView_song_playlist);

        dataBase = new DB_Sqlite(getContext(), "music.sqlite", null, 1);

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
//??nert
        GetPlayList();

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       Playlist playlist = arrayList.get(position);


        Intent i = new Intent(getContext(), Activity_Song_Playlist.class);

        i.putExtra("MaPlayList", playlist.id_Playlist);



        getContext().startActivity(i);

    }
});
        /////Sqlite

//

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Them();
            }
        });
        Xoa_LongItem();
        return view;
    }

    public void Dialog_Them() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_playlist);

        EditText editText_ten = (EditText) dialog.findViewById(R.id.eddit_playlist);
        Button btnTem = (Button) dialog.findViewById(R.id.btn_Them_playlist);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Xoa_playlist);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnTem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_playlist = editText_ten.getText().toString();
                Random rd = new Random();
                int ma = rd.nextInt(400);
                if (name_playlist.equals("")) {
                    Toast.makeText(getContext(), "Vui L??ng Nh???p T??n Playlist", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.QueryData("INSERT INTO playlist VALUES('"+ma+"','" + name_playlist + "')");
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                    GetPlayList();
                }
            }
        });
        dialog.show();
    }

    public void GetPlayList() {
        Cursor data_playlist = dataBase.GetData("Select * from playlist");
        arrayList.clear();
        while (data_playlist.moveToNext()) {
            int id = data_playlist.getInt(0);
            String name_playlist = data_playlist.getString(1);
            arrayList.add(new Playlist(id, name_playlist));
        }
        adapter_recycleView_song_playlist.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    private void Xoa_LongItem() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("X??c Nh???n X??a Playlist");
                builder.setMessage("B???n Ch???c Ch???n mu???n x??a ?");
                builder.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dataBase.QueryData("DELETE FROM playlist  WHERE id_playlist='" + arrayList.get(position).getId_Playlist() + "'");
                        GetPlayList();
                        Toast.makeText(getContext(), "???? X??a Th??nh C??ng", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GetPlayList();

                    }
                });
                builder.show();
                return true;
            }
        });
    }


}
