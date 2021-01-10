package com.example.firstandroidcodedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstandroidcodedemo.chapter6.BroadcastActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.open_broadcast_receiver_page).setOnClickListener {
            startActivity(Intent(this, BroadcastActivity::class.java))
        }
    }
}