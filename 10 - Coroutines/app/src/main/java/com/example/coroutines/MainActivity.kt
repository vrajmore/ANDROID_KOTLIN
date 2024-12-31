package com.example.coroutines

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val btn2 = findViewById<Button>(R.id.button2)


        btn.setOnClickListener {

            GlobalScope.launch {
                val ntcl = getans()
                val ntcl2 = getans2()
                Log.d("TAG", "task done $ntcl")
                Log.d("TAG", "task done $ntcl2")
                Log.d("TAG", "coroutine thread ${Thread.currentThread().name} ")
                withContext(Dispatchers.Main) {     //transferring data from one worker to another using withContext
                    Toast.makeText(
                        this@MainActivity,
                        "$ntcl\n$ntcl2 coroutine thread ${Thread.currentThread().name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Log.d("TAG", "thread ${Thread.currentThread().name} ")
            Toast.makeText(
                this,
                "this  ${Thread.currentThread().name} thread is not blocked",
                Toast.LENGTH_SHORT
            ).show()
        }

        btn2.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {   // used to specify the thread on which you want to run coroutine
                val ans = getans()
                val th = Thread.currentThread().name
                withContext(Dispatchers.Main) {  // to switch the context to main/UI thread
                    Toast.makeText(this@MainActivity, "ans is received on main thread through dispatcher", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "Thread is $th", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    suspend fun getans(): String {
        delay(3000)
        return "received answer1"
    }

    suspend fun getans2(): String {
        delay(3000)
        return "received answer2"
    }
}