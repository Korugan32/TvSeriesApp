package com.korugan.tvseriesapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Rating(rating: Double) {
    var isHalfStar = (rating/2 % 1) != 0.0
    Row {
        for (i in 1..5) {
            Icon(
                imageVector =
                if (i <= rating/2) {
                    Icons.Rounded.Star
                } else {
                    if (isHalfStar) {
                        isHalfStar = false
                        Icons.Rounded.StarHalf
                    } else {
                        Icons.Rounded.StarOutline
                    }
                },
                contentDescription = "ratingStar",
                tint = Color.Yellow,
            )
        }
    }
}