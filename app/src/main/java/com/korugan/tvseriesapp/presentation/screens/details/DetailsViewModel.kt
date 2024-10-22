package com.korugan.tvseriesapp.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.RetrofitInstance
import com.korugan.tvseriesapp.util.api.details.data.DetailsModel
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val detailsApi = RetrofitInstance.detailsApi
    private val detailsResult = MutableLiveData<NetworkResponse<DetailsModel>>()
    val details_Result: LiveData<NetworkResponse<DetailsModel>> = detailsResult

    fun getData(q: String) {
        detailsResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = detailsApi.getDetails(q)
                if (response.isSuccessful) {
                    response.body()?.let {
                        detailsResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    detailsResult.value = NetworkResponse.Loading
                }
            } catch (e: Exception) {
                detailsResult.value = NetworkResponse.Error("Error: $e")
            }
        }
    }
}