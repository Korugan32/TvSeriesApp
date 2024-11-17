package com.korugan.tvseriesapp.util.api

import com.korugan.tvseriesapp.util.api.details.DetailsApi
import com.korugan.tvseriesapp.util.api.popular.PopularApi
import com.korugan.tvseriesapp.util.api.search.SearchApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val URL = "https://www.episodate.com/api/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val searchApi: SearchApi = getInstance().create(SearchApi::class.java)
    val detailsApi: DetailsApi = getInstance().create(DetailsApi::class.java)
    val popularApi: PopularApi = getInstance().create(PopularApi::class.java)
}