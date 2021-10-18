package id.ak.mycleanmvi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel<UserIntent, SuccessState> : ViewModel() {
    val userIntent = Channel<UserIntent>()

    private val _state = MutableLiveData<BaseViewState<SuccessState>>()
    val state: LiveData<BaseViewState<SuccessState>>
        get() = _state

    abstract fun doUserIntent(intent: UserIntent)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                doUserIntent(it)
            }
        }
    }

    protected fun success(state: SuccessState) {
        _state.value = BaseViewState.Success(state)
    }

    protected fun doApiRequest(showLoading: Boolean = true, request: suspend () -> Unit) {
        viewModelScope.launch {
            if (showLoading) _state.postValue(BaseViewState.Loading)

            try {
                request()
            } catch (e: Exception) {
                _state.postValue(BaseViewState.Failed(e))
            }
        }
    }
}