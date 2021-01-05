package com.hemanth.weatherApp.data.repository


import com.hemanth.weatherApp.data.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Response

interface HomeRepository {

    fun getDetails(): Single<Response<WeatherResponse>>

}