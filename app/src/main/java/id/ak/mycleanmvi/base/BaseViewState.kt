package id.ak.mycleanmvi.base

sealed class BaseViewState<out SuccessState> {
    object Loading : BaseViewState<Nothing>()
    data class Failed(val throwable: Throwable) : BaseViewState<Nothing>()
    data class Success<out SuccessState>(val data: SuccessState) :
        BaseViewState<SuccessState>()
}
