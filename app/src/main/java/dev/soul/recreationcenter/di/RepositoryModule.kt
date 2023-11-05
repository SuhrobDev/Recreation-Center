package dev.soul.recreationcenter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.soul.recreationcenter.data.remote.ApiService
import dev.soul.recreationcenter.data.repository.MainRepositoryImpl
import dev.soul.recreationcenter.domain.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(api: ApiService): MainRepository = MainRepositoryImpl(api)

}