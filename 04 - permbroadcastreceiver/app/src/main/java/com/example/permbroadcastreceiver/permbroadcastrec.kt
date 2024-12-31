package com.example.permbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class permbroadcastrec: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.broadcast.perm"){
            var mybroad = intent.getBooleanExtra("STATE", false)
            if (mybroad){
                Toast.makeText(context, "PACKET RECEIVED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}