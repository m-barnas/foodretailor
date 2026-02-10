package com.foodretailor.bff.adapters.token

import com.foodretailor.bff.applications.homepage.port.api.ExchangeExternalJwtPort
import java.util.UUID

class ExchangeExternalJwtAdapter: ExchangeExternalJwtPort {
    override suspend fun exchange(externalJwt: String): String {
        // todo: remove mock
        return UUID.randomUUID().toString()
    }
}