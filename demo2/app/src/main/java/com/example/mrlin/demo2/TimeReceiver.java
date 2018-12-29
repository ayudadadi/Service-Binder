package com.example.mrlin.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReceiver extends BroadcastReceiver {
    private IOnTimeChangeListener listener;
    public TimeReceiver(IOnTimeChangeListener listener){

    }
    public void onReceive(Context context, Intent intent) {
        listener.updateTime();
    }
}
