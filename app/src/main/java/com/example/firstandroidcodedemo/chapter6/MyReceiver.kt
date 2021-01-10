package com.example.firstandroidcodedemo.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 自定义广播
 */
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
      Toast.makeText(context, "received MyReceiver", Toast.LENGTH_SHORT).show()
        // 拦截有序广播
        abortBroadcast()
    }
}
