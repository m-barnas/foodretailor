package com.foodretailor.bff.applications.homepage.port.api

import com.foodretailor.bff.applications.homepage.model.api.Reward

fun interface GetRewardByTokenPort {
    suspend fun getRewardByToken(token: String): Reward
}