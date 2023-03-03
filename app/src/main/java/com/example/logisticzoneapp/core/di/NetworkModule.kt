package com.example.logisticzoneapp.core.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.logisticzoneapp.core.data.api.MovexApiService
import com.example.logisticzoneapp.core.exception.ResultCallAdapterFactory
import com.example.logisticzoneapp.core.util.BasicAuthInterceptor
import com.example.logisticzoneapp.core.util.UnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val MOVEX_API_BASE_URL = "https://fr02-tracadev01.corp.int/logistic-zone-api/api/"
    private const val MOVEX_API_SERVICE = "Movex"

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideMovexApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVEX_API_BASE_URL)
            .client(UnsafeOkHttpClient.unsafeOkHttpClient
                .newBuilder()
                .addInterceptor(BasicAuthInterceptor())
                .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovexApiDao(retrofit: Retrofit): MovexApiService {
        return retrofit.create(MovexApiService::class.java)
    }
}