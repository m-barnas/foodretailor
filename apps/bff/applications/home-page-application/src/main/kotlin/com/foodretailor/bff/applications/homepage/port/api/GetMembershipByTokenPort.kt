package com.foodretailor.bff.applications.homepage.port.api

import com.foodretailor.bff.applications.homepage.model.api.Membership

fun interface GetMembershipByTokenPort {
    suspend fun getMembershipByToken(token: String): Membership
}