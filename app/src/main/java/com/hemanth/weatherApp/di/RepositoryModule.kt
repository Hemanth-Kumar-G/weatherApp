package com.hemanth.weatherApp.di

import com.hemanth.weatherApp.data.RepoService
import com.hemanth.weatherApp.data.repository.HomeRepository
import com.hemanth.weatherApp.data.repositoryImpl.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    fun provideHomeRepo(repoService: RepoService): HomeRepository =
        HomeRepositoryImpl(repoService)

}