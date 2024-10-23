package com.korugan.tvseriesapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController : NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(85.dp)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Home",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate("Main" )}
        )
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorites",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate("Favorites") }
        )
        Icon(
            imageVector = Icons.Outlined.RemoveRedEye,
            contentDescription = "WatchList",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate("WatchList" )}
        )
    }
}