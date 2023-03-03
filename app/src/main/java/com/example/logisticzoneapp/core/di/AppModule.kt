package com.example.logisticzoneapp.core.di

import com.example.logisticzoneapp.core.domain.zebra.IBarcodeScannerHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideReplenishmentListDatabase(@ApplicationContext context: Context): ReplenishmentListDatabase{
//        return  Room.databaseBuilder(
//            context,
//            ReplenishmentListDatabase::class.java,
//            "replenishment_db"
//        )
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideReplenishmentListDao(replenishmentListDatabase: ReplenishmentListDatabase): ReplenishmentListDao {
//        return  replenishmentListDatabase.replenishmentListDao()
//    }
}