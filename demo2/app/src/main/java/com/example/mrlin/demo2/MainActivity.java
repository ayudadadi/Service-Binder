package com.example.mrlin.demo2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements IOnTimeChangeListener {
    private TextView txtTime;
    private Button b1;
    private TimeReceiver timeReceiver;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTime = findViewById(R.id.txt_time);
        receiver = new MyBroadcastReceiver();
        b1 = findViewById(R.id.b1);
        IntentFilter filter = new IntentFilter();
        filter.addAction("weather_info");
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        filter.addAction("android.net.conn.CONNNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);
        updateTime();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("weather_info");
                intent.putExtra("weather","晴天");
                intent.putExtra("wendu",25);
                sendBroadcast(intent);
            }
        });
    }

    @Override
/*    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }*/

    public void updateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        StringBuffer buffer = new StringBuffer();
        if (hour < 10) {
            buffer.append("0");
        }
        buffer.append(hour);
        buffer.append(":");
        if (min < 10) {
            buffer.append("0");
        }
        buffer.append(min);
        txtTime.setText(buffer);
    }
}
