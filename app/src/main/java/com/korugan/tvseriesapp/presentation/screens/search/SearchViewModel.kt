package com.korugan.tvseriesapp.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.tvseriesapp.util.api.NetworkResponse
import com.korugan.tvseriesapp.util.api.RetrofitInstance
import com.korugan.tvseriesapp.util.api.search.data.SearchModel
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchApi = RetrofitInstance.searchApi
    private val searchResult = MutableLiveData<NetworkResponse<SearchModel>>()
    val search_result: LiveData<NetworkResponse<SearchModel>> = searchResult

    fun getData(q: String, page: String) {
        searchResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = searchApi.getSearch(q, page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        searchResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    searchResult.value = NetworkResponse.Loading
                }
            } catch (e: Exception) {
                searchResult.value = NetworkResponse.Error("Error: $e")
            }
        }
    }
}