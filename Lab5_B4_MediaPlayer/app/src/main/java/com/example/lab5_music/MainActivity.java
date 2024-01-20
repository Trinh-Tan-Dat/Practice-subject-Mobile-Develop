package com.example.lab5_music;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements DownloadTask.DownloadListener{


    // Music Player
    private TextView tvTime, tvDuration;
    private SeekBar seekBarTime, seekBarVolume;
    private Button btnPlay, btnPause;
    private MediaPlayer mediaPlayer;
    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        MusicPlayer();
    }

    private void MusicPlayer() {
        findViewByIds_MP();
        downloadTask = new DownloadTask(MainActivity.this);
        downloadTask.execute("https://drive.google.com/uc?id=1Nws1ePp_pMSDl06SUL-BVvYeVyLzpPsT");

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://drive.google.com/uc?id=1Nws1ePp_pMSDl06SUL-BVvYeVyLzpPsT");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPause.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);

                mediaPlayer.start();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                mediaPlayer.pause();
            }
        });
    }
    private void findViewByIds_MP() {
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
    }

    @Override
    public void onDownloadComplete(InputStream inputStream) {
    }

    @Override
    public void onDownloadFailed() {
    }
}