package id.ak.mycleanmvi.base

import com.google.gson.annotations.SerializedName

open class BasePagingResponse(
    @field:SerializedName("page")
    val page: Int = 0,

    @field:SerializedName("total_pages")
    val totalPages: Int = 0
)