package com.korugan.tvseriesapp.util.database.watchlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {

    @Query("SELECT * FROM Watchlist")
    fun getAllWatchlist(): Flow<List<Watchlist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(watchlist: Watchlist)

    @Query("DELETE FROM Watchlist WHERE watchlistId = :watchlistId")
    suspend fun deleteWatchlistId(watchlistId: Int)

    @Query("SELECT * FROM Watchlist WHERE watchlistId = :watchlistId")
    fun isWatchlistExist(watchlistId: Int): Boolean

}