package com.example.jetpackcompose_navigate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose_navigate.ui.theme.JetpackcomposeNavigateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navcontroller = rememberNavController()
            NavHost(navController = navcontroller, startDestination = screen.ScreenA, builder = {
                composable(screen.ScreenA) {
                    ScreenA(navcontroller)
                }
                composable(screen.ScreenB+"/{name}") {
                    val name = it.arguments?.getString("name")
                    ScreenB( name?:"no name passed")
                }
            })
        }
    }
}
