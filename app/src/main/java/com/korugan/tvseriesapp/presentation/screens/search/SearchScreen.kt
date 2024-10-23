package com.korugan.tvseriesapp.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.korugan.tvseriesapp.presentation.components.movieCard.MovieCard
import com.korugan.tvseriesapp.presentation.screens.search.util.searchData
import com.korugan.tvseriesapp.ui.theme.LightBlue

@Composable
fun SearchScreen(navController: NavHostController, searchViewModel: SearchViewModel) {
    var query by remember { mutableStateOf("") }
    var onSearch by remember { mutableStateOf(false) }
    val searchResult = searchViewModel.search_result.observeAsState()
    val result = searchData(searchResult)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
            .statusBarsPadding()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = "searchButton",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    searchViewModel.getData(" ", "1") // Data Sıfırlama Fonksiyonu Gelmeli, Şimdilik Kısa Çözüm
                },
                tint = Color.White
            )
            Spacer(Modifier.padding(5.dp))
            OutlinedTextField(
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "searchButton",
                        modifier = Modifier.clickable {
                            if (query != "") {
                                searchViewModel.getData(query, "1")
                                onSearch = true
                            }
                        },
                        tint = Color.White
                    )
                },
                value = query, onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(color = Color.White),
                singleLine = true,
                label = { Text(text = "Search", color = Color.White) },
            )
        }
        if (result != null) {
            onSearch = false
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(result.tv_shows.size) { index ->
                    MovieCard(navController, result, index)
                }
                item {
                    Spacer(Modifier.padding(20.dp))
                }
            }
        }
        if (onSearch) {
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