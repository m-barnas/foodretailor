package com.foodretailor.bff.adapters.web.ktor.dto

import com.foodretailor.bff.applications.homepage.model.api.Reward
import kotlinx.serialization.Serializable

@Serializable
data class RewardDto(
    val coupons: List<String>
)

fun Reward.toDto() =
    RewardDto(
        coupons = coupons,
    )