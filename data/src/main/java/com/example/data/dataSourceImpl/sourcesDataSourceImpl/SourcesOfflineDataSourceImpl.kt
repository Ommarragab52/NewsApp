package com.example.data.dataSourceImpl.sourcesDataSourceImpl

import com.example.data.local.dao.SourcesDao
import com.example.domain.model.Source
import com.example.data.mappers.toEntity
import com.example.domain.dataSource.sourcesDataSource.SourcesDataSource
import javax.inject.Inject

class SourcesOfflineDataSourceImpl @Inject constructor(
    private val sourcesDao: SourcesDao
) : SourcesDataSource {
    override suspend fun getSources(): List<Source?> {
        return sourcesDao.getSources().map { it.toEntity() }
    }

    override suspend fun getSourcesByCategory(category: String): List<Source?> {
        return sourcesDao.getSourcesByCategory(category).map { it.toEntity() }
    }



}