package com.example.newsapp.network

import com.example.newsapp.network.model.articles.ArticlesResponse
import com.example.newsapp.network.model.sources.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String = ApiConstants.API_KEY,
        @Query("category") category: String? = null
    ): Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(
        @Query("apiKey")key:String=ApiConstants.API_KEY,
        @Query("sources")source:String
    ):Call<ArticlesResponse>
}