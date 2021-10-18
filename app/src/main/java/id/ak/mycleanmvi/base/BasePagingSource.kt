package id.ak.mycleanmvi.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<ApiModel : BasePagingResponse, DomainModel : BasePagingDomainModel> :
    PagingSource<Int, DomainModel>() {

    abstract val firstPage: Int

    override fun getRefreshKey(state: PagingState<Int, DomainModel>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainModel> {
        return try {
            val pageNumber = params.key ?: firstPage

            val response = doRequest(page = pageNumber)

            val prevKey = if (pageNumber > firstPage) pageNumber - 1 else null
            val nextKey = if (pageNumber < response.totalPages) pageNumber + 1 else null

            LoadResult.Page(
                prevKey = prevKey,
                nextKey = nextKey,
                data = convertToDomainModel(response)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun doRequest(page: Int): ApiModel

    abstract fun convertToDomainModel(response: ApiModel): List<DomainModel>

}