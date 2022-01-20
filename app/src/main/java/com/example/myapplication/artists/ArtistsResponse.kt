package com.example.myapplication.artists

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    @SerializedName("artists") val artists: Artists
)
