package com.korugan.tvseriesapp.util.api.popular.data

data class PopularModel(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShow>
)