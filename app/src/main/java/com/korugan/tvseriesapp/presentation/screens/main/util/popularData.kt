package com.korugan.tvseriesapp.presentation.screens.main.util

import androidx.compose.runtime.State
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.popular.data.PopularModel

fun popularData(popularResult: State<NetworkResponse<PopularModel>?>): PopularModel? {
    val data : PopularModel
    when (val result = popularResult.value) {
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