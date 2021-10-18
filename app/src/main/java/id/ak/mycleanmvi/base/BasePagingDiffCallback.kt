package id.ak.mycleanmvi.base

import androidx.recyclerview.widget.DiffUtil

class BasePagingDiffCallback<DomainModel : BasePagingDomainModel> :
    DiffUtil.ItemCallback<DomainModel>() {

    override fun areItemsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean =
        oldItem==newItem
}