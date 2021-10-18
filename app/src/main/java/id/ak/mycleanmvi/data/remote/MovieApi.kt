package id.ak.mycleanmvi.data.remote

import id.ak.mycleanmvi.BuildConfig
import id.ak.mycleanmvi.data.remote.response.MovieDetailsResponse
import id.ak.mycleanmvi.data.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailsResponse
}