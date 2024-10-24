package com.korugan.tvseriesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.korugan.tvseriesapp.ui.theme.TvSeriesAppTheme
import com.korugan.tvseriesapp.util.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TvSeriesAppTheme {
                Navigation()
            }
        }
    }
}