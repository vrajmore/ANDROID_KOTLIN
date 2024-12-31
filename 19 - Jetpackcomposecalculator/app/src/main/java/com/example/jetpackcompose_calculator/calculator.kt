package com.example.jetpackcompose_calculator

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

val btnlist = listOf(
    "C",
    "(",
    ")",
    "/",
    "9",
    "8",
    "7",
    "+",
    "6",
    "5",
    "4",
    "-",
    "3",
    "2",
    "1",
    "*",
    "AC",
    "0",
    ".",
    "="
)

@Composable
fun calculator(viewModel: calculatorviewmodel) {
    val datatext = viewModel.datatext.observeAsState()
    val resulttext = viewModel.resulttext.observeAsState()
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier) {
            Text(text = datatext.value ?: "", fontSize = 50.sp)
            Text(text = resulttext.value ?: "", fontSize = 70.sp)
        }
        Spacer(Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(0.dp, 0.dp)
        ) {
            items(btnlist) {
                FloatingActionButton(
                    onClick = {
                            viewModel.onbtnclick(it)
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(95.dp)
                        .padding(8.dp)
                ) { Text(text = it, fontSize = 30.sp) }
            }
        }
    }
}
