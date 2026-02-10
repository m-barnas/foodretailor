package com.foodretailor.bff.adapters.useraccount

import com.foodretailor.bff.applications.homepage.model.api.Membership
import com.foodretailor.bff.applications.homepage.port.api.GetMembershipByTokenPort

class GetMembershipByTokenAdapter : GetMembershipByTokenPort {
    override suspend fun getMembershipByToken(token: String) =
        // todo: remove mock
        Membership(
            points = 42
        )
}