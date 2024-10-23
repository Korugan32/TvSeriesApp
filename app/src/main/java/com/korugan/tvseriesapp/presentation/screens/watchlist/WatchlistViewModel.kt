package com.korugan.tvseriesapp.presentation.screens.watchlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.tvseriesapp.util.database.watchlist.Watchlist
import com.korugan.tvseriesapp.util.database.watchlist.WatchlistDatabase
import com.korugan.tvseriesapp.util.database.watchlist.WatchlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WatchlistViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WatchlistRepository
    val allWatchlist: StateFlow<List<Watchlist>>
    private var _isWatchlistExist = MutableStateFlow(false)
    var isWatchlistExist : StateFlow<Boolean> = _isWatchlistExist

    init {
        val watchlistDao = WatchlistDatabase.getDatabase(application).watchlistDao()
        repository = WatchlistRepository(watchlistDao)
        allWatchlist = repository.allWatchlist.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insert(watchList: String) = viewModelScope.launch(Dispatchers.IO) {
        val watchlist = Watchlist(watchlistId = watchList)
            repository.insert(watchlist)
    }

    fun deleteById(favoriteId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(favoriteId)
    }

    fun isExist(watchListId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isWatchlistExist.value = repository.isWatchlistExist(watchListId)
        }
    }
}