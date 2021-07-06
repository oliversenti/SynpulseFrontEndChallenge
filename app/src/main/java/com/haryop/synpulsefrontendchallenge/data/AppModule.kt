package com.haryop.synpulsefrontendchallenge.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.haryop.synpulsefrontendchallenge.data.remote.AlphaVantageRemoteDataSource
import com.haryop.synpulsefrontendchallenge.data.remote.AlphaVantageService
import com.haryop.synpulsefrontendchallenge.data.repository.AlphaVintageRepository
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
@InstallIn(SingletonComponent::class) //ApplicationComponent ny adeprecated diganti jadi SingletonComponent
object AppModule {

    fun logOkHttplient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.alphavantage.co/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(logOkHttplient())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideSearchEndpoint(retrofit: Retrofit): AlphaVantageService =
        retrofit.create(AlphaVantageService::class.java)

    @Singleton
    @Provides
    fun provideSearchEndpointRemoteDataSource(alphaVantageService: AlphaVantageService) =
        AlphaVantageRemoteDataSource(alphaVantageService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: AlphaVantageRemoteDataSource
    ) =
        AlphaVintageRepository(remoteDataSource)

}
