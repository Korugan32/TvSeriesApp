package com.korugan.tvseriesapp.util.database.watchlist

import kotlinx.coroutines.flow.Flow

class WatchlistRepository(private val watchlistDao: WatchlistDao) {

    val allWatchlist: Flow<List<Watchlist>> = watchlistDao.getAllWatchlist()

    suspend fun insert(watchlist: Watchlist) {
        watchlistDao.insertWatchlist(watchlist)
    }

    suspend fun deleteById(watchlistId : Int) {
        watchlistDao.deleteWatchlistId(watchlistId)
    }

    suspend fun isWatchlistExist(watchlistId : Int) : Boolean {
        return watchlistDao.isWatchlistExist(watchlistId)
    }
}