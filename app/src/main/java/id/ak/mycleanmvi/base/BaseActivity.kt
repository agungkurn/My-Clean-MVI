package id.ak.mycleanmvi.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseActivity<UserIntent, SuccessState> : AppCompatActivity() {
    private val TAG_LOADING = "loading"

    /**
     * Set a ViewBinding layout
     */
    abstract val binding: ViewBinding

    /**
     * Set a BaseViewModel subclass with relevant UserIntent and SuccessState
     */
    abstract val viewModel: BaseViewModel<UserIntent, SuccessState>

    /**
     * Setup views or other behavior, get extras
     */
    abstract fun setup()

    /**
     * Render results based on retrieved state(s)
     */
    abstract fun render(state: SuccessState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        renderState()
    }

    private fun renderState() {
        viewModel.state.observe(this) {
            showLoading(it is BaseViewState.Loading)
            when (it) {
                is BaseViewState.Failed -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
                is BaseViewState.Success -> render(it.data)
            }
        }
    }

    /**
     * Send user's intent to the BaseViewModel subclass
     * @param intent: the subclass of the relevant UserIntent type
     */
    protected fun sendIntent(intent: UserIntent) {
        lifecycleScope.launch {
            viewModel.userIntent.send(intent)
        }
    }

    private fun showLoading(hasToShow: Boolean) {
        if (hasToShow) {
            LoadingIndicatorDialog.newInstance()
                .show(supportFragmentManager, TAG_LOADING)
        } else {
            (supportFragmentManager.findFragmentByTag(TAG_LOADING) as? LoadingIndicatorDialog)?.let {
                it.dismiss()
            }
        }
    }
}