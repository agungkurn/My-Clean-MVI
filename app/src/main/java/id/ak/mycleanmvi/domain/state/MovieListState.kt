package id.ak.mycleanmvi.domain.state

import androidx.paging.PagingData
import id.ak.mycleanmvi.domain.model.MovieListItem

sealed class MovieListState {
    data class MovieListFetched(val items: PagingData<MovieListItem>) : MovieListState()
}
