package com.example.logisticzoneapp.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListReelEntity

@Dao
interface ReelsAffectedToReplenishmentListDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertReplenishmentListReelEntities(replenishmentListReelEntities: List<ReplenishmentListReelEntity>)
}