package com.example.logisticzoneapp.login_feature.di

import com.example.logisticzoneapp.login_feature.domain.use_case.ValidateLoginInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase{
        return ValidateLoginInputUseCase()
    }
}