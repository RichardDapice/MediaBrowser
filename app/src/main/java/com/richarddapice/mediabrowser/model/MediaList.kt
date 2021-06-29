package com.richarddapice.mediabrowser.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.collections.List

@JsonClass(generateAdapter = true)
data class MediaList(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Result>,
    @Json(name = "total_pages")val totalPages: Int,
    @Json(name = "total_results")val totalResults: Int
)