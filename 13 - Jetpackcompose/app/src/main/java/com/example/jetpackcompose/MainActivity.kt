package com.example.jetpackcompose

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.RoundedCorner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackcomposeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "WORLD", modifier = Modifier.padding(innerPadding), this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, Context: Context) {
    Column {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) {
            Text(
                text = "Hello $name!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = modifier
                    .background(Color.Gray, RoundedCornerShape(10.dp))
                    .padding(30.dp, 10.dp)
            )
            Text(text = "NOICE",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = modifier
                    .background(Color.Gray, RoundedCornerShape(10.dp))
                    .padding(30.dp, 10.dp)
                    .clickable {
                        Toast
                            .makeText(Context, "heyy google", Toast.LENGTH_SHORT)
                            .show()
                    })
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "Hello $name!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = modifier
                    .background(Color.Gray, RoundedCornerShape(10.dp))
                    .padding(30.dp, 10.dp)
            )
            Text(text = "NOICE",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = modifier
                    .background(Color.Gray, RoundedCornerShape(10.dp))
                    .padding(30.dp, 10.dp)
                    .clickable {
                        Toast
                            .makeText(Context, "heyy google", Toast.LENGTH_SHORT)
                            .show()
                    })
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) {
            items(10) { i ->
                Text(
                    text = "Hello $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = modifier
                        .background(Color.Gray, RoundedCornerShape(10.dp))
                        .padding(30.dp, 10.dp)
                )
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) {
            items(10) { i ->
                Text(
                    text = "Hello $name!",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = modifier
                        .background(Color.Gray, RoundedCornerShape(10.dp))
                        .padding(30.dp, 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val mockContext = android.content.ContextWrapper(null)
    JetpackcomposeTheme {
        Greeting("WORLD", Context = mockContext)
    }
}