package com.foodretailor.bff.applications.homepage.port.api

import com.foodretailor.bff.applications.homepage.model.api.Store

fun interface GetStoreByTokenPort {
    suspend fun getStoreByToken(token: String): Store
}