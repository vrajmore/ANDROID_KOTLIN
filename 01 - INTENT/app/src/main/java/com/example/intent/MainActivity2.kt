package com.example.intent

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tv4 = findViewById<TextView>(R.id.textView4)
        val tv5 = findViewById<TextView>(R.id.textView5)

        fun run1() {
            tv5.text = "HEY"
            Handler(Looper.getMainLooper()).postDelayed({
                tv5.text = tv5.text.toString() + "\nYOU"
                Handler(Looper.getMainLooper()).postDelayed({
                    tv5.text = tv5.text.toString() + "\nARE"
                    Handler(Looper.getMainLooper()).postDelayed({
                        tv5.text = tv5.text.toString() + "\nON SECOND PAGE"
                        Handler(Looper.getMainLooper()).postDelayed({
                            tv5.text = ""
                            Handler(Looper.getMainLooper()).postDelayed({
                                run1()
                            }, 500)
                        }, 1000)
                    }, 1000)
                }, 1000)
            }, 1000)
        }
        run1()
    }
}
