package com.example.softexpert.network

import com.example.softexpert.models.CarModel
import com.example.softexpert.models.ErrorResponse
import com.example.softexpert.utils.Constants
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CarAPI {

    @GET(Constants.cars)
    suspend fun getCars(@Query("page")  pageSize:Int=1): NetworkResponse<CarModel, ErrorResponse>

    companion object {
        fun getMoshi(): Moshi {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        operator fun invoke(): CarAPI {
            return Retrofit.Builder()

                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(CarAPI::class.java)
        }

    }
}