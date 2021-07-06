package com.haryop.synpulsefrontendchallenge.ui.companylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.haryop.synpulsefrontendchallenge.data.entities.QuoteEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.repository.AlphaVintageRepository
import com.haryop.synpulsefrontendchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompanyDetailViewModel @Inject constructor(
    private val repository: AlphaVintageRepository
) : ViewModel() {

    fun start(symbol: String) {
        Timber.e("symbol: "+symbol)
        _symbol.value = symbol
    }

    private val _symbol = MutableLiveData<String>()

    private val _getQuoteEndpoint = _symbol.switchMap { symbol ->
        repository.getQuoteEndpoint(symbol)
    }

    val getQuoteEndpoint: LiveData<Resource<QuoteEndpointEntity>> = _getQuoteEndpoint

}