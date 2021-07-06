package com.haryop.synpulsefrontendchallenge.data.entities

import com.google.gson.annotations.SerializedName

data class GlobalQuoeEndpointEntity(
    @SerializedName("01. symbol") val symbol: String,
    @SerializedName("02. open") val open: String,
    @SerializedName("03. high") val high: String,
    @SerializedName("04. low") val low: String,
    @SerializedName("05. price") val price: String,
    @SerializedName("06. volume") val volume: String,
    @SerializedName("07. latest trading day") val latest: String,
    @SerializedName("08. previous close") val prev_close: String,
    @SerializedName("09. change") val change: String,
    @SerializedName("10. change percent") val change_percent: String
)