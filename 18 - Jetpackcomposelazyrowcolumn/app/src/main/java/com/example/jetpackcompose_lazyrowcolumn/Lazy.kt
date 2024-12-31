package com.example.jetpackcompose_lazyrowcolumn

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun lazy(context: Context) {
    var mylist = getallmarvel()
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color(0xFFA59D84))
            .padding(32.dp, 50.dp)
            .background(Color(0xFFD7D3BF))
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(mylist, itemContent = { index, item ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .clickable {
                    Toast
                        .makeText(
                            context, "${item.charname}", Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .fillMaxWidth()) {
                Image(
                    painter = painterResource(item.img),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .size(64.dp)
                        .scale(1.5f)
                )
                Spacer(Modifier.size(20.dp))
                Column {
                    Text(text = item.charname, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    Text(text = item.name, fontSize = 20.sp)
                }
            }
        })
    }
}

@Preview
@Composable
private fun prev() {
    lazy(LocalContext.current)
}


