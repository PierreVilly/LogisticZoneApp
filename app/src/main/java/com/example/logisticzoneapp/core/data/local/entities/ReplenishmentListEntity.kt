package com.example.logisticzoneapp.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReplenishmentListEntity(
    @PrimaryKey val replenishmentListId: String,
    val code: String,
    val remainingQuantity: Int,
    val date: String,
    val time: String
)
