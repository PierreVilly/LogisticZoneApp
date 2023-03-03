package com.example.logisticzoneapp.core.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["replenishmentListId", "reelId"])
data class ReplenishmentListReelEntity(
    val replenishmentListId: String,
    val reelId: String
)
