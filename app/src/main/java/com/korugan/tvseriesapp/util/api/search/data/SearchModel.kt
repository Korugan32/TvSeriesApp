package com.korugan.tvseriesapp.util.api.search.data

data class SearchModel(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShow>
)