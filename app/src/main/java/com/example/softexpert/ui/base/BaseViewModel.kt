package com.example.softexpert.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.softexpert.models.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse

open class BaseViewModel:ViewModel() {

    private var _networkState = MutableLiveData<String>()
    val networkState: LiveData<String>
        get() = _networkState

    fun NetworkResponse<*, ErrorResponse>.handleErrors() {
        when (this) {
            is NetworkResponse.ServerError -> _networkState.postValue(this.body?.erroe.toString())
            is NetworkResponse.UnknownError -> _networkState.postValue(this.error.message)
            is NetworkResponse.NetworkError -> _networkState.postValue(this.error.message)
        }
    }
}