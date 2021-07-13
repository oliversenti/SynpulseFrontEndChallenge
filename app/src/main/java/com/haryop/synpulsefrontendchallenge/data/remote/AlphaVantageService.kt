package com.haryop.synpulsefrontendchallenge.data.remote

import com.haryop.synpulsefrontendchallenge.data.entities.DailyDataEntities
import com.haryop.synpulsefrontendchallenge.data.entities.QuoteEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntities
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageService {

    @GET(ConstantsObj.ALPHAVANTAGE_BASEPARAM+"&function=SYMBOL_SEARCH")
    suspend fun searchEndpoint(@Query("keywords") keywords: String?): Response<SearchEndpointEntities>

    @GET(ConstantsObj.ALPHAVANTAGE_BASEPARAM+"&function=GLOBAL_QUOTE")
    suspend fun quoteEndpoint(@Query("symbol") symbol: String?): Response<QuoteEndpointEntity>

    @GET(ConstantsObj.ALPHAVANTAGE_BASEPARAM+"&function=TIME_SERIES_DAILY")
    suspend fun dailyData(@Query("symbol") symbol: String?): Response<DailyDataEntities>

}