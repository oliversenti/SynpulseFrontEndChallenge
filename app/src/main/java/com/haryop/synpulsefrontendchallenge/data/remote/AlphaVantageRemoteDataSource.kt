package com.haryop.synpulsefrontendchallenge.data.remote

import javax.inject.Inject

class AlphaVantageRemoteDataSource @Inject constructor(
    private val alphaVantageService: AlphaVantageService
) : BaseDataSource() {

    suspend fun getSearchEndpoint(keywords: String) = getResult { alphaVantageService.searchEndpoint(keywords) }

    suspend fun getQuoteEndpoint(symbol: String) = getResult { alphaVantageService.quoteEndpoint(symbol) }

}