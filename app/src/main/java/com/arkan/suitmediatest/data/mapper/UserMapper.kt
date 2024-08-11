package com.arkan.suitmediatest.data.mapper

import com.arkan.suitmediatest.data.model.User
import com.arkan.suitmediatest.data.source.network.model.UserDataResponse

fun UserDataResponse?.toUser() =
    User(
        id = this?.id ?: 0,
        email = this?.email.orEmpty(),
        avatar = this?.avatar.orEmpty(),
        lastName = this?.lastName.orEmpty(),
        firstName = this?.firstName.orEmpty(),
    )

fun Collection<UserDataResponse>?.toUser() =
    this?.map {
        it.toUser()
    } ?: listOf()
