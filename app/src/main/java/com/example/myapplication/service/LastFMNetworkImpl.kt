package com.example.myapplication.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LastFMNetworkImpl private constructor() : LastFMNetwork {

    private lateinit var artistsService: ArtistsService

    companion object {
        private const val BASE_URL = "https://ws.audioscrobbler.com/"

        private var instance: LastFMNetworkImpl? = null

        fun getInstance(): LastFMNetworkImpl {
            if (instance == null) {
                instance = LastFMNetworkImpl()
            }

            return instance!!
        }
    }

    init {
        initService()
    }

    private fun initService() {
        val bodyInterceptor = HttpLoggingInterceptor()
        val headersInterceptor = HttpLoggingInterceptor()
        bodyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        headersInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val client = OkHttpClient.Builder()
            .addInterceptor(bodyInterceptor)
            .addInterceptor(headersInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        artistsService = retrofit.create(ArtistsService::class.java)
    }

    override fun getArtistsService(): ArtistsService = artistsService
}