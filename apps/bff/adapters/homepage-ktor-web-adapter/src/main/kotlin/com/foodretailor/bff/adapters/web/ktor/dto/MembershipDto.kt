package com.foodretailor.bff.adapters.web.ktor.dto

import com.foodretailor.bff.applications.homepage.model.api.Membership
import kotlinx.serialization.Serializable

@Serializable
data class MembershipDto (
    val points: Int,
)

fun Membership.toDto() =
    MembershipDto(
        points = points,
    )