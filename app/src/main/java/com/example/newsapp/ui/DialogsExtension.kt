package com.example.newsapp.ui

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showMessage(
    message: String? = null,
    posActionName: String? = null,
    posAction: DialogInterface.OnClickListener? = null,
    negActionName: String? = null,
    neAction: DialogInterface.OnClickListener? = null
): AlertDialog {
    val dialogBuilder = AlertDialog.Builder(requireContext())

    dialogBuilder.setMessage(message)
    if (posActionName != null)
        dialogBuilder.setPositiveButton(posActionName, posAction)
    if (negActionName != null)
        dialogBuilder.setNeutralButton(negActionName, neAction)


    return dialogBuilder.show()
}