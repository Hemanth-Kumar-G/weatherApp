package com.hemanth.weatherApp.presentation.home


import com.hemanth.weatherApp.data.model.WeatherResponse
import com.hemanth.weatherApp.presentation.home.model.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class HomeModule {

    @Provides
    @ActivityScoped
    fun provideList() = ArrayList<WeatherResponse.Data>()

    @Provides
    @ActivityScoped
    fun provideAdapter(list: ArrayList<WeatherResponse.Data>) = WeatherAdapter(list)
}