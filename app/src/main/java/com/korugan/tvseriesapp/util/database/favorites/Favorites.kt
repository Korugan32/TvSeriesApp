package com.korugan.tvseriesapp.util.database.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorites(
    val favoritesId : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)
