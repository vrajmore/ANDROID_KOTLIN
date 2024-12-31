package com.example.custombroadcastandreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.registerReceiver
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var broadcastrec: Mybroadcastrec? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.button2)
        val btn2 = findViewById<Button>(R.id.button)
        val switch = findViewById<Switch>(R.id.switch1)

        broadcastrec = Mybroadcastrec(switch)
        val intentfil = IntentFilter("com.mybroad.ducati").also { registerReceiver(broadcastrec, it) }


        val engineon = Intent("com.mybroad.ducati").apply { putExtra("DUCATI", true) }
        val engineoff = Intent("com.mybroad.ducati").apply { putExtra("DUCATI", false) }


        btn1.setOnClickListener { sendBroadcast(engineon) }
        btn2.setOnClickListener { sendBroadcast(engineoff) }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastrec)
    }
}