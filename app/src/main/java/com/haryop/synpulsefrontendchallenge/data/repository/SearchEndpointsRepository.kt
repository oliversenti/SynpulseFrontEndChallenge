package com.haryop.synpulsefrontendchallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntities
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.remote.SearchEndpointRemoteDataSource
import com.haryop.synpulsefrontendchallenge.utils.Resource
import com.haryop.synpulsefrontendchallenge.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class SearchEndpointsRepository @Inject constructor(
    private val remoteDataSource: SearchEndpointRemoteDataSource
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

}