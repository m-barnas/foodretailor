package com.foodretailor.bff.adapters.useraccount

import com.foodretailor.bff.applications.homepage.model.api.Reward
import com.foodretailor.bff.applications.homepage.port.api.GetRewardByTokenPort

class GetRewardByTokenAdapter : GetRewardByTokenPort {
    override suspend fun getRewardByToken(token: String) =
        // todo: remove mock
        Reward(
            coupons = listOf(
                "5% off your entire purchase",
                "10% off selected products"
            )
        )
}