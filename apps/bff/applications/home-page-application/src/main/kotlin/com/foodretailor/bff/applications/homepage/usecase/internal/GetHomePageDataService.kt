package com.foodretailor.bff.applications.homepage.usecase.internal

import com.foodretailor.bff.applications.homepage.port.api.ExchangeExternalJwtPort
import com.foodretailor.bff.applications.homepage.port.api.GetRewardByTokenPort
import com.foodretailor.bff.applications.homepage.port.api.GetStoreByTokenPort
import com.foodretailor.bff.applications.homepage.port.api.GetMembershipByTokenPort
import com.foodretailor.bff.applications.homepage.usecase.api.GetHomePageDataUseCase
import com.foodretailor.bff.tools.parallel.parallel
import kotlinx.coroutines.async

internal class GetHomePageDataService(
    val exchangeExternalJwtPort: ExchangeExternalJwtPort,
    val getStoreByTokenPort: GetStoreByTokenPort,
    val getMembershipByTokenPort: GetMembershipByTokenPort,
    val getRewardByTokenPort: GetRewardByTokenPort,
) : GetHomePageDataUseCase {

    override suspend fun getHomePageData(input: GetHomePageDataUseCase.Input): GetHomePageDataUseCase.Output {
        val internalToken = exchangeExternalJwtPort.exchange(input.externalJwt)

        return parallel {
            val store = async { getStoreByTokenPort.getStoreByToken(internalToken) }
            val membership = async { getMembershipByTokenPort.getMembershipByToken(internalToken) }
            val reward = async { getRewardByTokenPort.getRewardByToken(internalToken) }

            GetHomePageDataUseCase.Output(
                store = store.await(),
                membership = membership.await(),
                reward = reward.await()
            )
        }
    }
}