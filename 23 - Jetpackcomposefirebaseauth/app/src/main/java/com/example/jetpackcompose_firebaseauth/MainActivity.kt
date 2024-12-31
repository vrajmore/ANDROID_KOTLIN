package com.example.jetpackcompose_firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcompose_firebaseauth.page.Login
import com.example.jetpackcompose_firebaseauth.ui.theme.JetpackcomposefirebaseauthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val firebaseauthviewmodel = ViewModelProvider(this).get(firebaseauthviewmodel::class.java)
//        val firebaseauthviewmodel = firebaseauthviewmodel(this)
        setContent {
            JetpackcomposefirebaseauthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    firebaseauth(firebaseauthviewmodel)
                }
            }
        }
    }
}
