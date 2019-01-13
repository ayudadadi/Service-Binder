package com.example.mrlin.service;

import android.os.Binder;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyBinder extends Binder {
    String str = "I Love Android";
    String result = reverseWord(str);
    public int add(int a, int b){
        return a + b;
    }

    public String getTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
    private static String reverseWord(String str){
        String [] words =str.split(" ");
        StringBuffer resultBuffer = new StringBuffer();
        for(String word :words){
            StringBuffer wordBuffer = new StringBuffer(word);
            resultBuffer.append(wordBuffer.reverse()).append(" ");
        }
        return resultBuffer.toString();
    }
}
