package id.ak.mycleanmvi.presentation.details

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.ak.mycleanmvi.base.BaseActivity
import id.ak.mycleanmvi.databinding.ActivityMovieDetailsBinding
import id.ak.mycleanmvi.domain.intent.MovieDetailsIntent
import id.ak.mycleanmvi.domain.model.MovieDetails
import id.ak.mycleanmvi.domain.state.MovieDetailsState

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<MovieDetailsIntent, MovieDetailsState>() {
    override val binding by lazy { ActivityMovieDetailsBinding.inflate(layoutInflater) }
    override val viewModel by viewModels<MovieDetailsViewModel>()

    override fun setup() {
        intent.extras?.let {
            val id = it.getInt(EXTRA_ID, 0)
            sendIntent(MovieDetailsIntent.FetchDetails(id))
        }
    }

    override fun render(state: MovieDetailsState) {
        when (state) {
            is MovieDetailsState.DetailsFetched -> {
                onDetailsFetched(state.details)
            }
        }
    }

    private fun onDetailsFetched(details: MovieDetails) {
        with(binding) {
            Glide.with(this@MovieDetailsActivity)
                .load(details.backdropUrl)
                .into(ivBackdrop)

            Glide.with(this@MovieDetailsActivity)
                .load(details.posterUrl)
                .into(ivPoster)

            tvTitle.text = details.title
            tvYear.text = details.releaseDate

            rating.rating = details.rate.toFloat() / 2f
            tvRating.text = "${details.rate} out of ${details.totalVote} votes"

            tvOverview.text = details.overview
        }
    }

    companion object {
        private const val EXTRA_ID = "id"

        @JvmStatic
        fun go(context: Context, id: Int) {
            val i = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }
            context.startActivity(i)
        }
    }
}