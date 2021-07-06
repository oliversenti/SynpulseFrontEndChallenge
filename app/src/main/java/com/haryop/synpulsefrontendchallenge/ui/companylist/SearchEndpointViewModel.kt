package com.haryop.synpulsefrontendchallenge.ui.companylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.repository.SearchEndpointsRepository
import com.haryop.synpulsefrontendchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchEndpointViewModel @Inject constructor(
    private val repository: SearchEndpointsRepository
) : ViewModel() {

    fun start(keyword: String) {
        _keyword.value = keyword
    }

    private val _keyword = MutableLiveData<String>()

    private val _getSearchEndpoint = _keyword.switchMap { keyword ->
        repository.getSearchEndpoint(keyword)
    }

    val getSearchEndpoint: LiveData<Resource<List<SearchEndpointEntity>>> = _getSearchEndpoint

}