package com.korugan.tvseriesapp.util.api.details

import com.korugan.tvseriesapp.util.api.details.data.DetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {
    @GET("show-details")
    suspend fun getDetails(
        @Query("q") query: String,
    ): Response<DetailsModel>
}