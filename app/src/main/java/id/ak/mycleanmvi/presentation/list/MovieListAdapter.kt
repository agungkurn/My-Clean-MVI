package id.ak.mycleanmvi.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ak.mycleanmvi.base.BasePagingDiffCallback
import id.ak.mycleanmvi.databinding.ItemMovieBinding
import id.ak.mycleanmvi.domain.model.MovieListItem

class MovieListAdapter(
    diffCallback: BasePagingDiffCallback<MovieListItem>,
    private val onItemClicked: (MovieListItem) -> Unit
) : PagingDataAdapter<MovieListItem, MovieListAdapter.MovieListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClicked)
        }
    }

    class MovieListViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieListItem, onItemClicked: (MovieListItem) -> Unit) {
            Glide.with(itemView.context)
                .load(item.posterUrl)
                .into(binding.root)

            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}