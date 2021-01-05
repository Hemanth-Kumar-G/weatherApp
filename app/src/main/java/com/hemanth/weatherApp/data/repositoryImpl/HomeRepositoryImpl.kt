package com.hemanth.weatherApp.data.repositoryImpl


import com.hemanth.weatherApp.data.RepoService
import com.hemanth.weatherApp.data.model.WeatherResponse
import com.hemanth.weatherApp.data.repository.HomeRepository
import io.reactivex.Single
import retrofit2.Response


class HomeRepositoryImpl(
    private val repoService: RepoService
) : HomeRepository {

    override fun getDetails(): Single<Response<WeatherResponse>> =
        repoService.getDetails()

}