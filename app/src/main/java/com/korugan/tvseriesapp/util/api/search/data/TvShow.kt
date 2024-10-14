package com.korugan.tvseriesapp.util.api.search.data

data class TvShow(
    val country: String,
    val end_date: String,
    val id: Int,
    val image_thumbnail_path: String,
    val name: String,
    val network: String,
    val permalink: String,
    val start_date: String,
    val status: String
)