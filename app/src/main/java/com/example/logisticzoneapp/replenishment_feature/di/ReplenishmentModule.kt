package com.example.logisticzoneapp.replenishment_feature.di

import com.example.logisticzoneapp.replenishment_feature.domain.use_case.CheckIfReplenishmentListIsCompleteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReplenishmentModule {
    @Provides
    @Singleton
    fun provideCheckIfReplenishmentListIsCompleteUseCase(): CheckIfReplenishmentListIsCompleteUseCase {
        return CheckIfReplenishmentListIsCompleteUseCase()
    }
}