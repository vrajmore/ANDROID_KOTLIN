package com.example.permbroadcastsender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btn = findViewById<Button>(R.id.button)
        var btn2 = findViewById<Button>(R.id.button2)

        var permbroadcast = Intent("com.broadcast.perm").apply { putExtra("STATE", true) }
        btn.setOnClickListener {
            sendBroadcast(permbroadcast, "com.example.permbroadcastreceiver")
        }
        btn2.setOnClickListener {
            sendBroadcast(permbroadcast,"com.wrongexample.permbroadcastreceiver")
        }
    }
}