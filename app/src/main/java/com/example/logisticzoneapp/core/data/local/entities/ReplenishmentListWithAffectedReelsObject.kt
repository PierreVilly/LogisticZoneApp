package com.example.logisticzoneapp.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ReplenishmentListWithAffectedReelsObject(
    @Embedded val replenishmentListEntity: ReplenishmentListEntity,
    @Relation(
        parentColumn = "replenishmentListId",
        entityColumn = "affectedReplenishmentListId",
        associateBy = Junction(ReplenishmentListReelEntity::class)
    )
    val reels: List<ReelEntity>,
    @Relation(
        parentColumn = "replenishmentListId",
        entityColumn = "replenishmentListId"
    )
    val replenishmentListReelEntities: List<ReplenishmentListReelEntity>
)
