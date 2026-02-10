package com.foodretailor.bff.applications.homepage.usecase.api

import com.foodretailor.bff.applications.homepage.port.api.ExchangeExternalJwtPort
import com.foodretailor.bff.applications.homepage.port.api.GetMembershipByTokenPort
import com.foodretailor.bff.applications.homepage.port.api.GetRewardByTokenPort
import com.foodretailor.bff.applications.homepage.port.api.GetStoreByTokenPort
import com.foodretailor.bff.applications.homepage.usecase.internal.GetHomePageDataService

fun getHomePageDataUseCaseInstance(
    exchangeExternalJwtPort: ExchangeExternalJwtPort,
    getStoreByTokenPort: GetStoreByTokenPort,
    getMembershipByTokenPort: GetMembershipByTokenPort,
    getRewardByTokenPort: GetRewardByTokenPort,
): GetHomePageDataUseCase =
    GetHomePageDataService(
        exchangeExternalJwtPort,
        getStoreByTokenPort,
        getMembershipByTokenPort,
        getRewardByTokenPort,
    )