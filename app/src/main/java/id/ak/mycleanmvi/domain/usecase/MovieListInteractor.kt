package id.ak.mycleanmvi.domain.usecase

import androidx.paging.PagingData
import dagger.hilt.android.scopes.ViewModelScoped
import id.ak.mycleanmvi.domain.model.MovieListItem
import id.ak.mycleanmvi.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class MovieListInteractor @Inject constructor(private val repository: IMovieRepository) :
    MovieListUseCase {

    override val movies: Flow<PagingData<MovieListItem>>
        get() = repository.fetchMovieList()
}