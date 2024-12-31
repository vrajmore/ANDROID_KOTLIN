package com.example.jetpackcompose_state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent


@Composable
fun State(){
    var name1 by remember { mutableStateOf("") }    // value will be removed if the orientation changes
    var name by rememberSaveable { mutableStateOf("") }     // value will no be removed if orientation changes(saved each time)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyText(name)
        Myedittext(name, onchange = {name = it})
    }
}

@Composable
fun MyText(name:String){
    Text(text = "HELLO - $name",
        fontSize = 30.sp)
}

@Composable
fun Myedittext(name:String,onchange:(String)->Unit){
    OutlinedTextField(
        value = name,
        onValueChange = { onchange(it)},
        label = { Text(text = "Name") },
        shape = RoundedCornerShape(30.dp)
    )
}