package com.example.domain.repository.newsRepository

import com.example.domain.model.Article

interface NewsRepository {
    suspend fun getNews(): List<Article?>
    suspend fun getNewsBySource(sources: String): List<Article?>
}