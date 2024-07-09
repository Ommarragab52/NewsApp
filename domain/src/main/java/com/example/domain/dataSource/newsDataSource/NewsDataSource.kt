package com.example.domain.dataSource.newsDataSource

import com.example.domain.model.Article

interface NewsDataSource {
    suspend fun getNews(): List<Article?>
    suspend fun getNewsBySource(sources: String): List<Article?>
}