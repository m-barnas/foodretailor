package com.foodretailor.bff.configuration.ktor.internal

import io.ktor.server.application.ApplicationCall

private const val AUTH_HEADER = "Authorization"
private const val BEARER_PREFIX = "Bearer "

internal fun ApplicationCall.bearerToken(): String? =
    request.headers[AUTH_HEADER]
        ?.trim()
        ?.takeIf { it.startsWith(BEARER_PREFIX) }
        ?.removePrefix(BEARER_PREFIX)
        ?.trim()
        ?.takeIf { it.isNotEmpty() }