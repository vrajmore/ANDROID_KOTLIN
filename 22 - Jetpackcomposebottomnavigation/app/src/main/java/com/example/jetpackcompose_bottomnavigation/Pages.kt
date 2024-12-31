package com.example.jetpackcompose_bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Home(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Cyan)
    ) {
        Text(text = "HOME PAGE", fontSize = 30.sp, color = Color.Black)
    }
}

@Composable
fun Notification(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Magenta)
    ) {
        Text(text = "NOTIFICATIONS PAGE", fontSize = 30.sp, color = Color.Black)
    }
}

@Composable
fun Settings(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Text(text = "SETTINGS PAGE", fontSize = 30.sp, color = Color.Black)
    }
}

@Composable
fun Calls(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Yellow)
    ) {
        Text(text = "CALLS PAGE", fontSize = 30.sp, color = Color.Black)
    }
}

@Composable
fun Profile(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Green)
    ) {
        Text(text = "PROFILE PAGE", fontSize = 30.sp, color = Color.Black)
    }
}