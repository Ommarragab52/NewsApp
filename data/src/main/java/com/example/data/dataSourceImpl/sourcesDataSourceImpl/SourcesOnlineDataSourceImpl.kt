package com.example.data.dataSourceImpl.sourcesDataSourceImpl

import com.example.data.mappers.toEntity
import com.example.data.network.WebServices
import com.example.domain.dataSource.sourcesDataSource.SourcesDataSource
import com.example.domain.model.Source
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) :
    SourcesDataSource {

    override suspend fun getSources(): List<Source?> {
        return webServices.getSources().sources?.map { it?.toEntity() } ?: listOf()
    }

    override suspend fun getSourcesByCategory(category: String): List<Source?> {
        return webServices.getSources(category = category).sources?.map { it?.toEntity() }
            ?: listOf()
    }

}