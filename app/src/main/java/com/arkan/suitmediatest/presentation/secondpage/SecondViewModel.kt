package com.arkan.suitmediatest.presentation.secondpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel

class SecondViewModel(
    extras: Bundle?,
) : ViewModel() {
    val name = extras?.getString(SecondActivity.EXTRA_NAME)
    var selectedName: String? = null

    fun handleActivityResult(
        resultCode: Int,
        data: Intent?,
    ) {
        if (resultCode == Activity.RESULT_OK) {
            selectedName = data?.getStringExtra(SecondActivity.EXTRA_SELECTED_NAME)
        }
    }
}
