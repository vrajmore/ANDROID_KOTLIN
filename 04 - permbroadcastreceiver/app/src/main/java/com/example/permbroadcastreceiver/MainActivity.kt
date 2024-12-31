package com.example.permbroadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val permbroadcastrec: permbroadcastrec? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var permbroadcastrec = permbroadcastrec()
        IntentFilter("com.broadcast.perm").also { registerReceiver(permbroadcastrec,it) }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(permbroadcastrec)
    }
}