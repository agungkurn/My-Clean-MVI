package id.ak.mycleanmvi.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import id.ak.mycleanmvi.domain.model.MovieDetails
import id.ak.mycleanmvi.domain.repository.IMovieRepository
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsInteractor @Inject constructor(private val repository: IMovieRepository):MovieDetailsUseCase {
    override suspend fun getMovieDetails(id: Int): MovieDetails {
        return repository.fetchMovieDetails(id)
    }
}