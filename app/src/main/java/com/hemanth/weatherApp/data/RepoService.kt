package com.hemanth.weatherApp.data


import com.hemanth.weatherApp.data.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RepoService {

    @GET("/v2/5d3a99ed2f0000bac16ec13a")
    fun getDetails(): Single<Response<WeatherResponse>>

}