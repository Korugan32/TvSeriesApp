package com.korugan.tvseriesapp.util.api.popular

import com.korugan.tvseriesapp.util.api.popular.data.PopularModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularApi {
    @GET("/most-popular")
    suspend fun getPopular(
        @Query("page") page: String,
    ): Response<PopularModel>
}