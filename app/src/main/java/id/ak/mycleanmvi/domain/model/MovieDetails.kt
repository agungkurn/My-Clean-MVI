package id.ak.mycleanmvi.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val rate: Double,
    val totalVote: Int,
    val genres: List<String>,
    val runtime: Int,
    val productionCompanies: List<String>,
    val releaseDate: String,
    val overview: String
)
