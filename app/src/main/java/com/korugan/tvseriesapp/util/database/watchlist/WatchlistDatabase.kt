package com.korugan.tvseriesapp.util.database.watchlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Watchlist::class], version = 1, exportSchema = false)
abstract class WatchlistDatabase : RoomDatabase() {

    abstract fun watchlistDao(): WatchlistDao

    companion object {
        @Volatile
        private var INSTANCE: WatchlistDatabase? = null

        fun getDatabase(context: Context): WatchlistDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchlistDatabase::class.java,
                    "watchlist_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}