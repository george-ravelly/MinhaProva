package curso.tads.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SendMessageDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.msgTitle)
            builder.setMessage(R.string.msgDialog)
                .setPositiveButton("Sim",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(it.baseContext, R.string.msgOtimo, Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("Não",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(it.baseContext, R.string.msgCancelar, Toast.LENGTH_SHORT).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}