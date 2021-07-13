package com.haryop.synpulsefrontendchallenge.data.entities

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.util.*

data class DailyDataEntities(
    @SerializedName("metadata") val metadata: DailyDataMetadata,
    @SerializedName("Time Series (Daily)") val time_series_data: JsonElement
)

data class DailyDataMetadata(
    @SerializedName("1. Information") val Information: String,
    @SerializedName("2. Symbol") val Symbol: String,
    @SerializedName("3. Last Refreshed") val last_refreshed: String,
    @SerializedName("4. Output Size") val output_size: String,
    @SerializedName("5. Time Zone") val time_zone: String
)