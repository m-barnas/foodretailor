package com.foodretailor.bff.adapters.web.ktor.call

import com.foodretailor.bff.adapters.web.ktor.dto.toDto
import com.foodretailor.bff.applications.homepage.usecase.api.GetHomePageDataUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

suspend fun ApplicationCall.homePageCall(
    externalJwt: String,
    getHomePageDataUseCase: GetHomePageDataUseCase,
) {
    val result = getHomePageDataUseCase.getHomePageData(
        GetHomePageDataUseCase.Input(
            externalJwt = externalJwt
        )
    )

    respond(HttpStatusCode.OK, result.toDto())
}