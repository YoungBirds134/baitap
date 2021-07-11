package com.example.baitap.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Main;
import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull
    String url = "https://huychimnonblog.000webhostapp.com/image/";

    ArrayList<Song> arrayList;
    Context context;
    private OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;
    public Song song = new Song();


    public Adapter_RecycleView_Song_ThuVien(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
//        TONGGLE BUTTON
//        TONGGLE BUTTON
//        try {
//            holder.favBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    boolean checked = ((ToggleButton) v).isChecked();
//                    if (checked) {
//                        if (Activity_Main.arrayList_lovesong.size() <= 0) {
//                            Activity_Main.arrayList_lovesong.add(new Song(arrayList.get(position).getId_Song(), arrayList.get(position).getName_Song(), arrayList.get(position).getImage_Song(), arrayList.get(position).getDuration(), arrayList.get(position).getPath(), arrayList.get(position).getLike(), arrayList.get(position).getDate(), arrayList.get(position).getName_Genre(), arrayList.get(position).getName_Album(), arrayList.get(position).getName_Artist()));
//                            Toast.makeText(context, " Success <=0", Toast.LENGTH_SHORT).show();
//
//
//                        } else {
//                            for (int i = 0; i < Activity_Main.arrayList_lovesong.size(); i++) {
//                                if (Activity_Main.arrayList_lovesong.get(i).getId_Song() != arrayList.get(position).getId_Song()) {
//
//                                    Activity_Main.arrayList_lovesong.add(new Song(arrayList.get(position).getId_Song(), arrayList.get(position).getName_Song(), arrayList.get(position).getImage_Song(), arrayList.get(position).getDuration(), arrayList.get(position).getPath(), arrayList.get(position).getLike(), arrayList.get(position).getDate(), arrayList.get(position).getName_Genre(), arrayList.get(position).getName_Album(), arrayList.get(position).getName_Artist()));
//                                    Toast.makeText(context, " Success !=" + " " + "id_lovesong" + i + " " + " id_pos" + position, Toast.LENGTH_SHORT).show();
//
//
//                                } else {
//                                    Toast.makeText(context, " Fail ==", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            SharedPreferences.Editor editor = context.getSharedPreferences("save", MODE_PRIVATE).edit();
//                            editor.putBoolean("NameOfThingToSave" , checked);
//                            editor.apply();
//                        }
//
//
//                    } else {
//                        try {
//                            for (Song song : Activity_Main.arrayList_lovesong
//                            ) {
//                                if (song.getName_Song() == arrayList.get(position).getName_Song()) {
//
//                                }
//
//                            }
////                            for (int i = 0; i< Activity_Main.arrayList_lovesong.size();i++){
////                                if (Activity_Main.arrayList_lovesong.get(i).getId_Song() == arrayList.get(position).getId_Song())
////                                {
////                                    Activity_Main.arrayList_lovesong.remove(i);
////
////                                }
////                            }
//                            Activity_Main.arrayList_lovesong.remove(song);
//                            SharedPreferences.Editor editor = context.getSharedPreferences("save", MODE_PRIVATE).edit();
//                            editor.putBoolean("NameOfThingToSave" , false);
//                            editor.apply();
//
//
//
//                            Toast.makeText(context, " Success delete", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//                            Toast.makeText(context, " Fail :" + " " + e, Toast.LENGTH_SHORT).show();
//
//
//                        }
//
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.getMessage();
//        }
//          END TONGGLE BUTTon

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Activity_Main.arrayList_lovesong.size() <= 0) {
                    Activity_Main.arrayList_lovesong.add(new Song(arrayList.get(position).getId_Song(), arrayList.get(position).getName_Song(), arrayList.get(position).getImage_Song(), arrayList.get(position).getDuration(), arrayList.get(position).getPath(), arrayList.get(position).getLike(), arrayList.get(position).getDate(), arrayList.get(position).getName_Genre(), arrayList.get(position).getName_Album(), arrayList.get(position).getName_Artist()));
                    Toast.makeText(context, " Success <=0", Toast.LENGTH_SHORT).show();


                }else {
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
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des;
        //        ToggleButton favBtn;
        Button favBtn;
        ImageView imageView_Song;
        TextView number;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = (TextView) itemView.findViewById(R.id.item_album_view_title);
            textView_Des = (TextView) itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song = itemView.findViewById(R.id.item_album_view_image);
            favBtn = itemView.findViewById(R.id.favBtn_New);
            number = itemView.findViewById(R.id.number);

//            favBtn.setChecked(false);

//            SharedPreferences sharedPrefs = context.getSharedPreferences("save", MODE_PRIVATE);
//            favBtn.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", false));

            //add to fav btn

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
