package id.ak.mycleanmvi.presentation.details

import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.mycleanmvi.base.BaseViewModel
import id.ak.mycleanmvi.domain.intent.MovieDetailsIntent
import id.ak.mycleanmvi.domain.state.MovieDetailsState
import id.ak.mycleanmvi.domain.usecase.MovieDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: MovieDetailsUseCase) :
    BaseViewModel<MovieDetailsIntent, MovieDetailsState>() {

    override fun doUserIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.FetchDetails -> {
                getMovieDetails(intent.id)
            }
        }
    }

    private fun getMovieDetails(id: Int) {
        doApiRequest {
            val details = useCase.getMovieDetails(id)
            success(MovieDetailsState.DetailsFetched(details))
        }
    }
}