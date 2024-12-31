package com.example.jetpackcompose_simpleloginpage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose_simpleloginpage.ui.theme.JetpackcomposesimpleloginpageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackcomposesimpleloginpageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding),
                        this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF7E6))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = "",
            modifier = Modifier
                .size(250.dp)
                .background(Color(0x30664343), RoundedCornerShape(50.dp))
        )
        Text(
            text = "Welcome Back",
            fontSize = 20.sp,
            style = TextStyle(fontWeight = FontWeight.ExtraBold),
            color = Color.Black
        )
        Text(text = "Login to your account", fontSize = 18.sp, color = Color.Black)
        OutlinedTextField(value = email,
            textStyle = TextStyle(color = Color.Black),
            onValueChange = {text-> email=text},
            shape = RoundedCornerShape(30.dp),
            label = { Text(text = "Email") })
        OutlinedTextField(value = password,
            textStyle = TextStyle(color = Color.Black),
            onValueChange = {text-> password=text},
            shape = RoundedCornerShape(30.dp),
            label = { Text(text = "Password") })
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
            modifier = Modifier
        ) {
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF795757)),
                onClick = { Toast.makeText(context, "Your Welcome", Toast.LENGTH_SHORT).show()}) { Text(text = "Login", fontSize = 16.sp, color = Color.White) }
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF795757)),
                onClick = { Toast.makeText(context, "You are being redirected to registration page", Toast.LENGTH_SHORT).show()}) { Text(text = "Sign up", fontSize = 16.sp, color = Color.White) }
        }
        TextButton(onClick = { Toast.makeText(context, "Ohhh damn you forgot your Password", Toast.LENGTH_SHORT).show()}) {
            Text(
                text = "Forget Password?", color = Color.Black, fontSize = 15.sp
            )
        }
        Text(text = "Or signin with", color = Color.Black)
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .clickable {
                        Toast
                            .makeText(context, "You clicked on Google", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.twitter_5969020),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .clickable {
                        Toast
                            .makeText(context, "You clicked on twitter", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.facebook_5968764),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .clickable {
                        Toast
                            .makeText(context, "You clicked on facebook", Toast.LENGTH_SHORT)
                            .show()
                    }
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackcomposesimpleloginpageTheme {
        Greeting("Android", context = LocalContext.current)
    }
}