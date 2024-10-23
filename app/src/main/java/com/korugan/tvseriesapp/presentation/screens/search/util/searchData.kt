package com.korugan.tvseriesapp.presentation.screens.search.util

import androidx.compose.runtime.State
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.search.data.SearchModel

fun searchData(searchResult: State<NetworkResponse<SearchModel>?>): SearchModel? {
    val data : SearchModel
    when (val result = searchResult.value) {
        is NetworkResponse.Error -> {
            return null
        }
        NetworkResponse.Loading -> {
            return null
        }
        is NetworkResponse.Success -> {
            data = result.data
        }
        null -> { return null }
    }
    return data
}