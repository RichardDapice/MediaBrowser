package com.richarddapice.mediabrowser.di

import com.richarddapice.mediabrowser.BuildConfig
import com.richarddapice.mediabrowser.network.MediaService
import com.richarddapice.mediabrowser.util.Constants.Companion.API_KEY
import com.richarddapice.mediabrowser.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun mediaService(): MediaService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(MediaService::class.java)

    private val client
        get() =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiInterceptor)
                .build()

    private val loggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) BODY else NONE
        }

    private val apiInterceptor = Interceptor { chain ->
        val originalUrl = chain.request().url
        val apiKeyUrl = originalUrl
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()
        val request = chain.request().newBuilder().url(apiKeyUrl).build()
        chain.proceed(request)
    }
}

