package id.ak.mycleanmvi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.ak.mycleanmvi.data.remote.MovieApi
import id.ak.mycleanmvi.domain.model.MovieDetails
import id.ak.mycleanmvi.domain.model.MovieListItem
import id.ak.mycleanmvi.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val api: MovieApi) :
    IMovieRepository {

    override fun fetchMovieList(): Flow<PagingData<MovieListItem>> = Pager(
        PagingConfig(20),
        pagingSourceFactory = { MovieListPagingSource(api) }
    ).flow

    override suspend fun fetchMovieDetails(id: Int): MovieDetails {
        return api.getMovieDetails(id).toDomainModel()
    }
}