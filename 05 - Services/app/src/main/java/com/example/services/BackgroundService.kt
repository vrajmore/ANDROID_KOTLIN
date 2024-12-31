package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast

class BackgroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            countdown()
        }.start()
        Log.e("TAG", "onStartCommand:dlihsl ")
        return START_NOT_STICKY

    }

    fun countdown() {
        val handler = Handler(Looper.getMainLooper())
        for (i in 1..20) {
            handler.post({
                Toast.makeText(this, "this is your count-down --> $i", Toast.LENGTH_SHORT).show()
            })
            Log.e("TAG", "countdown: $i")
            Thread.sleep(1000)
        }
        stopSelf()
    }
}