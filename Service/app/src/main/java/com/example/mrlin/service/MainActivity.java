package com.example.mrlin.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnBind;
    private ServiceConnection serviceConnection;
    private MyBinder myBinder;
    private Button btnAdd;
    private Button btnrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.start_service);
        btnStop = findViewById(R.id.stop_service);
        btnBind = findViewById(R.id.bind_service);
        btnUnBind = findViewById(R.id.unbind_service);
        btnAdd =  findViewById(R.id.btn_add);
        btnrev = findViewById(R.id.btn_reverse);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("MainActivity", "onServiceConnected");
                myBinder = (MyBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.e("MainActivity", "onServiceDisconnected");
            }

            @Override
            public void onBindingDied(ComponentName name) {
                Log.e("MainActivity", "onBindingDied");
            }
        };

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        btnUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = myBinder.getTime();
                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();

            }

        });
        btnrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = myBinder.result;
                Toast.makeText(MainActivity.this, words, Toast.LENGTH_SHORT).show();
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

    }
}

