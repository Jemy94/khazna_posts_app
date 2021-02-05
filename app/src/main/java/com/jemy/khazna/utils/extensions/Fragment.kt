package com.jemy.khazna.utils.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.afollestad.materialdialogs.MaterialDialog
import com.jemy.khazna.R

fun Fragment.messageDialog(@StringRes res: Int? = null, message: String? = null): MaterialDialog? {
    return if (isAdded) {
        return MaterialDialog(activity!!)
            .title(R.string.dialog_title)
            .message(res, message)
            .cancelable(true)
            .cancelOnTouchOutside(true)
            .positiveButton(android.R.string.ok) { it.dismiss() }
    } else {
        null
    }
}
