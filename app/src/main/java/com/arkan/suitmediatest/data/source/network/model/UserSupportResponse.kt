package com.arkan.suitmediatest.data.source.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserSupportResponse(
    @SerializedName("text")
    val text: String?,
    @SerializedName("url")
    val url: String?,
)
