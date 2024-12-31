package com.example.jetpackcompose_animation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose_animation.ui.theme.JetpackcomposeanimationTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var scale = remember { Animatable(0f) }
            var rotate = remember { Animatable(0f) }
            var round = remember { Animatable(0f) }
            var animateagain by remember {
                 mutableStateOf(false)
            }
            LaunchedEffect(key1 = animateagain) {
                scale.snapTo(0f)
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = {
                            OvershootInterpolator(5f).getInterpolation(it)
                        }
                    )
                )
            }
            LaunchedEffect(key1 = animateagain) {
                rotate.snapTo(0f)
                round.snapTo(1f)
                rotate.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = {
                            OvershootInterpolator(2f).getInterpolation(it)
                        }
                    )
                )
                round.animateTo(
                    targetValue = 100f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = {
                            OvershootInterpolator(2f).getInterpolation(it)
                        }
                    )
                )
            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(scale.value)
                        .rotate(rotate.value)
                        .background(color = Color.Gray, RoundedCornerShape(round.value))
                )
                Spacer(Modifier.height(200.dp))
                Button(
                    onClick = {
                        animateagain = !animateagain
                    },
                    modifier = Modifier
                ) { Text(text = "Animation ->") }
            }
        }
    }
}
