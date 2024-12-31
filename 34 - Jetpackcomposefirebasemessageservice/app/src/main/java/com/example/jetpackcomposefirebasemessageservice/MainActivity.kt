package com.example.jetpackcomposefirebasemessageservice

import android.Manifest
import android.content.Context
import android.content.SharedPreferences

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.jetpackcomposefirebasemessageservice.ui.theme.JetpackcomposefirebasemessageserviceTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ComponentActivity() {
//    val database = FirebaseDatabase.getInstance()
//    val dbRef = database.getReference("usertoken")
    var token: String = ""


    fun saveRegistrationState(context: Context, isRegistered: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isRegistered", isRegistered)
        editor.apply()
    }
    fun isUserRegistered(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isRegistered", false)
    }
    fun sendtoken(name: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("usertoken")

        val userId = name // Unique key for the user
        dbRef.child(userId).setValue(token)
            .addOnSuccessListener {
                Log.d("TAG", "Data added successfully")
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Failed to add data", e)
            }
    }


    











    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    token = task.result
                    Log.d("TAGTOKEN", "FCM Token: $token")
                } else {
                    Log.e("TAGTOKEN", "Failed to fetch FCM token", task.exception)
                }
            }

        FirebaseMessaging.getInstance().subscribeToTopic("broadcast")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "Subscribed to broadcast topic!")
                } else {
                    Log.e("TAG", "Failed to subscribe to broadcast topic")
                }
            }




        setContent {
            JetpackcomposefirebasemessageserviceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(onClick = {
                            shownotification(this@MainActivity, "hey local", "hey local")
                        }) { Text(text = "notification") }
                        Spacer(modifier = Modifier.height(100.dp))
                        var name by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(text = "name") })
                        Spacer(modifier = Modifier.height(50.dp))
                        Button(onClick = {
                            if (!name.isEmpty()) {
                                sendtoken(name)
                                name = ""
                                saveRegistrationState(applicationContext,true)
                            } else {
                                Toast.makeText(this@MainActivity, "enter name", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }, enabled = !isUserRegistered(applicationContext)) { Text(text = "send-Token") }
                    }
                }
            }
        }


    }

}
