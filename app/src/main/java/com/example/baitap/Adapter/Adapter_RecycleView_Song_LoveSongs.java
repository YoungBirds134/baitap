package com.example.baitap.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Activity_Main;
import com.example.baitap.Fragment.Fragment_LoveSongs;
import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter_RecycleView_Song_LoveSongs extends RecyclerView.Adapter<Adapter_RecycleView_Song_LoveSongs.SongHolder> {
    String url = "https://huychimnonblog.000webhostapp.com/image/";

    ArrayList<Song> arrayList;
    Context context;
    private Adapter_RecycleView_Song_LoveSongs.OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;
    public Song song = new Song();

    public Adapter_RecycleView_Song_LoveSongs(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list_love, parent, false);
        return new Adapter_RecycleView_Song_LoveSongs.SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SongHolder holder, int position) {
        Song song = arrayList.get(position);


        holder.textView_Name.setText(arrayList.get(position).getName_Song());
        holder.textView_Des.setText(arrayList.get(position).getName_Artist());
        Picasso.with(context).load(url + arrayList.get(position).getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_Song);

        holder.favBtn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i< Activity_Main.arrayList_lovesong.size(); i++){
//                    if (Activity_Main.arrayList_lovesong.get(i).getId_Song() == arrayList.get(position).getId_Song())
//                    {
//                        Activity_Main.arrayList_lovesong.remove(i);
//                    }
//                    Toast.makeText(context, " Remove success: " +" "+ Activity_Main.arrayList_lovesong.get(i).getName_Song(), Toast.LENGTH_SHORT).show();
//                }
                arrayList.remove(song);
                Toast.makeText(context, " Remove success: " +" "+ song.getName_Song(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener Listener) {
        this.mOnItemClickListener = Listener;
    }


    public void UpdateList(ArrayList<Song> tam){
        arrayList=tam;
        notifyDataSetChanged();
    }
    public class SongHolder extends RecyclerView.ViewHolder {

        TextView textView_Name;
        TextView textView_Des;
        Button favBtn_remove;

        ImageView imageView_Song;
        TextView number;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = (TextView) itemView.findViewById(R.id.item_album_view_title);
            textView_Des = (TextView) itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song = itemView.findViewById(R.id.item_album_view_image);

            number = itemView.findViewById(R.id.number);

favBtn_remove=itemView.findViewById(R.id.favBtn_Remove);


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
