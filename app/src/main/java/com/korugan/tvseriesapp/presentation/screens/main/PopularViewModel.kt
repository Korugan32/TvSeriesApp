package com.korugan.tvseriesapp.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.RetrofitInstance
import com.korugan.tvseriesapp.util.api.popular.data.PopularModel
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val popularApi = RetrofitInstance.popularApi
    private val popularResult = MutableLiveData<NetworkResponse<PopularModel>>()
    val popular_result: LiveData<NetworkResponse<PopularModel>> = popularResult

    fun getData(page: Int) {
        popularResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = popularApi.getPopular("1")
                if (response.isSuccessful) {
                    response.body()?.let {
                        popularResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    popularResult.value = NetworkResponse.Loading
                }
            } catch (e: Exception) {
                popularResult.value = NetworkResponse.Error("Error: $e")
            }
        }
    }
}