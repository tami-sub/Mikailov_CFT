package com.example.cft.di

import com.example.cft.data.network.BinApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.utils.ResultCallAdapterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): BinApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(loggingHttp())
            .build()
        return retrofit.create(BinApi::class.java)
    }

    @Singleton
    @Provides
    fun loggingHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

//    @Module
//    class RepositoryModule {
//        @Provides
//        fun providesBinRepository(binRepository: BinRepository): IBinRepository {
//            return binRepository
//        }
//    }
}