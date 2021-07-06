package com.haryop.synpulsefrontendchallenge.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.haryop.synpulsefrontendchallenge.data.remote.SearchEndpointRemoteDataSource
import com.haryop.synpulsefrontendchallenge.data.remote.SearchEndpointService
import com.haryop.synpulsefrontendchallenge.data.repository.SearchEndpointsRepository
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

    fun logOkHttplient():OkHttpClient {
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
    fun provideSearchEndpoint(retrofit: Retrofit): SearchEndpointService =
        retrofit.create(SearchEndpointService::class.java)

    @Singleton
    @Provides
    fun provideSearchEndpointRemoteDataSource(searchEndpointService: SearchEndpointService) =
        SearchEndpointRemoteDataSource(searchEndpointService)

//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext appContext: Context) =
//        AppDatabase.getDatabase(appContext)
//
//    @Singleton
//    @Provides
//    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

//    @Singleton
//    @Provides
//    fun provideRepository(
//        remoteDataSource: CharacterRemoteDataSource,
//        localDataSource: CharacterDao
//    ) =
//        CharacterRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: SearchEndpointRemoteDataSource
    ) =
        SearchEndpointsRepository(remoteDataSource)

}
