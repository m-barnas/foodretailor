package com.foodretailor.bff.applications.homepage.usecase.api

import com.foodretailor.bff.applications.homepage.model.api.Reward
import com.foodretailor.bff.applications.homepage.model.api.Store
import com.foodretailor.bff.applications.homepage.model.api.Membership

fun interface GetHomePageDataUseCase {

    suspend fun getHomePageData(input: Input): Output

    data class Input(
        val externalJwt: String,
    )

    data class Output(
        val store: Store,
        val membership: Membership,
        val reward: Reward,
    )
}