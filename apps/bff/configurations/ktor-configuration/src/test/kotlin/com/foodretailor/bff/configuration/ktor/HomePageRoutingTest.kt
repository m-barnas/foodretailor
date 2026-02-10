package com.foodretailor.bff.configuration.ktor

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class HomePageRoutingTest {
    @Test
    fun `GET home-page returns 200 for mobile-app with JWT`() = testApplication {
        application {
            module()
        }

        val response = client.get("/mobile-app/home-page") {
            header(
                HttpHeaders.Authorization,
                "Bearer eyJhbGciOiJub25lIn0.eyJzdWIiOiJ1c2VyLTEyMyJ9."
            )
            accept(ContentType.Application.Json)
        }

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `GET home-page returns 403 for non-mobile channel`() = testApplication {
        application {
            module()
        }

        val response = client.get("/desktop-app/home-page") {
            header(
                HttpHeaders.Authorization,
                "Bearer eyJhbGciOiJub25lIn0.eyJzdWIiOiJ1c2VyLTEyMyJ9."
            )
            accept(ContentType.Application.Json)
        }

        assertEquals(HttpStatusCode.Forbidden, response.status)
    }

    @Test
    fun `GET home-page returns 401 when Authorization header missing`() = testApplication {
        application {
            module()
        }

        val response = client.get("/mobile-app/home-page") {
            accept(ContentType.Application.Json)
        }

        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }
}