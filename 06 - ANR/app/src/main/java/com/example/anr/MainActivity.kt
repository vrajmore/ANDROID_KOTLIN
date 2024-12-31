package com.example.anr

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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

        val btn = findViewById<Button>(R.id.button)
        val btn1 = findViewById<Button>(R.id.button2)

        btn.setOnClickListener {
            Toast.makeText(this, "ANR STARTED FOR 10 SECONDS", Toast.LENGTH_SHORT).show()
            Thread.sleep(10000)
            Toast.makeText(this, "ANR HAS BEEN STOPPED", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener {
            Thread(Runnable {
                Log.e("TAG", "onCreate: dlkcnslvnsl")

                runOnUiThread {
                    Toast.makeText(this, "Thread has been started", Toast.LENGTH_SHORT).show()
                }
                Thread.sleep(5000)
                runOnUiThread {
                    Toast.makeText(this, "Thread has been ended", Toast.LENGTH_SHORT).show()
                }
            }).start()
        }

    }
}