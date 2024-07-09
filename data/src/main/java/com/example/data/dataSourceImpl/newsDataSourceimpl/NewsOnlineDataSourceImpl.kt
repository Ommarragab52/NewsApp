package com.example.data.dataSourceImpl.newsDataSourceimpl

import com.example.data.local.Convertors
import com.example.data.mappers.toEntity
import com.example.data.network.WebServices
import com.example.domain.dataSource.newsDataSource.NewsDataSource
import com.example.domain.model.Article
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : NewsDataSource {

    override suspend fun getNews(): List<Article?> {
        return webServices.getNews().articles?.map { it?.toEntity() } ?: listOf()
    }

    override suspend fun getNewsBySource(sources: String): List<Article?> {
        return webServices.getNewsBySource(sources = sources).articles?.map { it?.toEntity()
        } ?: listOf()
    }
}