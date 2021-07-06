package com.haryop.synpulsefrontendchallenge.data.entities

import com.google.gson.annotations.SerializedName

data class QuoteEndpointEntity(
    @SerializedName("Global Quote") val global_quote: GlobalQuoeEndpointEntity
)