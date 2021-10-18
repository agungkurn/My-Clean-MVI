package id.ak.mycleanmvi.domain.intent

sealed class MovieListIntent {
    object FetchMovieList : MovieListIntent()
}
