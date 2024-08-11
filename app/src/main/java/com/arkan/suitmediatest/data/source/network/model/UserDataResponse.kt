package com.arkan.suitmediatest.data.source.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserDataResponse(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
)
