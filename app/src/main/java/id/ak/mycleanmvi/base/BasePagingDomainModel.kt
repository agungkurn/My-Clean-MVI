package id.ak.mycleanmvi.base

open class BasePagingDomainModel(open val id: Int) {

    override fun equals(other: Any?): Boolean {
        return (other as? BasePagingDomainModel)?.let {
            id == it.id
        } ?: false
    }

    override fun hashCode(): Int {
        return id
    }

}