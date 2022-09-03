package com.richarddapice.mediabrowser.network

import com.richarddapice.mediabrowser.model.MediaList
import retrofit2.http.GET

interface MediaService {
    @GET("trending/movie/week")
    suspend fun trendingMovies(): MediaList

    @GET("trending/tv/week")
    suspend fun trendingShows(): MediaList

    @GET("trending/all/day")
    suspend fun trendingToday(): MediaList
}

