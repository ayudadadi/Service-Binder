package com.example.mrlin.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getAction()+intent.getStringExtra("weather")+intent.getIntExtra("wendu",0),Toast.LENGTH_LONG).show();
/*        Toast.makeText(context,"网络发生变化",Toast.LENGTH_LONG).show();*/
    }
}
