package com.example.logisticzoneapp.replenishment_feature.domain.model

import com.example.logisticzoneapp.core.domain.model.Reel

data class ReplenishmentListRemainingQuantityToAffectWithLastReelDTO(
    val remainingReelsQuantityToAffect: Int,
    val reel: Reel
)
