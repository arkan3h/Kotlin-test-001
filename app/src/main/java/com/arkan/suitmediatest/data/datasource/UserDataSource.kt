package com.arkan.suitmediatest.data.datasource

import com.arkan.suitmediatest.data.source.network.model.UserResponse
import com.arkan.suitmediatest.data.source.network.services.SuitMediaApiServices

interface UserDataSource {
    suspend fun getUser(page: Int): UserResponse
}

class UserApiDataSource(
    private val service: SuitMediaApiServices,
) : UserDataSource {
    override suspend fun getUser(page: Int): UserResponse {
        return service.getUser(page)
    }
}
