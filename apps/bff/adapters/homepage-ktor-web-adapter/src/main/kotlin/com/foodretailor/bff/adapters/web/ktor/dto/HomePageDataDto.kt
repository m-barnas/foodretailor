package com.foodretailor.bff.adapters.web.ktor.dto

import com.foodretailor.bff.applications.homepage.usecase.api.GetHomePageDataUseCase
import kotlinx.serialization.Serializable

@Serializable
data class HomePageDataDto(
    val store: StoreDto,
    val membership: MembershipDto,
    val reward: RewardDto
)

fun GetHomePageDataUseCase.Output.toDto() =
    HomePageDataDto(
        store = store.toDto(),
        membership = membership.toDto(),
        reward = reward.toDto(),
    )