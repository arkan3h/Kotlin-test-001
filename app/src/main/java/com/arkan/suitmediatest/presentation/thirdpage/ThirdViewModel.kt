package com.arkan.suitmediatest.presentation.thirdpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arkan.suitmediatest.data.repository.UserRepository

class ThirdViewModel(
    private val repository: UserRepository,
) : ViewModel() {
    fun getUserList() = repository.getUser().cachedIn(viewModelScope)
}
