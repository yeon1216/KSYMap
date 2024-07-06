package com.example.ksymap.di

import com.example.ksymap.data.remote.api.TmapService
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(HttpRoutes.getBaseUrl())
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Provides
    fun provideTmapService(
        retrofit: Retrofit,
    ): TmapService {
        return retrofit.create(TmapService::class.java)
    }

}

object HttpRoutes {

    private const val TMAP_BASE_URL = "https://apis.openapi.sk.com/tmap"
    fun getBaseUrl(): String {
        return TMAP_BASE_URL
    }

}

