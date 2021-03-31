package com.example.softexpert.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.softexpert.models.CarModel
import com.example.softexpert.repository.CarRepository
import com.example.softexpert.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
     private var _carLiveData = MutableLiveData<CarModel>()
         val carLiveData: LiveData<CarModel>
             get() = _carLiveData
     
    var reposatory = CarRepository()

    init {
        getCars()
    }
    fun getCars() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = reposatory.getCars(1)
            when (response){
                is NetworkResponse.Success ->{
                    _carLiveData.postValue(response.body)
                    
                }
                else -> response.handleErrors()
            }
        }
    }
}