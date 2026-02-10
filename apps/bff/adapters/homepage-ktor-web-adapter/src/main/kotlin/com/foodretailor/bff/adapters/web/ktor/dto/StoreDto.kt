package com.foodretailor.bff.adapters.web.ktor.dto

import com.foodretailor.bff.applications.homepage.model.api.Store
import kotlinx.serialization.Serializable

@Serializable
data class StoreDto(
    val name: String,
    val open: Boolean,
)

fun Store.toDto() =
    StoreDto(
        name = name,
        open = open,
    )