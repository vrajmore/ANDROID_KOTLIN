package com.example.jetpackcompose_broadcastrec

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.compose.runtime.MutableState

class Broadcastrec(val switch: MutableState<Boolean>): BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?,) {
        if (intent?.action.equals(Intent.ACTION_SCREEN_ON)){
            Toast.makeText(context, "Screen turned onn", Toast.LENGTH_SHORT).show()
        }
        if (intent?.action == "com.example.DUCATI"){
            val engine = intent.getBooleanExtra("Engine", false)
            Log.d("TAG", "onReceive: $engine")
            if (engine){
                switch.value = true
            }else{
                switch.value = false
            }
        }
    }
}