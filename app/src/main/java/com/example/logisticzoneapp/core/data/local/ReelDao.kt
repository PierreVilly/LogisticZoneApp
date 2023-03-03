package com.example.logisticzoneapp.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.logisticzoneapp.core.data.local.entities.ReelEntity
import com.example.logisticzoneapp.core.data.local.entities.ReplenishmentListReelEntity

@Dao
interface ReelDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertReel(reelEntity: ReelEntity)
//
//    @Transaction
//    @Query("SELECT * FROM ReelEntity WHERE uniqueCode = :uniqueCode")
//    suspend fun getReelByCode(uniqueCode: String): ReelEntity



}