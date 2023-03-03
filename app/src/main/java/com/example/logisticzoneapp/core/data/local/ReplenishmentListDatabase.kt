package com.example.logisticzoneapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.logisticzoneapp.core.data.local.entities.ReelEntity
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListEntity
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListReelEntity

//@Database(
//    entities = [
//        ReelEntity::class,
//        ReplenishmentListEntity::class,
//        ReplenishmentListReelEntity::class,
//    ],
//    version = 1
//)
//abstract class ReplenishmentListDatabase: RoomDatabase() {
//    abstract fun replenishmentListDao(): ReplenishmentListDao
//    abstract fun reelDao(): ReelDao
//}