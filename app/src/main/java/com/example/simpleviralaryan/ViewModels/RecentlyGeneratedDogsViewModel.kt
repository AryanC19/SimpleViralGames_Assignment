// viewmodel/RecentlyGeneratedDogsViewModel.kt
package com.example.simpleviralaryan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleviralaryan.data.model.DogImage
import com.example.simpleviralaryan.data.repository.AppDatabase
import com.example.simpleviralaryan.data.repository.DogRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecentlyGeneratedDogsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DogRepository

    val recentDogImages: StateFlow<List<DogImage>>

    init {
        val dogImageDao = AppDatabase.getDatabase(application).dogImageDao()
        repository = DogRepository(dogImageDao)
        recentDogImages = repository.getRecentDogImages()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun clearDogs() {
        viewModelScope.launch {
            repository.clearDogImages()
        }
    }
}
