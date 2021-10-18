package id.ak.mycleanmvi.domain.model

import id.ak.mycleanmvi.base.BasePagingDomainModel

data class MovieListItem(
    override val id: Int,
    val posterUrl: String
) : BasePagingDomainModel(id)
