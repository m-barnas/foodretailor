package com.foodretailor.bff.configuration.ktor

import com.foodretailor.bff.adapters.web.ktor.call.homePageCall
import com.foodretailor.bff.configuration.ktor.internal.bearerToken
import com.foodretailor.bff.configuration.ktor.usecase.wireGetHomePageDataUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        authenticate(jwtAuthName) {
            get("/{channelId}/{screenId}") {
                val channelId = call.parameters["channelId"]
                    ?: return@get call.respond(HttpStatusCode.NotFound)

                // todo: remove mock
                if (channelId != "mobile-app") {
                    return@get call.respond(HttpStatusCode.Forbidden)
                }

                val screenId = call.parameters["screenId"]
                    ?: return@get call.respond(HttpStatusCode.NotFound)

                val externalJwt = call.bearerToken()
                    ?: return@get call.respond(HttpStatusCode.Unauthorized)

                when (screenId) {
                    "home-page" ->
                        call.homePageCall(
                            externalJwt = externalJwt,
                            getHomePageDataUseCase = wireGetHomePageDataUseCase(),
                        )

                    else ->
                        call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}