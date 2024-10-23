package com.korugan.tvseriesapp.presentation.screens.details.util

import androidx.compose.runtime.State
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.details.data.DetailsModel

fun detailsData(detailsResult: State<NetworkResponse<DetailsModel>?>): DetailsModel? {
    val data : DetailsModel
    when (val result = detailsResult.value) {
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