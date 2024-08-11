package com.arkan.suitmediatest.data.source.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
    @SerializedName("data")
    val data: List<UserDataResponse>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("support")
    val support: UserSupportResponse?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
)
