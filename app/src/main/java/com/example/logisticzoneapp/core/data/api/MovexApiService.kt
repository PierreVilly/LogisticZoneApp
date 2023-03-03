package com.example.logisticzoneapp.core.data.api

import com.example.logisticzoneapp.login_feature.domain.model.MovexUser
import retrofit2.Response
import retrofit2.http.*

interface MovexApiService {
//    @GET("replenishmentList")
//    suspend fun getReplenishment(@Query("replenishmentCode") replenishmentCode: String): Response<Int>

    @POST("movex/login")
    suspend fun login(@Body credentials: MovexUser): Response<Void>

    @GET("fakeData/replenishmentList")
    suspend fun getReplenishment(@Query("replenishmentCode") replenishmentCode: String): Response<Int>

//    @POST("replenishmentList/addReel")
//    suspend fun addReelToReplenishmentList(@Body replenishmentListAffectationDTO: ReplenishmentListAffectationDTO): ReplenishmentListRemainingQuantityWithLastReelDTO
}