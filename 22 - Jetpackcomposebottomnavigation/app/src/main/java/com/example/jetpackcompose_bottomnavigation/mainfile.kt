package com.example.jetpackcompose_bottomnavigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun mainfile(modifier: Modifier = Modifier) {
    val navitemslist = listOf(
        navitems("Home", Icons.Default.Home, 0),
        navitems("Notification", Icons.Default.Notifications, 25),
        navitems("Settings", Icons.Default.Settings, 0),
        navitems("Calls", Icons.Default.Call, 4),
        navitems("Profile", Icons.Default.Person, 1)
    )
    var selectedindex by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navitemslist.forEachIndexed { index, navitems ->
                    NavigationBarItem(
                        selected = selectedindex == index,
                        onClick = {
                            selectedindex = index
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (navitems.badgecount > 0) {
                                        Badge() { Text(text = navitems.badgecount.toString()) }
                                    }
                                }
                            ) {
                                Icon(imageVector = navitems.icon, contentDescription = "")
                            }
                        },
                        label = {
                            Text(text = navitems.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        contentscreen(modifier = Modifier.padding(innerPadding), selectedindex)
    }
}

@Composable
fun contentscreen(modifier: Modifier, index: Int) {
    when (index) {
        0 -> Home()
        1 -> Notification()
        2 -> Settings()
        3 -> Calls()
        4 -> Profile()
    }
}
