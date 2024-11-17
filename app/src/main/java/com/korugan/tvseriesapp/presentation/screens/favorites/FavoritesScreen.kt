package com.korugan.tvseriesapp.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.navigation.NavHostController
import com.korugan.tvseriesapp.presentation.components.BottomBar
import com.korugan.tvseriesapp.presentation.components.movieCard.MovieCard
import com.korugan.tvseriesapp.presentation.screens.details.DetailsViewModel
import com.korugan.tvseriesapp.presentation.screens.details.util.detailsData
import com.korugan.tvseriesapp.ui.theme.LightBlue
import com.korugan.tvseriesapp.util.api.NetworkResponse

@Composable
fun FavoritesScreen(navController: NavHostController, detailsViewModel: DetailsViewModel, favoritesViewModel: FavoritesViewModel) {
    val favoritesList by favoritesViewModel.allFavorites.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (favoritesList.isNotEmpty()) {
            Scaffold(
                Modifier
                    .fillMaxWidth(),
                bottomBar = { BottomBar(navController) },
                containerColor = DefaultTintColor,
            ) { innerPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(favoritesList.size) { index ->
                        /* Burada'ki Sıkıntı DetailsModel'in her farklı id için güncellenmemesi
                       * Kısaca [21,22] benim id'lerim olsun 21 için internetten veri çektiğinde
                       * modelin içinde veriler tutuluyor fakat 22 için veri çekme işlemi yapmıyor
                       * veya yapıp detailsmodel'i yeni id için güncellemiyor.(gelen id'lerde skıntı yok,
                       * her seferinde farklı bir id geliyor.) */
                        val favoriteId = favoritesList[index].favoritesId
                        LaunchedEffect(favoriteId) {
                            detailsViewModel.getData(favoriteId)
                        }
                        val detailsResult = detailsViewModel.details_Result.observeAsState(NetworkResponse.Loading)
                        val result = detailsData(detailsResult)
                        if (result != null) {
                            MovieCard(navController, result)
                        }
                    }
                }
            }
        } else {
            Scaffold(
                bottomBar = { BottomBar(navController) },
                containerColor = DefaultTintColor,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Favorites Not Found", color = Color.White)
                }
            }
        }
    }
}