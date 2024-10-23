package com.korugan.tvseriesapp.util.database.watchlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Watchlist(
    val watchlistId : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)
