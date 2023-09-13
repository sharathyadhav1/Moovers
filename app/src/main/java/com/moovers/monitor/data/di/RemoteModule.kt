package com.moovers.monitor.data.di

import android.util.Log
import com.moovers.monitor.data.networking.CoroutineDispatcherProvider
import com.moovers.monitor.data.remote.Constants.BASE_URL
import com.moovers.monitor.data.remote.TruckMonitorApi


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val TAG = "API-LOGS"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message: String ->
            Log.d(TAG, message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                  .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideTruckMonitorApi(okHttpClient: OkHttpClient): TruckMonitorApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TruckMonitorApi::class.java)
    }

    @Provides
    fun provideCoroutineDispatcher() = CoroutineDispatcherProvider()
}