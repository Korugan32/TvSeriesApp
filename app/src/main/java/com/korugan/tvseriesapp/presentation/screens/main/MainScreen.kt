package com.korugan.tvseriesapp.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.navigation.NavHostController
import com.korugan.tvseriesapp.presentation.components.Header
import com.korugan.tvseriesapp.presentation.components.movieCard.MovieCard
import com.korugan.tvseriesapp.presentation.components.BottomBar
import com.korugan.tvseriesapp.presentation.screens.main.util.popularData
import com.korugan.tvseriesapp.ui.theme.LightBlue

@Composable
fun MainScreen(navController: NavHostController, popularViewModel: PopularViewModel) {
    LaunchedEffect(Unit) {
        popularViewModel.getData(1)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val popularResult = popularViewModel.popular_result.observeAsState()
        val result = popularData(popularResult)
        if (result != null) {
            Scaffold(
                Modifier
                    .fillMaxWidth(),
                topBar = {Header(navController)},
                bottomBar = { BottomBar(navController)},
                containerColor = DefaultTintColor,) { innerPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(result.tv_shows.size) { index ->
                        MovieCard(navController, result, index)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}