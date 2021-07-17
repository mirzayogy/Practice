package com.mirzayogy.practice.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirzayogy.practice.model.User
import com.mirzayogy.practice.network.Api
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val response = MutableLiveData<User>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getUsers()
                response.value = listResult
            } catch (e: Exception) {
                response.value = null
            }
        }
    }
}