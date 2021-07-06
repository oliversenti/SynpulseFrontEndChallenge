package com.haryop.synpulsefrontendchallenge.data.entities

import com.google.gson.annotations.SerializedName

data class SearchEndpointEntities(
    @SerializedName("bestMatches") val bestMatches: List<SearchEndpointEntity>
)