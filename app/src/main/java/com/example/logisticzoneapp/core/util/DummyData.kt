package com.example.logisticzoneapp.core.util

import com.example.logisticzoneapp.core.domain.model.Reel
import com.example.logisticzoneapp.replenishment_feature.presentation.domain.model.ReplenishmentList

object DummyData {
    val replenishments = listOf(
        ReplenishmentList("", 4,
            listOf(
                Reel(
                    "",
                    "",
                    "",
                    "",
                    arrayOf(),
                    "",
                )
            ))
    )
}