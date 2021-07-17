package com.mirzayogy.practice.ui.jenisbarang

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirzayogy.practice.model.Jenisbarang
import com.mirzayogy.practice.network.Api
import kotlinx.coroutines.launch

class JenisbarangViewModel : ViewModel() {
    val response = MutableLiveData<Jenisbarang>()

    fun getJenisbarang() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getJenisbarang()
                response.value = listResult
            } catch (e: Exception) {
                response.value = null
            }
        }
    }
}