package com.example.baitap.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Model.Song;
import com.example.baitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_RecycleView_Song_ThuVien extends RecyclerView.Adapter<Adapter_RecycleView_Song_ThuVien.SongHolder> {
    @NonNull
    String url = "https://huychimnonblog.000webhostapp.com/image/";

    ArrayList<Song> arrayList;
    Context context;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    MediaPlayer mediaPlayer;
    public Song song = new Song();

    public Adapter_RecycleView_Song_ThuVien(ArrayList<Song> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick( View view, Song obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = (AdapterView.OnItemClickListener) mItemClickListener;
    }

    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_songs_list, parent, false);
        return new SongHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        final Song song = arrayList.get(position);

        holder.textView_Name.setText(arrayList.get(position).getName_Song());
        holder.textView_Des.setText(arrayList.get(position).getName_Artist());
        Picasso.with(context).load(url + arrayList.get(position).getImage_Song()).placeholder(R.drawable.music_empty).into(holder.imageView_Song);


     holder.getBtn_playsong().setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (mOnItemClickListener != null) {
             mOnItemClickListener.onItemClick(v,song,position);
             }
             }

     });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        MediaPlayer mediaPlayer;
        TextView textView_Name;
        TextView textView_Des;
        private Button btn_playsong;
        ToggleButton favBtn;
        ImageView imageView_Song;
        TextView number;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = (TextView) itemView.findViewById(R.id.item_album_view_title);
            textView_Des = (TextView) itemView.findViewById(R.id.item_album_view_aritst);
            imageView_Song = itemView.findViewById(R.id.item_album_view_image);
            favBtn = itemView.findViewById(R.id.favBtn);
            number = itemView.findViewById(R.id.number);
            setBtn_playsong((Button) itemView.findViewById(R.id.btnPlaysong));
            favBtn.setChecked(false);


            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();


                    PreparePlaying();
                    Toast.makeText(view.getContext(), "You clicked " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void PreparePlaying() {
            try {
                mediaPlayer.setDataSource(song.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
            } catch (Exception exception) {
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        public AdapterView<?> getBtn_playsong() {
            return btn_playsong;
        }

        public void setBtn_playsong(Button btn_playsong) {
            this.btn_playsong = btn_playsong;
        }
    }
}
