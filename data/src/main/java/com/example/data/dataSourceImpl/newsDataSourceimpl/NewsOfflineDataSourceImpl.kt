package com.example.data.dataSourceImpl.newsDataSourceimpl

import com.example.data.local.dao.NewsDao
import com.example.data.mappers.toDto
import com.example.data.mappers.toEntity
import com.example.data.network.model.articles.ArticleDto
import com.example.domain.dataSource.newsDataSource.NewsDataSource
import com.example.domain.model.Article
import javax.inject.Inject

class NewsOfflineDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : NewsDataSource {


    override suspend fun getNews(): List<Article?> {
        return newsDao.getNews().map { it.toEntity() }
    }

    override suspend fun getNewsBySource(sources: String): List<Article?> {
        return newsDao.getNewsBySource(sources).map { it.toEntity() }
    }

}