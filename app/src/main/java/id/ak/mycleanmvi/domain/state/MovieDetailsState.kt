package id.ak.mycleanmvi.domain.state

import id.ak.mycleanmvi.domain.model.MovieDetails

sealed class MovieDetailsState {
    data class DetailsFetched(val details: MovieDetails) : MovieDetailsState()
}