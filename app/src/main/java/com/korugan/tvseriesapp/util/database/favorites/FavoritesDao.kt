package com.korugan.tvseriesapp.util.database.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Favorites")
    fun getAllFavorites(): Flow<List<Favorites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorites)

    @Query("DELETE FROM Favorites WHERE favoritesId = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)

    @Query("SELECT * FROM Favorites WHERE favoritesId = :favoriteId")
    fun isFavoriteExists(favoriteId: Int): Boolean

}