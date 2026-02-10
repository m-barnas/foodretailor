package com.foodretailor.bff.adapters.store

import com.foodretailor.bff.applications.homepage.model.api.Store
import com.foodretailor.bff.applications.homepage.port.api.GetStoreByTokenPort
import kotlinx.coroutines.delay

class GetStoreByTokenAdapter: GetStoreByTokenPort {
    override suspend fun getStoreByToken(token: String): Store {
        delay(120)

        // todo: remove mock
        return Store(
            name = "Prague",
            open = true,
        )
    }
}