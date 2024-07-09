package com.example.data.network

import com.example.data.network.model.articles.ArticlesResponse
import com.example.data.network.model.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = ApiConstants.API_KEY,
        @Query("category") category: String? = null
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") key: String = ApiConstants.API_KEY,
    ): ArticlesResponse

    @GET("v2/everything")
    suspend fun getNewsBySource(
        @Query("apiKey") key: String = ApiConstants.API_KEY,
        @Query("sources") sources: String
    ): ArticlesResponse
}