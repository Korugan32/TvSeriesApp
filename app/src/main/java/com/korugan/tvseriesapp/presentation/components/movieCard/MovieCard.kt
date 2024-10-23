package com.korugan.tvseriesapp.presentation.components.movieCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.korugan.tvseriesapp.presentation.components.movieCard.util.getAverageColor
import com.korugan.tvseriesapp.presentation.screens.details.DetailsViewModel
import com.korugan.tvseriesapp.presentation.screens.details.util.detailsData
import com.korugan.tvseriesapp.ui.theme.LightBlue
import com.korugan.tvseriesapp.util.api.details.data.DetailsModel
import com.korugan.tvseriesapp.util.api.popular.data.PopularModel
import com.korugan.tvseriesapp.util.api.search.data.SearchModel


@Composable
fun MovieCard(navController: NavHostController, data: PopularModel, index: Int) {

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.tv_shows[index].image_thumbnail_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        dominantColor
                    )
                )
            )
            .clickable { navController.navigate("Details" + "?id=${data.tv_shows[index].id}") },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                painter = imageState.painter,
                contentDescription = data.tv_shows[index].name,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = data.tv_shows[index].name,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}

@Composable
fun MovieCard(navController: NavHostController, data: SearchModel, index: Int) {

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.tv_shows[index].image_thumbnail_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        dominantColor
                    )
                )
            )
            .clickable { navController.navigate("Details" + "?id=${data.tv_shows[index].id}") },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                painter = imageState.painter,
                contentDescription = data.tv_shows[index].name,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = data.tv_shows[index].name,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}

@Composable
fun MovieCard(navController: NavHostController, data: DetailsModel) {

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data.tvShow.image_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        dominantColor
                    )
                )
            )
            .clickable { navController.navigate("Details" + "?id=${data.tvShow.id}") },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                painter = imageState.painter,
                contentDescription = data.tvShow.name,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = data.tvShow.name,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            text = data.tvShow.id.toString(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}