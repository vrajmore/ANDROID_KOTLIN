package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Airplane : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action.equals(Intent.ACTION_SCREEN_ON)){
            Toast.makeText(context, "screen turned on enjoy your time", Toast.LENGTH_SHORT).show()
        }
    }
}