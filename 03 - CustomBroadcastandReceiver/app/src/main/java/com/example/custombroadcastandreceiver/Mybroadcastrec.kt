package com.example.custombroadcastandreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Switch
import android.widget.Toast

class Mybroadcastrec(var swi : Switch) : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.mybroad.ducati") {
            val ducati = intent.getBooleanExtra("DUCATI", false)
            if (ducati) {
                Toast.makeText(context, "ENGINE ONNNNNNN", Toast.LENGTH_SHORT).show()
                swi.isChecked = true
            } else {
                Toast.makeText(context, "ENGINE OFFFFFF", Toast.LENGTH_SHORT).show()
                swi.isChecked = false
            }

        }
    }
}