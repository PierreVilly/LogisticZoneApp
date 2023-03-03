package com.example.logisticzoneapp.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReelEntity(
    @PrimaryKey val reelId: String,
    val uniqueCode: String,
    val designation: String,
    val reference: String,
    val quantity: String,
//    val locations: Array<String>,
    val defaultLocation: String,
    val affectedReplenishmentListId: String
)
