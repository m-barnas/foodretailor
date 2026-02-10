package com.foodretailor.bff.configuration.ktor

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond

const val jwtAuthName = "home-page-jwt"

fun Application.configureSecurity() {
    install(Authentication) {
        jwt(jwtAuthName) {

            // todo: remove mock
            verifier(
                JWT
                    .require(Algorithm.none())
                    .build()
            )

            // todo: remove mock
            validate { credential ->
                JWTPrincipal(credential.payload)
            }

            challenge { _, _ ->
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "Not Authorized"
                )
            }
        }
    }
}