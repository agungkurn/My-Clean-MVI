package id.ak.mycleanmvi.data.remote.response

import com.google.gson.annotations.SerializedName
import id.ak.mycleanmvi.BuildConfig
import id.ak.mycleanmvi.domain.model.MovieDetails

data class MovieDetailsResponse(
    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem> = emptyList(),

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem> = emptyList(),

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    data class ProductionCompaniesItem(
        @field:SerializedName("logo_path")
        val logoPath: Any? = null,

        @field:SerializedName("name")
        val name: String? = null,
    )

    data class GenresItem(
        @field:SerializedName("name")
        val name: String? = null
    )

    fun toDomainModel() = MovieDetails(
        id = id ?: 0,
        title = (title ?: originalTitle).orEmpty(),
        posterUrl = BuildConfig.BASE_IMAGE_URL + posterPath,
        backdropUrl = BuildConfig.BASE_IMAGE_URL + backdropPath,
        rate = voteAverage ?: 0.0,
        totalVote = voteCount ?: 0,
        genres = genres.map { it.name.orEmpty() },
        runtime = runtime ?: 0,
        productionCompanies = productionCompanies.map { it.name.orEmpty() },
        releaseDate = releaseDate?.take(4).orEmpty(),
        overview = overview.orEmpty()
    )
}
