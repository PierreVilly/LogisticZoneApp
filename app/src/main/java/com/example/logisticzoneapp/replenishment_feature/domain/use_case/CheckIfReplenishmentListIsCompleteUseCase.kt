package com.example.logisticzoneapp.replenishment_feature.domain.use_case

import com.example.logisticzoneapp.replenishment_feature.domain.model.ReplenishmentListStateType

class CheckIfReplenishmentListIsCompleteUseCase {
    operator fun invoke(remainingReelsToScan: Int) : ReplenishmentListStateType {
        return when{
            remainingReelsToScan == 0 -> ReplenishmentListStateType.Completed
            remainingReelsToScan == 9999999 -> ReplenishmentListStateType.CantBeCompleted
            remainingReelsToScan > 0 -> ReplenishmentListStateType.Uncompleted
            else -> ReplenishmentListStateType.Unknown
        }
    }
}