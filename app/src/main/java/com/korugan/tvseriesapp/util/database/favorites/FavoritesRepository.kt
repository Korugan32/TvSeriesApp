package com.korugan.tvseriesapp.util.database.favorites

import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    val allFavorites: Flow<List<Favorites>> = favoritesDao.getAllFavorites()

    suspend fun insert(favorite: Favorites) {
        favoritesDao.insertFavorite(favorite)
    }

    suspend fun deleteById(favoriteId: Int) {
        favoritesDao.deleteFavoriteById(favoriteId)
    }

    suspend fun isFavoriteExists(favoriteId: Int) : Boolean {
        return favoritesDao.isFavoriteExists(favoriteId)
    }
}