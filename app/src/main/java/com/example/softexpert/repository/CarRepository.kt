package com.example.softexpert.repository

import com.example.softexpert.models.CarModel
import com.example.softexpert.models.ErrorResponse
import com.example.softexpert.network.CarAPI
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepository {

    suspend fun getCars(page:Int) : NetworkResponse<CarModel, ErrorResponse> =
        withContext(Dispatchers.IO){
            return@withContext CarAPI.invoke().getCars(page)
        }
}