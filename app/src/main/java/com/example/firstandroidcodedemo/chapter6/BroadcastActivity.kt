package com.example.firstandroidcodedemo.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firstandroidcodedemo.R
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : AppCompatActivity() {
    val timeListener : TimeChangeListener by lazy{
        TimeChangeListener()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        registerReceiver(timeListener, intentFilter)

        btn_send_broadcast.setOnClickListener {
            val intent = Intent("com.example.firstandroidcodedemo.chapter6.MyReceiver")
            // 必须要指定包名，这样系统才能知道是发给哪个应用，从而成为显示广播
            intent.setPackage("com.example.firstandroidcodedemo")
            // 发送标准广播，一次发给所有人，不能拦截
            sendBroadcast(intent)

        }
        btn_send_ordered_broadcast.setOnClickListener {
            val intent = Intent("com.example.firstandroidcodedemo.chapter6.MyReceiver")
            // 必须要指定包名，这样系统才能知道是发给哪个应用，从而成为显示广播
            intent.setPackage("com.example.firstandroidcodedemo")
            // 发送有序广播
            sendOrderedBroadcast(intent, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeListener)
    }

    inner class TimeChangeListener : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed!", Toast.LENGTH_SHORT).show()
        }

    }
}