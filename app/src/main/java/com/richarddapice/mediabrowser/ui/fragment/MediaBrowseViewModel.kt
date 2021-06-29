package com.richarddapice.mediabrowser.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richarddapice.mediabrowser.model.MediaList
import com.richarddapice.mediabrowser.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MediaBrowseViewModel @Inject constructor(private val service: MediaRepository) : ViewModel() {

    private val _trendingMoviesState = MutableStateFlow(UiState.Success())
    private val _trendingShowsState = MutableStateFlow(UiState.Success())
    private val _trendingTodayState = MutableStateFlow(UiState.Success())
    val trendingMoviesState: StateFlow<UiState> = _trendingMoviesState
    val trendingShowsState: StateFlow<UiState> = _trendingShowsState
    val trendingTodayState: StateFlow<UiState> = _trendingTodayState

    init {
        viewModelScope.launch {
            service.getTrendingMovies()
                .catch { exception ->
                    Timber.e(exception)
                }
                .collect { mediaList ->
                    _trendingMoviesState.value = UiState.Success(mediaList)
                }
        }

        viewModelScope.launch {
            service.getTrendingShows()
                .catch { exception ->
                    Timber.e(exception)
                }
                .collect { mediaList ->
                    _trendingShowsState.value = UiState.Success(mediaList)
                }
        }

        viewModelScope.launch {
            service.getTrendingToday()
                .catch { exception ->
                    Timber.e(exception)
                }
                .collect { mediaList ->
                    _trendingTodayState.value = UiState.Success(mediaList)
                }
        }
    }
}

sealed class UiState {
    class Success(val mediaList: MediaList? = null) : UiState()
    class Error(val exception: Throwable) : UiState()
}
