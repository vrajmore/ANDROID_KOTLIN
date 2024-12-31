package com.example.jetpackcomposetry

import android.content.Context
import android.os.Bundle
import android.view.RoundedCorner
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.mutableIntIntMapOf
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetry.ui.theme.JetpackcomposetryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackcomposetryTheme {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackcomposetryTheme {
        Greeting("Android", context = LocalContext.current)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, context: Context) {
    var count by remember { mutableStateOf(0) }
    var name by remember { mutableStateOf("") }
    var names by remember { mutableStateOf(listOf<String>()) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD3F1DF))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(value = name,
                textStyle = TextStyle(Color.Black),
                label = { Text(text = "hello", color = Color.Gray) },
                modifier = Modifier.focusable(true),
                shape = RoundedCornerShape(30.dp),
                onValueChange = { text ->
                    name = text
                })
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85A98F)),
                onClick = {
                    if (!name.isEmpty()) {

                        names += name
                        name = ""
                    } else {
                        Toast.makeText(context, "Text field is Empty", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Text(text = "Add", color = Color.White)
            }
            LazyColumn {
                items(names) { currentname ->
                    Text(
                        text = "$currentname",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "$count", color = Color.Black, fontSize = 40.sp
            )
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85A98F)),
                onClick = { count++ },
            ) {
                Text(
                    text = "Hello", fontSize = 20.sp, color = Color.White
                )
            }
        }
    }
}
