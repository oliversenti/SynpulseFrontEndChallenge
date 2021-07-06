package com.haryop.synpulsefrontendchallenge.data.remote

import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntities
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpointService {

    @GET(ConstantsObj.ALPHAVANTAGE_BASEPARAM+"&function=SYMBOL_SEARCH")
    suspend fun searchEndpoint(@Query("keywords") keywords: String?): Response<SearchEndpointEntities>

}