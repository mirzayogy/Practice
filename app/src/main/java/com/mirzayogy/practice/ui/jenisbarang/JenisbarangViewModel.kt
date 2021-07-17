package com.mirzayogy.practice.ui.jenisbarang

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirzayogy.practice.model.Jenisbarang
import com.mirzayogy.practice.model.JenisbarangData
import com.mirzayogy.practice.model.JenisbarangResponse
import com.mirzayogy.practice.network.Api
import kotlinx.coroutines.launch
import retrofit2.Response

class JenisbarangViewModel : ViewModel() {
    val response = MutableLiveData<Jenisbarang>()
    val createResponse = MutableLiveData<Response<JenisbarangResponse>>()

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

    fun create(jenisbarangData: JenisbarangData) {
        viewModelScope.launch {
            val response = Api.retrofitService.create(jenisbarangData)
            createResponse.value = response
        }
    }
}