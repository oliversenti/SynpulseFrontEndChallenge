package com.haryop.synpulsefrontendchallenge.data.remote

import javax.inject.Inject

class SearchEndpointRemoteDataSource @Inject constructor(
    private val searchEndpointService: SearchEndpointService
) : BaseDataSource() {

    suspend fun getSearchEndpoint(keywords: String) = getResult { searchEndpointService.searchEndpoint(keywords) }

}