package com.example.logisticzoneapp.replenishment_feature.presentation.state

import com.example.logisticzoneapp.core.domain.model.Reel

data class ReplenishmentListState(
    val code: String = "",
    val remainingReelsToScan: Int = 0,
    val listOfAffectedReels: MutableList<Reel> = mutableListOf(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isComplete: Boolean = false
)
