package id.ak.mycleanmvi.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import id.ak.mycleanmvi.databinding.DialogLoadingIndicatorBinding

class LoadingIndicatorDialog : DialogFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = LoadingIndicatorDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setView(DialogLoadingIndicatorBinding.inflate(layoutInflater).root)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun show(manager: FragmentManager, tag: String?) {
        isCancelable = false
        super.show(manager, tag)
    }
}