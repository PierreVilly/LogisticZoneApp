package com.example.logisticzoneapp.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListEntity
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListWithAffectedReelsObject

@Dao
interface ReplenishmentListDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertReplenishmentList(replenishmentListEntity: ReplenishmentListEntity)
//
//    @Transaction
//    @Query("SELECT * FROM ReplenishmentListEntity")
//    suspend fun getReplenishmentLists(): List<ReplenishmentListWithAffectedReelsObject>
//
//    @Query("SELECT code FROM REPLENISHMENTLISTENTITY WHERE replenishmentListId = :replenishmentListId")
//    suspend fun getReplenishmentListCodeById(replenishmentListId: String): String
}