package com.example.jetpackcompose_firebaseauth.page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcompose_firebaseauth.AuthState
import com.example.jetpackcompose_firebaseauth.firebaseauthviewmodel


@Composable
fun Home(firebaseauthviewmodel: firebaseauthviewmodel,navController: NavController){

    val authstate = firebaseauthviewmodel.authstate.observeAsState()

    LaunchedEffect(authstate.value) {
        when(authstate.value){
            is AuthState.unauthenticated -> navController.navigate("Login")
            else -> Unit
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF3D564E)),
    ) {
        Text(text = "Home Page", fontSize = 32.sp)
        Spacer(Modifier.height(100.dp))
        Button(onClick = {
            firebaseauthviewmodel.signout()
        }) { Text(text = "Signout") }
    }
}