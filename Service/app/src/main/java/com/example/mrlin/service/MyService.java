package com.example.mrlin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private boolean isServiceLive = true;
    private MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate");

        myBinder = new MyBinder();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isServiceLive){
                    Log.e("MyService", "time = " + System.currentTimeMillis());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isServiceLive = false;
        super.onDestroy();
        Log.e("MyService", "onDestroy");
    }

    }
