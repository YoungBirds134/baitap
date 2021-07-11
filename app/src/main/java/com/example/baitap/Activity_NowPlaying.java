package com.example.baitap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.Fragment.Fragment_ThuVien;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class Activity_NowPlaying extends AppCompatActivity implements View.OnClickListener {


    // views declartion
    public static TextView txt_NameSong, txt_NameArtist;
    TextView tvTime, tvDuration;
    SeekBar seekBarTime, seekBarVolume;
    Button btnPlay, btnPre, btnSkip;
    ToggleButton btn_shuffle;
    public static MediaPlayer musicPlayer;
    int maBaiHat, viTriBaiHat;
    public static String tenBaiHat, tenCaSi, thoiGian, hinhAnh, link;
    ImageView imageView;
    TextView textView_Name_Top, textView_Artist_Top;
    public static boolean isShuffle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);


        Intent intent = getIntent();
        viTriBaiHat = intent.getIntExtra("Position", 0);
        maBaiHat = intent.getIntExtra("MaBaiHat", 1);
        tenBaiHat = intent.getStringExtra("TenBaiHat");
        tenCaSi = intent.getStringExtra("TenCaSi");
        thoiGian = intent.getStringExtra("ThoiGian");
        hinhAnh = intent.getStringExtra("HinhAnh");
        link = intent.getStringExtra("Link");


        // hide the actionbar
        textView_Name_Top = findViewById(R.id.title_Name_Top_playlist);
        textView_Artist_Top = findViewById(R.id.title_Artist_Top);


        btn_shuffle=findViewById(R.id.Btn_Shuffle);
        imageView = findViewById(R.id.image__Now_Playing);
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.btnPlay_Top);
        btnPre = findViewById(R.id.btnPre);
        btnSkip = findViewById(R.id.btnSkip_Top);

        txt_NameSong = findViewById(R.id.txt_nameSongPlaying);
        txt_NameSong.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Song());
        txt_NameArtist = findViewById(R.id.txt_nameArtist_Playing1);
        txt_NameArtist.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Artist());
        Picasso.with(this).load(Fragment_ThuVien.url_image + Fragment_ThuVien.arrayList.get(viTriBaiHat).getImage_Song()).placeholder(R.drawable.music_empty).into(imageView);

        btn_shuffle.setChecked(false);
        btn_shuffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)  {
                    isShuffle=true;
                } else {
                    isShuffle=false;
                }
            }
        });

        //Gửi dữ liệu đến fragment
        Bundle bundle = new Bundle();
        bundle.putString("TenBaiHat_Top",Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Song());
        bundle.putString("TenCaSi_Top",Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Artist());
        bundle.putString("HinhAnh_Top",Fragment_ThuVien.arrayList.get(viTriBaiHat).getImage_Song());
        Fragment_ThuVien fragment_thuVien = new Fragment_ThuVien();
        fragment_thuVien.setArguments(bundle);

        //Gửi dữ liệu đến fragment

//        Toast.makeText(this, "" + thoiGian, Toast.LENGTH_LONG).show();
try {
    textView_Name_Top.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Song());
    textView_Artist_Top.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Artist());
}catch (Exception e){}

        KhoiTaoMusicPlayer();
        try {
            musicPlayer.start();
            btnPlay.setBackgroundResource(R.drawable.ic_pause);
        } catch (Exception e) {

        }




//        musicPlayer = new MediaPlayer();
//        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//        try {
//            musicPlayer.setDataSource(String.valueOf(Uri.parse(Fragment_ThuVien.arrayList.get(viTriBaiHat).getPath())));
//            musicPlayer.prepareAsync();
//            musicPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    if (mp.isPlaying()) {
//                        mp.stop();
//                    }
//                    mp.start();
//                    btnPlay.setBackgroundResource(R.drawable.ic_pause);
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        musicPlayer.setLooping(true);

        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);

//        thoiGian = millisecondsToString(musicPlayer.getDuration());
        tvDuration.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getDuration());

        btnPlay.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        btnSkip.setOnClickListener(this);

        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();

            }
        });

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
            viTriBaiHat--;

            if (viTriBaiHat<0) {
                viTriBaiHat = 0;
            }
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
            }
            KhoiTaoMusicPlayer();
            musicPlayer.start();
        } else if (view.getId() == R.id.btnSkip_Top) {
            viTriBaiHat++;

            if (viTriBaiHat == Fragment_ThuVien.arrayList.size()) {
                viTriBaiHat = 0;
            }
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
            }
            KhoiTaoMusicPlayer();
            musicPlayer.start();

        }
    }

    private void KhoiTaoMusicPlayer() {

        musicPlayer = MediaPlayer.create(this, Uri.parse(Fragment_ThuVien.arrayList.get(viTriBaiHat).getPath()));
        txt_NameSong.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Song());
        tvDuration.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getDuration());
        txt_NameArtist.setText(Fragment_ThuVien.arrayList.get(viTriBaiHat).getName_Artist());
        Picasso.with(this).load(Fragment_ThuVien.url_image + Fragment_ThuVien.arrayList.get(viTriBaiHat).getImage_Song()).placeholder(R.drawable.music_empty).into(imageView);
    }
    public void nextSong(){
        Random rd = new Random();
if (isShuffle){
    viTriBaiHat=rd.nextInt(Fragment_ThuVien.arrayList.size());
}else {
    viTriBaiHat++;
}
        if (viTriBaiHat == Fragment_ThuVien.arrayList.size()) {
            viTriBaiHat = 0;
        }
        if (musicPlayer.isPlaying()) {
            musicPlayer.stop();
        }
        KhoiTaoMusicPlayer();
        musicPlayer.start();
    }


}
