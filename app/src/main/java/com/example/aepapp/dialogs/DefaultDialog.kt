package com.example.aepapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class DefaultDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("TEST")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}