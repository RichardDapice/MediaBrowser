package com.richarddapice.mediabrowser.repository

import com.richarddapice.mediabrowser.model.MediaList
import com.richarddapice.mediabrowser.network.MediaService
import com.richarddapice.mediabrowser.util.Constants.Companion.API_REFRESH_MILLIS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MediaRepository @Inject constructor(private val service: MediaService) {
    fun getTrendingMovies(): Flow<MediaList> = flow {
        emit(service.trendingMovies())
        delay(API_REFRESH_MILLIS)
    }.flowOn(Dispatchers.IO)

    fun getTrendingShows(): Flow<MediaList> = flow {
        emit(service.trendingShows())
        delay(API_REFRESH_MILLIS)
    }.flowOn(Dispatchers.IO)

    fun getTrendingToday(): Flow<MediaList> = flow {
        emit(service.trendingToday())
        delay(API_REFRESH_MILLIS)
    }.flowOn(Dispatchers.IO)
}

