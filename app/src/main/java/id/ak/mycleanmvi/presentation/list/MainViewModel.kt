package id.ak.mycleanmvi.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.mycleanmvi.base.BaseViewModel
import id.ak.mycleanmvi.domain.intent.MovieListIntent
import id.ak.mycleanmvi.domain.state.MovieListState
import id.ak.mycleanmvi.domain.usecase.MovieListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MovieListUseCase) :
    BaseViewModel<MovieListIntent, MovieListState>() {

    override fun doUserIntent(intent: MovieListIntent) {
        when (intent) {
            is MovieListIntent.FetchMovieList -> {
                fetchMovies()
            }
        }
    }

    private fun fetchMovies() {
        useCase.movies
            .cachedIn(viewModelScope)
            .map { MovieListState.MovieListFetched(it) }
            .onEach {
                success(it)
            }
            .launchIn(viewModelScope)
    }
}