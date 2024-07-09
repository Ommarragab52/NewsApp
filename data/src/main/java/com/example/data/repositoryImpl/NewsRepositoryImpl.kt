package com.example.data.repositoryImpl

import android.util.Log
import com.example.data.NewsOfflineDataSource
import com.example.data.NewsOnlineDataSource
import com.example.data.local.dao.NewsDao
import com.example.data.mappers.toDto
import com.example.data.network.model.articles.ArticleDto
import com.example.data.util.ConnectivityUtil
import com.example.domain.dataSource.newsDataSource.NewsDataSource
import com.example.domain.model.Article
import com.example.domain.repository.newsRepository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    @NewsOfflineDataSource
    private val newsOfflineDataSource: NewsDataSource,
    @NewsOnlineDataSource
    private val newsOnlineDataSource: NewsDataSource,
    private val newsDao: NewsDao,
    private val connectivityUtil: ConnectivityUtil
) : NewsRepository {

    override suspend fun getNews(): List<Article?> {
        return if (connectivityUtil.checkForInternet()) {
            val newData = newsOnlineDataSource.getNews()
            newsDao.insertAll(newData.map { it?.toDto() ?: ArticleDto() })
            newsOfflineDataSource.getNews()
        } else newsOfflineDataSource.getNews()

    }

    override suspend fun getNewsBySource(sources: String): List<Article?> {
        return if (connectivityUtil.checkForInternet()) {
            val newData =
                newsOnlineDataSource.getNewsBySource(sources)
            newsDao.insertAll(newData.map {
                it?.sources = sources
                it?.toDto() ?: ArticleDto()
            })
            newsOfflineDataSource.getNewsBySource(sources)
        } else newsOfflineDataSource.getNewsBySource(sources)

    }


}