package id.ak.mycleanmvi.domain.usecase

import androidx.paging.PagingData
import id.ak.mycleanmvi.domain.model.MovieListItem
import kotlinx.coroutines.flow.Flow

interface MovieListUseCase {
    val movies: Flow<PagingData<MovieListItem>>
}