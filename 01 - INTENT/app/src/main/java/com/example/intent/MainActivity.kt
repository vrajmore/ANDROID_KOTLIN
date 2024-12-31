package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
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
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val btn = findViewById<Button>(R.id.button)
        val intent = Intent(this, MainActivity2::class.java)

        fun run() {

            tv3.text = "Welcome"
            Handler(Looper.getMainLooper()).postDelayed({
                tv3.text = tv3.text.toString() + "\nTo"
                Handler(Looper.getMainLooper()).postDelayed({
                    tv3.text = tv3.text.toString() + "\nPage ONE"
                    Handler(Looper.getMainLooper()).postDelayed({
                        tv3.text = ""
                        Handler(Looper.getMainLooper()).postDelayed({
                            run()
                        }, 500)
                    }, 1000)
                }, 1000)
            }, 1000)

        }
        run()

        btn.setOnClickListener {
            startActivity(intent)
        }


    }
}