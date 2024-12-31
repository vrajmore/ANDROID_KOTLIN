package com.example.deviceinventory

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.GoogleApi.Settings
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

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
        val db = Firebase.firestore
        val btn = findViewById<Button>(R.id.button)
        val btn2 = findViewById<Button>(R.id.button2)
        val firstname = findViewById<EditText>(R.id.editTextText)
        val lastname = findViewById<EditText>(R.id.editTextText2)
        val serialnumber = findViewById<EditText>(R.id.editTextText3)
        val fil = findViewById<EditText>(R.id.editTextText5)
        val recy: RecyclerView = findViewById<RecyclerView>(R.id.recy)

        serialnumber.setText(
            android.provider.Settings.Secure.getString(
                contentResolver,
                android.provider.Settings.Secure.ANDROID_ID
            )
        )


        fun adddata() {
            val User = hashMapOf(
                "first" to firstname.text.toString(),
                "last" to lastname.text.toString(),
                "Device" to serialnumber.text.toString()
            )
            db.collection("Users")
                .add(User)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                    firstname.setText("")
                    lastname.setText("")
                    serialnumber.setText("")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "user adding fail", Toast.LENGTH_SHORT).show()
                }
        }

        fun retdata() {
            db.collection("Users")
                .get()
                .addOnSuccessListener { result ->
                    val itemlist = mutableListOf<Item>()
                    for (document in result) {
                        val firstName = document.getString("first") ?: "N/A"
                        val lastName = document.getString("last") ?: "N/A"
                        val device = document.getString("Device") ?: "N/A"
                        itemlist.add(
                            Item(
                                "First name:$firstName",
                                "Lastname:$lastName",
                                "Device:$device"
                            )
                        )
                        val adapter = recy.adapter as myadapter
                        adapter.updateData(itemlist)
                        Log.d(
                            "TAG",
                            "User: First Name: $firstName, Last Name: $lastName, Device: $device"
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "fail to get result", Toast.LENGTH_SHORT).show()
                }
        }
        fun retdata(fil: String) {
            db.collection("Users")
                .get()
                .addOnSuccessListener { result ->

                    val itemlist = mutableListOf<Item>()
                    for (document in result) {
                        val firstName = document.getString("first") ?: "N/A"
                        val lastName = document.getString("last") ?: "N/A"
                        val device = document.getString("Device") ?: "N/A"
                        if (fil.toString() == firstName || fil.toString() == lastName || fil.toString() == device) {
                            itemlist.add(
                                Item(
                                    "First name:$firstName",
                                    "Lastname:$lastName",
                                    "Device:$device"
                                )
                            )
                            val adapter = recy.adapter as myadapter
                            adapter.updateData(itemlist)
                            Log.d(
                                "TAG",
                                "User: First Name: $firstName, Last Name: $lastName, Device: $device"
                            )

                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "fail to get result", Toast.LENGTH_SHORT).show()
                }
        }

        val itemList = mutableListOf(
            Item("No Device", "", "")

        )
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = myadapter(itemList)
        recy.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


        retdata()
        btn.setOnClickListener {
            if (!firstname.text.isEmpty() && !lastname.text.isEmpty() && !serialnumber.text.isEmpty()) {
                adddata()
            } else {
                Toast.makeText(this, "field can not be empty", Toast.LENGTH_SHORT).show()
            }
            retdata()

        }

        btn2.setOnClickListener {
            if (fil.text.isEmpty()) {
                retdata()
            } else {
                retdata(fil.text.toString())
                fil.setText("")
            }
        }
        fil.addTextChangedListener {
            if (fil.text.isEmpty()) {
                retdata()
            } else {
                retdata(fil.text.toString())
            }
        }
    }
}