package id.ak.mycleanmvi.presentation.list

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ak.mycleanmvi.base.BaseActivity
import id.ak.mycleanmvi.base.BasePagingDiffCallback
import id.ak.mycleanmvi.databinding.ActivityMainBinding
import id.ak.mycleanmvi.domain.intent.MovieListIntent
import id.ak.mycleanmvi.domain.state.MovieListState
import id.ak.mycleanmvi.domain.model.MovieListItem
import id.ak.mycleanmvi.presentation.details.MovieDetailsActivity
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MovieListIntent, MovieListState>() {
    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel by viewModels<MainViewModel>()

    private val diffCallback = BasePagingDiffCallback<MovieListItem>()

    private val adapter by lazy {
        MovieListAdapter(diffCallback) {
            MovieDetailsActivity.go(this, it.id)
        }
    }

    override fun setup() {
        setupViews()
        sendIntent(MovieListIntent.FetchMovieList)
    }

    private fun setupViews() {
        with(binding) {
            rvMovie.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rvMovie.adapter = adapter

            adapter.addLoadStateListener {
                pbList.isVisible = it.source.refresh is LoadState.Loading
                btnRetry.isVisible = it.source.refresh is LoadState.Error
                rvMovie.isVisible = it.source.refresh is LoadState.NotLoading
            }
        }
    }

    override fun render(state: MovieListState) {
        when (state) {
            is MovieListState.MovieListFetched -> {
                renderList(state.items)
            }
        }
    }

    private fun renderList(items: PagingData<MovieListItem>) {
        lifecycleScope.launch {
            adapter.submitData(items)
        }
    }
}