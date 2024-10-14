package com.korugan.tvseriesapp.util.api.search

import com.korugan.tvseriesapp.util.api.search.data.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/search")
    suspend fun getSearch(
        @Query("q") query : String,
        @Query("page") page : String,
    ) : Response<SearchModel>
}
