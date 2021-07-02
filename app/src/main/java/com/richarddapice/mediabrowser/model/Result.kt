package com.richarddapice.mediabrowser.model


import android.os.Parcelable
import com.richarddapice.mediabrowser.util.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlin.collections.List

@Parcelize
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "title") var title: String = "",
    @Json(name = "name") var name: String = "",
    @Json(name = "poster_path") var posterPath: String = "",
    @Json(name = "video") var video: Boolean = false,
    @Json(name = "vote_average") var voteAverage: Float = 0f,
    @Json(name = "overview") var overview: String = "",
    @Json(name = "release_date") var releaseDate: String = "",
    @Json(name = "id") var id: Int = 0,
    @Json(name = "adult") var adult: Boolean = false,
    @Json(name = "backdrop_path") var backdropPath: String = "",
    @Json(name = "genre_ids") var genreIds: List<Int> = emptyList(),
    @Json(name = "vote_count") var voteCount: Int = 0,
    @Json(name = "original_language") var originalLanguage: String = "",
    @Json(name = "original_title") var originalTitle: String = "",
    @Json(name = "original_name") var original_name: String = "",
    @Json(name = "popularity") var popularity: Float = 0f,
    @Json(name = "media_type") var mediaType: String = "",
    @Json(name = "first_air_date") var firstAirDate: String = ""
) : Parcelable {

    val titleName: String
        get() =
            listOf(title, name).run {
                find { it.isNotBlank() } ?: Constants.EMPTY_STRING
            }

    val formattedDate: String
        get() =
            listOf(firstAirDate, releaseDate.take(4)).run {
                find { it.isNotBlank() } ?: Constants.EMPTY_STRING
            }
}

