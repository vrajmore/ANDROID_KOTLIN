package com.example.jetpackcompose_firebaseauth.page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcompose_firebaseauth.AuthState
import com.example.jetpackcompose_firebaseauth.firebaseauthviewmodel
import kotlin.reflect.typeOf


@Composable
fun Login(firebaseauthviewmodel: firebaseauthviewmodel, navController: NavController) {
    val context = LocalContext.current
    var Email by rememberSaveable { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    val authstate = firebaseauthviewmodel.authstate.observeAsState()

    LaunchedEffect(authstate.value) {
        when (authstate.value) {
            is AuthState.authenticated -> navController.navigate("Home")
//            is AuthState.error -> Toast.makeText(context, "${(authstate.value as AuthState.error).message}", Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3D564E)),
    ) {
        Text(text = "Login Page", fontSize = 32.sp)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = Email,
            onValueChange = { Email = it },
            label = { Text(text = "Email ID") },
            shape = RoundedCornerShape(17.dp)
        )
        OutlinedTextField(
            value = Password,
            onValueChange = { Password = it },
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(17.dp)
        )
        Spacer(Modifier.height(15.dp))
        Button(
            onClick = {
                firebaseauthviewmodel.login(Email, Password, context)
            }, enabled = authstate.value != AuthState.loading
        ) {
            Text(text = "Login")
        }
        TextButton(onClick = { navController.navigate("Signup") }) { Text(text = "Dont have account? Click here to Signup") }
    }
}
