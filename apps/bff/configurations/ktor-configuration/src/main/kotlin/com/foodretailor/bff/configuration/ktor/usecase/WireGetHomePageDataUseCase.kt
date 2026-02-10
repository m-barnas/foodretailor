package com.foodretailor.bff.configuration.ktor.usecase

import com.foodretailor.bff.adapters.store.GetStoreByTokenAdapter
import com.foodretailor.bff.adapters.token.ExchangeExternalJwtAdapter
import com.foodretailor.bff.adapters.useraccount.GetMembershipByTokenAdapter
import com.foodretailor.bff.adapters.useraccount.GetRewardByTokenAdapter
import com.foodretailor.bff.applications.homepage.usecase.api.GetHomePageDataUseCase
import com.foodretailor.bff.applications.homepage.usecase.api.getHomePageDataUseCaseInstance

fun wireGetHomePageDataUseCase(): GetHomePageDataUseCase =
    getHomePageDataUseCaseInstance(
        exchangeExternalJwtPort = ExchangeExternalJwtAdapter(),
        getStoreByTokenPort = GetStoreByTokenAdapter(),
        getMembershipByTokenPort = GetMembershipByTokenAdapter(),
        getRewardByTokenPort = GetRewardByTokenAdapter(),
    )