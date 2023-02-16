package com.example.cft.di

import android.content.Context
import androidx.room.Room
import com.example.cft.data.local.AppDataBase
import com.example.cft.data.network.BinApi
import com.example.cft.domain.entity.Card
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.utils.ResultCallAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Module
    @InstallIn(SingletonComponent::class)
    object DbModule {
        //Hilt needs to know how to create an instance of NoteDatabase. For that add another method below provideDao.
        @Provides
        @Singleton
        fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
            context, AppDataBase::class.java, "card_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        //This annotation marks the method provideDao as a provider of noteDoa.
        @Provides
        @Singleton
        fun provideDao(db: AppDataBase) = db.cardDao()

        @Provides
        fun provideEntity() = Card()
    }

//    @Module
//    class RepositoryModule {
//        @Provides
//        fun providesBinRepository(binRepository: BinRepository): IBinRepository {
//            return binRepository
//        }
//    }
}