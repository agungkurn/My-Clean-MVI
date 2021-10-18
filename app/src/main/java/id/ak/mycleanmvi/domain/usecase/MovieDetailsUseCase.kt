package id.ak.mycleanmvi.domain.usecase

import id.ak.mycleanmvi.domain.model.MovieDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetails(id: Int): MovieDetails
}