package com.arkan.suitmediatest.presentation.firstpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isPalindrome = MutableLiveData<Boolean>()
    val isPalindrome: LiveData<Boolean> = _isPalindrome

    fun checkPalindrome(input: String) {
        val sanitizedInput = input.replace("\\s".toRegex(), "").lowercase()
        _isPalindrome.value = sanitizedInput == sanitizedInput.reversed()
    }
}
