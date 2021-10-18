package id.ak.mycleanmvi.data.repository

import id.ak.mycleanmvi.base.BasePagingSource
import id.ak.mycleanmvi.data.remote.MovieApi
import id.ak.mycleanmvi.data.remote.response.MovieListResponse
import id.ak.mycleanmvi.data.remote.response.toDomainModel
import id.ak.mycleanmvi.domain.model.MovieListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListPagingSource @Inject constructor(private val api: MovieApi) :
    BasePagingSource<MovieListResponse, MovieListItem>() {

    override val firstPage: Int = 1

    override suspend fun doRequest(page: Int): MovieListResponse = api.getMovieList(page)

    override fun convertToDomainModel(response: MovieListResponse): List<MovieListItem> =
        response.results.toDomainModel()
}