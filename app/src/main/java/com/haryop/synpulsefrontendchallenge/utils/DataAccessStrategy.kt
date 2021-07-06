package com.haryop.synpulsefrontendchallenge.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntities
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import kotlinx.coroutines.Dispatchers

//LiveData Coroutine
//T = List<SearchEndpointEntity>
fun <T, A> performGetOperation(
    networkCall: suspend () -> Resource<A>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            val listSearch = (responseStatus.data!! as SearchEndpointEntities).bestMatches
            emitSource(liveData { Resource.success(listSearch) })

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }