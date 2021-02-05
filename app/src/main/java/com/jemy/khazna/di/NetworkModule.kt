package com.jemy.khazna.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jemy.khazna.BuildConfig
import com.jemy.khazna.data.network.Api
import com.jemy.khazna.utils.Constants
import com.jemy.khazna.utils.NetworkInterceptor
import com.jemy.khazna.utils.NullOnEmptyConverterFactory
import com.jemy.khazna.utils.extensions.isNetworkAvailable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    @Singleton
    fun getNetworkInterceptor(
        @ApplicationContext context: Context
    ): NetworkInterceptor = object : NetworkInterceptor() {
        override fun isInternetAvailable(): Boolean = context.isNetworkAvailable()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson:Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

        @Provides
        @Singleton
        fun providesApiInterface(retrofit: Retrofit): Api = retrofit.create(
            Api::class.java
        )
}