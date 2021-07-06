package com.haryop.synpulsefrontendchallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.haryop.synpulsefrontendchallenge.data.entities.QuoteEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntities
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.remote.AlphaVantageRemoteDataSource
import com.haryop.synpulsefrontendchallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class AlphaVintageRepository @Inject constructor(
    private val remoteDataSource: AlphaVantageRemoteDataSource
) {

    fun getSearchEndpoint(keywords: String) = performGetSearchEndpointOperation(
        networkCall = { remoteDataSource.getSearchEndpoint(keywords) }
    )

    fun <A> performGetSearchEndpointOperation(
        networkCall: suspend () -> Resource<A>
    ): LiveData<Resource<List<SearchEndpointEntity>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                val listSearch = (responseStatus.data!! as SearchEndpointEntities).bestMatches
                emit(Resource.success(listSearch))

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }

    fun getQuoteEndpoint(symbol: String) = performGetQuoteEndpointOperation(
        networkCall = { remoteDataSource.getQuoteEndpoint(symbol) }
    )

    fun <A> performGetQuoteEndpointOperation(
        networkCall: suspend () -> Resource<A>
    ): LiveData<Resource<QuoteEndpointEntity>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            val quote = responseStatus.data as QuoteEndpointEntity
            emit(Resource.success(quote))

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }


}