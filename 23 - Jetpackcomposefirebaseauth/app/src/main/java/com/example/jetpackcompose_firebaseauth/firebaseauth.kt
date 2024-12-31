package com.example.jetpackcompose_firebaseauth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose_firebaseauth.page.Home
import com.example.jetpackcompose_firebaseauth.page.Login
import com.example.jetpackcompose_firebaseauth.page.Signup

@Composable
fun firebaseauth(firebaseauthviewmodel: firebaseauthviewmodel){
    val navcontroller = rememberNavController()
    NavHost(
        navController = navcontroller,
        startDestination = "Login",
        builder = {
            composable("Login"){ Login(firebaseauthviewmodel,navcontroller) }
            composable("Signup"){ Signup(firebaseauthviewmodel,navcontroller) }
            composable("Home"){ Home(firebaseauthviewmodel,navcontroller) }
        }
    )

}