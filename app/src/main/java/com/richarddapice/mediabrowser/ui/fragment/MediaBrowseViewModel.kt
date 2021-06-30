package com.richarddapice.mediabrowser.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richarddapice.mediabrowser.model.MediaList
import com.richarddapice.mediabrowser.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaBrowseViewModel @Inject constructor(private val service: MediaRepository) : ViewModel() {

    private val _trendingMoviesState = MutableStateFlow<ApiState>(ApiState.Success())
    private val _trendingShowsState = MutableStateFlow<ApiState>(ApiState.Success())
    private val _trendingTodayState = MutableStateFlow<ApiState>(ApiState.Success())
    val trendingMoviesState: StateFlow<ApiState> = _trendingMoviesState
    val trendingShowsState: StateFlow<ApiState> = _trendingShowsState
    val trendingTodayState: StateFlow<ApiState> = _trendingTodayState

    init {
        viewModelScope.launch {
            service.getTrendingMovies()
                .catch { exception ->
                    _trendingMoviesState.value = ApiState.Error(exception)
                }
                .collect { mediaList ->
                    _trendingMoviesState.value = ApiState.Success(mediaList)
                }
        }

        viewModelScope.launch {
            service.getTrendingShows()
                .catch { exception ->
                    _trendingShowsState.value = ApiState.Error(exception)
                }
                .collect { mediaList ->
                    _trendingShowsState.value = ApiState.Success(mediaList)
                }
        }

        viewModelScope.launch {
            service.getTrendingToday()
                .catch { exception ->
                    _trendingTodayState.value = ApiState.Error(exception)
                }
                .collect { mediaList ->
                    _trendingTodayState.value = ApiState.Success(mediaList)
                }
        }
    }
}

sealed class ApiState {
    class Success(val mediaList: MediaList? = null) : ApiState()
    class Error(val exception: Throwable) : ApiState()
}
