package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import kotlin.random.Random

class BoundService: Service() {
    val binder = LocalBinder()
    inner class LocalBinder : Binder(){
        fun getService():BoundService = this@BoundService
    }


    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this, "service bounded", Toast.LENGTH_SHORT).show()
        return binder
    }
    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "service unbounded", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }
    fun rndnumber():Int{
        return Random.nextInt(2000)
    }
}