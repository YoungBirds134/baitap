package com.example.baitap.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Main;
import com.example.baitap.DB.DB_Sqlite;
import com.example.baitap.Model.Playlist;
import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull
    String url = "https://huychimnonblog.000webhostapp.com/image/";
    Spinner spinner;
    ArrayList<Song> arrayList;
    Context context;
    private OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;
    public Song song = new Song();

    DB_Sqlite dataBase;

    public Adapter_RecycleView_Song_ThuVien(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener Listener) {
        this.mOnItemClickListener = Listener;
    }

    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list, parent, false);
        return new SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = arrayList.get(position);

        holder.textView_Name.setText(arrayList.get(position).getName_Song());
        holder.textView_Des.setText(arrayList.get(position).getName_Artist());
        Picasso.with(context).load(url + arrayList.get(position).getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_Song);


        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Activity_Main.arrayList_lovesong.size() <= 0) {
                    Activity_Main.arrayList_lovesong.add(new Song(arrayList.get(position).getId_Song(), arrayList.get(position).getName_Song(), arrayList.get(position).getImage_Song(), arrayList.get(position).getDuration(), arrayList.get(position).getPath(), arrayList.get(position).getLike(), arrayList.get(position).getDate(), arrayList.get(position).getName_Genre(), arrayList.get(position).getName_Album(), arrayList.get(position).getName_Artist()));
                    Toast.makeText(context, " Success <=0", Toast.LENGTH_SHORT).show();


                } else {
                    for (int i = 0; i < Activity_Main.arrayList_lovesong.size(); i++) {

                        if (Activity_Main.arrayList_lovesong.get(i).getId_Song() != arrayList.get(position).getId_Song()) {

                            Activity_Main.arrayList_lovesong.add(new Song(arrayList.get(position).getId_Song(), arrayList.get(position).getName_Song(), arrayList.get(position).getImage_Song(), arrayList.get(position).getDuration(), arrayList.get(position).getPath(), arrayList.get(position).getLike(), arrayList.get(position).getDate(), arrayList.get(position).getName_Genre(), arrayList.get(position).getName_Album(), arrayList.get(position).getName_Artist()));
                            Toast.makeText(context, " Success !=" + " " + "id_lovesong" + Activity_Main.arrayList_lovesong.get(i).getId_Song() + " " + " id_pos" + position, Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(context, " Fail ==", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    }
                }
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (position != RecyclerView.NO_POSITION) {
                    mOnItemClickListener.onItemClick(position);

                }
                return false;
            }
        });


        holder.btn_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Them_Playlist(position);
            }
        });
    }

    public void Dialog_Them_Playlist(int position) {
      
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_song_playlist);
        spinner = dialog.findViewById(R.id.spiner_playlist);

        dataBase = new DB_Sqlite(context, "music.sqlite", null, 1);

        dataBase.QueryData("CREATE TABLE IF NOT EXISTS playlist (id_playlist INTEGER" +
                " PRIMARY KEY AUTOINCREMENT, name_playlist TEXT);");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS \"songs\" (\n" +
                "\t\"id_song\"\tinteger,\n" +
                "\t\"name_song\"\tvarchar(20),\n" +

                "\t\"image_song\"\tvarchar(24),\n" +
                "\t\"duration\"\tTIME,\n" +


                "\t\"link\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id_song\" AUTOINCREMENT)\n" +
                ");");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS song_playlist (id_song_playlist INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_song INTEGER REFERENCES songs (id_song), id_playlist INTEGER REFERENCES playlist (id_playlist));");


        ArrayList<Playlist> data = new ArrayList<Playlist>();
        Cursor data_playlist = dataBase.GetData("Select * from playlist");

        while (data_playlist.moveToNext()) {
            int id = data_playlist.getInt(0);
            String name_playlist = data_playlist.getString(1);
            data.add(new Playlist(id, name_playlist));
        }


        ArrayAdapter<Playlist> arrayAdapter = new ArrayAdapter<Playlist>(context, R.layout.support_simple_spinner_dropdown_item, data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Playlist playlist = (Playlist) parent.getItemAtPosition(position);
                SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("id_Playlis", playlist.id_Playlist);
                editor.commit();
                Toast.makeText(context, " Bạn Đã Chọn: " + playlist.name_Playlist, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button btnTem = (Button) dialog.findViewById(R.id.btn_Them_playlist_song);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Xoa_playlist_song);
        btnTem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                int id_playlist = sharedPref.getInt("id_Playlis", 0);
                Random rd = new Random();
                int maID = rd.nextInt(555);
                //Them     Vao bang song_playlist         va bang song
             try {


                    dataBase.QueryData("INSERT INTO  songs VALUES('" + maID + "','" + arrayList.get(position).getName_Song() + "','" + arrayList.get(position).getImage_Song() + "','" + arrayList.get(position).getDuration() + "','" + arrayList.get(position).getPath() + "')");
                    Toast.makeText(context, " Đã thêm vào DB song: ", Toast.LENGTH_SHORT).show();


               } catch (Exception e) {
                 //Toast.makeText(context, " Loi thêm vào DB song: " + e, Toast.LENGTH_SHORT).show();
                   e.getMessage();
               }
                try {

                    int ma = rd.nextInt(900);
                    dataBase.QueryData("INSERT INTO song_playlist VALUES('"+ma+"','" + maID + "','" + id_playlist + "')");
                    Toast.makeText(context, " Đã thêm vào DB song_playlist: ", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.getMessage();
                }
dialog.dismiss();

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des;
        //        ToggleButton favBtn;
        Button favBtn, btn_playlist;
        ImageView imageView_Song;
        TextView number;
        Spinner spinner;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = (TextView) itemView.findViewById(R.id.item_album_view_title);
            textView_Des = (TextView) itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song = itemView.findViewById(R.id.item_album_view_image);
            favBtn = itemView.findViewById(R.id.favBtn_New);
            number = itemView.findViewById(R.id.number);
            btn_playlist = itemView.findViewById(R.id.PlaylistBtn_New);
            spinner = (Spinner) itemView.findViewById(R.id.spiner_playlist);




            try {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                mOnItemClickListener.onItemClick(position);
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }

        }


    }


}