package id.ak.mycleanmvi.domain.intent

sealed class MovieDetailsIntent {
    data class FetchDetails(val id: Int) : MovieDetailsIntent()
}