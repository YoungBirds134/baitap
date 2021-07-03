package com.example.baitap;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.Fragment.Fragment_ThuVien;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Activity_NowPlaying extends AppCompatActivity implements View.OnClickListener {


    // views declartion

    TextView tvTime, tvDuration;
    SeekBar seekBarTime, seekBarVolume;
    Button btnPlay, btnPre, btnSkip;
    public static MediaPlayer musicPlayer;
    int maBaiHat;
   public static String tenBaiHat, tenCaSi, thoiGian, hinhAnh, link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);


        Intent intent = getIntent();
        maBaiHat = intent.getIntExtra("MaBaiHat", 1);
        tenBaiHat = intent.getStringExtra("TenBaiHat");
        tenCaSi = intent.getStringExtra("TenCaSi");
        thoiGian = intent.getStringExtra("ThoiGian");
        hinhAnh = intent.getStringExtra("HinhAnh");
        link = intent.getStringExtra("Link");


        // hide the actionbar
        TextView txt_NameSong, txt_NameArtist;

        ImageView imageView = findViewById(R.id.image__Now_Playing);
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.btnPlay_Top);
        btnPre = findViewById(R.id.btnPre);
        btnSkip = findViewById(R.id.btnSkip_Top);

        txt_NameSong = findViewById(R.id.txt_nameSongPlaying);
        txt_NameSong.setText(tenBaiHat);
        txt_NameArtist = findViewById(R.id.txt_nameArtist_Playing);
        txt_NameArtist.setText(tenCaSi);
        Picasso.with(this).load(Fragment_ThuVien.url_image + hinhAnh).placeholder(R.drawable.music_empty).into(imageView);

//        Toast.makeText(this, "" + thoiGian, Toast.LENGTH_LONG).show();


        musicPlayer = MediaPlayer.create(this, Uri.parse(link));
//        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            musicPlayer.setDataSource(this, Uri.parse(link));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            musicPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        musicPlayer.start();


        musicPlayer.setLooping(true);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);

//        thoiGian = millisecondsToString(musicPlayer.getDuration());
        tvDuration.setText(thoiGian);

        btnPlay.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        btnSkip.setOnClickListener(this);


        seekBarVolume.setProgress(50);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTime.setMax(musicPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
                if (isFromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    if (musicPlayer.isPlaying()) {
                        try {
                            final double current = musicPlayer.getCurrentPosition();
                            final String elapsedTime = millisecondsToString((int) current);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvTime.setText(elapsedTime);
                                    seekBarTime.setProgress((int) current);
                                }
                            });

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }).start();

    } // end main


    public String millisecondsToString(int time) {
        String elapsedTime = "";
        int minutes = time / 1000 / 60;
        int seconds = time / 1000 % 60;
        elapsedTime = minutes + ":";
        if (seconds < 10) {
            elapsedTime += "0";
        }
        elapsedTime += seconds;

        return elapsedTime;
    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.btnPlay_Top) {
            if (musicPlayer.isPlaying()) {
                // is playing
                musicPlayer.pause();
                btnPlay.setBackgroundResource(R.drawable.ic_play);
            } else {
                // on pause
                musicPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
            }
        } else if (view.getId() == R.id.btnPre) {
            Toast.makeText(Activity_NowPlaying.this, "This is a message: Pre", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.btnSkip_Top) {

            Toast.makeText(Activity_NowPlaying.this, "This is a message: Skip", Toast.LENGTH_LONG).show();
        }
    }
}
