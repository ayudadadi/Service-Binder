package com.ayu.mediaplayer;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private Button btn_start;
    private Button btn_pause;
    private Button btn_stop;
    private Button btn_xh;
    private Button btn_pre;
    private Button btn_next;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView txt_dq;
    private TextView txt_zong;
    private Handler handler;
    private final int MSG_PROGRESS_CHANGED = 1001;

    /*private Button btn_load;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private SoundPool soundPool;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == MSG_PROGRESS_CHANGED){
                    int position = msg.arg1;
                    int total = msg.arg2;
                    txt_dq.setText(getTimeStr(position));
                    txt_zong.setText(getTimeStr(total));
                }
                return false;
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer == null||!mediaPlayer.isPlaying()){
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource("/mnt/shared/Other/cbg.mp3");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }else {
                    mediaPlayer.start();
                }
            }
        });
        btn_xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.setLooping(true);
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                        int position = mediaPlayer.getCurrentPosition();
                        int total = mediaPlayer.getDuration();
                        int progress =100*position/total;
                        seekBar.setProgress(progress);
                        Message message = new Message();
                        message.what = MSG_PROGRESS_CHANGED;
                        message.arg1 = position;
                        message.arg2 = total;
                        handler.sendMessage(message);
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer !=null){
                    int progress = seekBar.getProgress();
                    int total = mediaPlayer.getDuration();
                    int position = total*progress/100;
                    mediaPlayer.seekTo(position);
                }
            }
        });
    }

    private String getTimeStr(int position) {
        int second = position/1000;
        int min = second/60;
        second = second%60;
        StringBuffer buffer = new StringBuffer();
        if(min<10){
            buffer.append("0");
        }
        buffer.append(min);
        buffer.append(":");
        if(second<10){
            buffer.append("0");
        }
        buffer.append(second);
        return buffer.toString();
    }

    private void findViews() {
        seekBar = findViewById(R.id.seekBar);
        btn_start = findViewById(R.id.btn_start);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);
        txt_dq = findViewById(R.id.txt_dq);
        txt_zong = findViewById(R.id.txt_zong);
        btn_xh = findViewById(R.id.btn_xh);
        btn_pre = findViewById(R.id.btn_pre);
        btn_next = findViewById(R.id.btn_next);
    }
}
