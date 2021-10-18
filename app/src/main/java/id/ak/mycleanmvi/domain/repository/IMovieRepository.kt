package id.ak.mycleanmvi.domain.repository

import androidx.paging.PagingData
import id.ak.mycleanmvi.domain.model.MovieDetails
import id.ak.mycleanmvi.domain.model.MovieListItem
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun fetchMovieList(): Flow<PagingData<MovieListItem>>
    suspend fun fetchMovieDetails(id: Int): MovieDetails
}