package com.android.prakhar.cokestudio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play(View view)
    {

        mediaPlayer.start();

    }

    public void pause(View view)
    {

        mediaPlayer.pause();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer  = MediaPlayer.create(this, R.raw.laung);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        final SeekBar volumeController = (SeekBar) findViewById(R.id.seekBar);
        final SeekBar trackSeek  =(SeekBar) findViewById(R.id.trackSeek);

        trackSeek.setMax(mediaPlayer.getDuration());
        trackSeek.setProgress(0);

        volumeController.setMax(maxVolume);
        volumeController.setProgress(curVolume);


        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                trackSeek.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0 , 1000);

        trackSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                mediaPlayer.getCurrentPosition();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }
}
