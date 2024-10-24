package com.korugan.tvseriesapp.util.navigation

import com.korugan.tvseriesapp.presentation.screens.main.PopularViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.korugan.tvseriesapp.presentation.screens.main.MainScreen
import com.korugan.tvseriesapp.presentation.screens.details.DetailsViewModel
import com.korugan.tvseriesapp.presentation.screens.favorites.FavoritesViewModel
import com.korugan.tvseriesapp.presentation.screens.search.SearchViewModel
import com.korugan.tvseriesapp.presentation.screens.details.DetailsScreen
import com.korugan.tvseriesapp.presentation.screens.favorites.FavoritesScreen
import com.korugan.tvseriesapp.presentation.screens.search.SearchScreen
import com.korugan.tvseriesapp.presentation.screens.watchlist.WatchlistScreen
import com.korugan.tvseriesapp.presentation.screens.watchlist.WatchlistViewModel


@Composable
fun Navigation() {
    val popularViewModel: PopularViewModel = viewModel()
    val detailsViewModel: DetailsViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()

    val favoritesViewModel: FavoritesViewModel = viewModel()
    val watchlistViewModel: WatchlistViewModel = viewModel()

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Main") {
        composable(route = "Main") {
            MainScreen(navController, popularViewModel)
        }
        composable(route = "Details"+"?id={id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments!!.getString("id")
            DetailsScreen(navController,detailsViewModel,id!!,favoritesViewModel,watchlistViewModel)
        }
        composable(route = "Search") {
            SearchScreen(navController,searchViewModel)
        }
        composable(route = "Favorites") {
            FavoritesScreen(navController,detailsViewModel,favoritesViewModel)
        }
        composable(route = "WatchList") {
            WatchlistScreen(navController,detailsViewModel,watchlistViewModel)
        }
    }
}
