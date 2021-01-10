package com.example.firstandroidcodedemo.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 * 通过静态注册在应用未启动时监听开机广播
 */
class BootCompleteReceiver() :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("WWS", "BootComplete")
        Toast.makeText(context, "系统开机了！", Toast.LENGTH_SHORT).show()
    }
}