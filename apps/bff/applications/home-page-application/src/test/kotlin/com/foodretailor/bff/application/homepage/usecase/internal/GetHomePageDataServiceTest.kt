package com.foodretailor.bff.application.homepage.usecase.internal

import com.foodretailor.bff.applications.homepage.model.api.Membership
import com.foodretailor.bff.applications.homepage.model.api.Reward
import com.foodretailor.bff.applications.homepage.model.api.Store
import com.foodretailor.bff.applications.homepage.usecase.api.GetHomePageDataUseCase
import com.foodretailor.bff.applications.homepage.usecase.internal.GetHomePageDataService
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetHomePageDataServiceTest {

    @Test
    fun `returns aggregated home page data`() = runTest {
        // given
        val service = GetHomePageDataService(
            exchangeExternalJwtPort = { "internal-token" },
            getStoreByTokenPort = {
                Store(name = "Main Store", open = true)
            },
            getMembershipByTokenPort = {
                Membership(points = 120)
            },
            getRewardByTokenPort = {
                Reward(coupons = listOf("10%", "FREE_COFFEE"))
            },
        )

        // when
        val result = service.getHomePageData(
            GetHomePageDataUseCase.Input(externalJwt = "external-jwt")
        )

        // then
        assertEquals("Main Store", result.store.name)
        assertEquals(true, result.store.open)
        assertEquals(120, result.membership.points)
        assertEquals(listOf("10%", "FREE_COFFEE"), result.reward.coupons)
    }


    @Test
    fun `uses exchanged internal token for downstream services`() = runTest {
        // given
        var tokenSeenByStorePort: String? = null

        val service = GetHomePageDataService(
            exchangeExternalJwtPort = { "internal-token-xyz" },
            getStoreByTokenPort = {
                tokenSeenByStorePort = it
                Store(name = "Any Store", open = false)
            },
            getMembershipByTokenPort = {
                Membership(points = 10)
            },
            getRewardByTokenPort = {
                Reward(coupons = emptyList())
            },
        )

        // when
        service.getHomePageData(
            GetHomePageDataUseCase.Input(externalJwt = "external-jwt")
        )

        // then
        assertEquals("internal-token-xyz", tokenSeenByStorePort)
    }

    @Test
    fun `fails when one downstream service fails`() = runTest {
        // given
        val service = GetHomePageDataService(
            exchangeExternalJwtPort = { "internal-token" },
            getStoreByTokenPort = {
                throw IllegalStateException("Store service unavailable")
            },
            getMembershipByTokenPort = {
                Membership(points = 50)
            },
            getRewardByTokenPort = {
                Reward(coupons = listOf("5%"))
            },
        )

        // when / then
        assertFailsWith<IllegalStateException> {
            service.getHomePageData(
                GetHomePageDataUseCase.Input(externalJwt = "external-jwt")
            )
        }
    }
}