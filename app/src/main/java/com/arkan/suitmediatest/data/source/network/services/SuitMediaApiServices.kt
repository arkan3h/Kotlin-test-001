package com.arkan.suitmediatest.data.source.network.services

import com.arkan.suitmediatest.BuildConfig
import com.arkan.suitmediatest.data.source.network.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface SuitMediaApiServices {
    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): UserResponse

    companion object {
        @JvmStatic
        operator fun invoke(): SuitMediaApiServices {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(SuitMediaApiServices::class.java)
        }
    }
}
