package com.korugan.tvseriesapp.presentation.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.tvseriesapp.util.database.favorites.Favorites
import com.korugan.tvseriesapp.util.database.favorites.FavoritesDatabase
import com.korugan.tvseriesapp.util.database.favorites.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoritesRepository
    val allFavorites: StateFlow<List<Favorites>>
    private var _isFavoriteExists = MutableStateFlow(false)
    var isFavoriteExists : StateFlow<Boolean> = _isFavoriteExists

    init {
        val favoritesDao = FavoritesDatabase.getDatabase(application).favoritesDao()
        repository = FavoritesRepository(favoritesDao)
        allFavorites = repository.allFavorites.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insert(favorite: String) = viewModelScope.launch(Dispatchers.IO) {
        val favorite = Favorites(favoritesId = favorite)
            repository.insert(favorite)
    }

    fun deleteById(favoriteId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(favoriteId)
    }

    fun isExist(favoriteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isFavoriteExists.value = repository.isFavoriteExists(favoriteId)
        }
    }



}
