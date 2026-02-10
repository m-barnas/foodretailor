package com.foodretailor.bff.applications.homepage.port.api

fun interface ExchangeExternalJwtPort {
    suspend fun exchange(externalJwt: String): String
}