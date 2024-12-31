package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
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

        val swi = findViewById<Switch>(R.id.switch1)
        val btn = findViewById<Button>(R.id.button)
        var intentforegound = Intent(this,ForegroundServ::class.java)
        var intentbackground = Intent(this, BackgroundService::class.java)
        var swi2 = findViewById<Switch>(R.id.switch2)
        var txt = findViewById<TextView>(R.id.textView)


        swi.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                startForegroundService(intentforegound)
            } else {
                stopService(intentforegound)
            }
        }

        btn.setOnClickListener {
            startService(intentbackground)
        }


        var myService: BoundService? = null
        var isBound = false
        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                val localBinder = binder as BoundService.LocalBinder
                myService = localBinder.getService()
                isBound = true

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
                myService = null
            }
        }


        swi2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val intent = Intent(this, BoundService::class.java)
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)


            } else {
                unbindService(serviceConnection)
                isBound = false
            }
        }

        txt.setOnClickListener {
            if (isBound){
                val abc = myService?.rndnumber()
                txt.setText("RANDOM NUMBER IS $abc")
            }else{
                txt.setText("service not bound")
            }
        }

    }

}