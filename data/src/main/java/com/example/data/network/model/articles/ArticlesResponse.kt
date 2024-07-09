package com.example.data.network.model.articles

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,
    @field:SerializedName("articles")
    val articles: List<ArticleDto?>? = null,
    @field:SerializedName("status")
    val status: String? = null,
    val message: String? = null,
    val code: String? = null
)