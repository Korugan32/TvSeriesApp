package com.korugan.tvseriesapp.presentation.screens.details

import ZoomableImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.korugan.tvseriesapp.presentation.screens.favorites.FavoritesViewModel
import com.korugan.tvseriesapp.presentation.components.movieCard.util.getAverageColor
import com.korugan.tvseriesapp.presentation.components.Rating
import com.korugan.tvseriesapp.presentation.screens.details.util.detailsData
import com.korugan.tvseriesapp.presentation.screens.watchlist.WatchlistViewModel
import com.korugan.tvseriesapp.ui.theme.LightBlue

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel,
    id: String,
    favoritesViewModel: FavoritesViewModel,
    watchlistViewModel: WatchlistViewModel
) {
    LaunchedEffect(Unit) {
        detailsViewModel.getData(id)
    }
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer //
    var dominantColor by remember { mutableStateOf(defaultColor) } //
    var isFavorite by remember { mutableStateOf(false) }
    var isWatchlist by remember { mutableStateOf(false) }
    val detailsResult = detailsViewModel.details_Result.observeAsState()
    val result = detailsData(detailsResult) //


    Column(modifier = Modifier.fillMaxSize()) {
        if (result != null) {
            LaunchedEffect(Unit) {
                favoritesViewModel.isExist(id.toInt())
                isFavorite = favoritesViewModel.isFavoriteExists.value

                watchlistViewModel.isExist(id.toInt())
                isWatchlist = watchlistViewModel.isWatchlistExist.value
            }
            val imageState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(result.tvShow.image_path)
                    .size(Size.ORIGINAL)
                    .build()
            ).state
            if (imageState is AsyncImagePainter.State.Success) {
                dominantColor = getAverageColor(
                    imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                ) {
                    AsyncImage(
                        model = result.tvShow.image_path,
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .statusBarsPadding(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { navController.popBackStack() }
                        )
                        Row {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "addFavorites",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        if (isFavorite) {
                                            favoritesViewModel.deleteById(result.tvShow.id)
                                            isFavorite = !isFavorite
                                        } else {
                                            favoritesViewModel.insert(result.tvShow.id.toString())
                                            isFavorite = !isFavorite
                                        }
                                        favoritesViewModel.isExist(result.tvShow.id)
                                    }
                            )
                            Spacer(Modifier.padding(5.dp))
                            Icon(
                                imageVector = if (isWatchlist) Icons.Filled.RemoveRedEye else Icons.Outlined.RemoveRedEye,
                                contentDescription = "addWatchList",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        if (isWatchlist) {
                                            watchlistViewModel.deleteById(result.tvShow.id)
                                        } else {
                                            watchlistViewModel.insert(result.tvShow.id.toString())
                                        }
                                        watchlistViewModel.isExist(result.tvShow.id)
                                        isWatchlist = !isWatchlist
                                    }
                            )
                        }

                    }
                    AsyncImage(
                        model = result.tvShow.image_path,
                        contentDescription = "image",
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(colors = listOf(dominantColor, Color.DarkGray)))
                        .padding(10.dp)
                ) {
                    item {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(result.tvShow.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }
                    item {
                        Spacer(Modifier.padding(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Rating(result.tvShow.rating.toDouble())
                                Spacer(Modifier.padding(5.dp))
                                Text(String.format("%.1f", (result.tvShow.rating).toDouble() / 2), color = Color.White)
                            }
                            Row {
                                Text(text = "Total Ratings : ", color = Color.White)
                                Text(text = result.tvShow.rating_count, color = Color.White)
                            }
                        }
                        HorizontalDivider(thickness = 1.dp)
                    }
                    item {
                        Spacer(Modifier.padding(5.dp))
                        Text("Origin: " + result.tvShow.country, color = Color.White)
                        Text("Genres: " + result.tvShow.genres, color = Color.White)
                        Text("Network: " + result.tvShow.network, color = Color.White)
                        Text("Start Date: " + result.tvShow.start_date, color = Color.White)
                        Text("Status: " + result.tvShow.status, color = Color.White)
                        HorizontalDivider(thickness = 1.dp)
                    }
                    item {
                        Spacer(Modifier.padding(5.dp))
                        Text("Description : " + result.tvShow.description, color = Color.White)
                    }
                    item {
                        Spacer(Modifier.padding(5.dp))
                        Text("Images:", color = Color.White)
                        Spacer(Modifier.padding(5.dp))
                        LazyRow(
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            items(result.tvShow.pictures.size) { index ->
                                Spacer(Modifier.padding(5.dp))
                                ZoomableImage(result.tvShow.pictures[index])
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                    }
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = LightBlue)
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}