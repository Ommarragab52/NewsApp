package com.example.newsapp.network.model.sources


data class SourcesResponse(
    val sources: List<Source?>? = null,
    val status: String? = null,
    val message: String? = null,
    val code: String? = null
)


