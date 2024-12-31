package com.example.jetpackcompose_broadcastrec

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose_broadcastrec.ui.theme.JetpackcomposebroadcastrecTheme

class MainActivity : ComponentActivity(){
    var bcr: Broadcastrec? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        var checked = mutableStateOf(false)
        bcr = Broadcastrec(checked)
        Log.d("TAG", "onCreate: $checked")
        val intf = IntentFilter(Intent.ACTION_SCREEN_ON)
        intf.addAction("com.example.DUCATI")
        registerReceiver(bcr, intf)
        setContent {
            checked = checked
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        val intent = Intent("com.example.DUCATI").apply { putExtra("Engine", true) }
                        sendBroadcast(intent)
                    }
                ) {
                    Text(text = "Send Broadcast onn")
                }
                Button(
                    onClick = {
                        val intent = Intent("com.example.DUCATI").apply { putExtra("Engine", false) }
                        sendBroadcast(intent)
                    }
                ) {
                    Text(text = "Send Broadcast off")
                }
                Switch(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                    })
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bcr)
    }
}