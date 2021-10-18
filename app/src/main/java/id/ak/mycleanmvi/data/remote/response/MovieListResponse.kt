package id.ak.mycleanmvi.data.remote.response

import com.google.gson.annotations.SerializedName
import id.ak.mycleanmvi.BuildConfig
import id.ak.mycleanmvi.base.BasePagingResponse
import id.ak.mycleanmvi.domain.model.MovieListItem

data class MovieListResponse(
    @field:SerializedName("results")
    val results: List<Item> = emptyList(),

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : BasePagingResponse() {
    data class Item(
        @field:SerializedName("overview")
        val overview: String? = null,

        @field:SerializedName("original_language")
        val originalLanguage: String? = null,

        @field:SerializedName("original_title")
        val originalTitle: String? = null,

        @field:SerializedName("video")
        val video: Boolean? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("genre_ids")
        val genreIds: List<Int?>? = null,

        @field:SerializedName("poster_path")
        val posterPath: String? = null,

        @field:SerializedName("backdrop_path")
        val backdropPath: String? = null,

        @field:SerializedName("release_date")
        val releaseDate: String? = null,

        @field:SerializedName("popularity")
        val popularity: Double? = null,

        @field:SerializedName("vote_average")
        val voteAverage: Double? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("adult")
        val adult: Boolean? = null,

        @field:SerializedName("vote_count")
        val voteCount: Int? = null
    )
}

fun List<MovieListResponse.Item>.toDomainModel() = map {
    MovieListItem(it.id ?: 0, BuildConfig.BASE_IMAGE_URL + it.posterPath.orEmpty())
}
