package com.example.data.repositoryImpl

import com.example.data.SourcesOfflineDataSource
import com.example.data.SourcesOnlineDataSource
import com.example.data.local.dao.SourcesDao
import com.example.domain.model.Source
import com.example.data.mappers.toDto
import com.example.data.network.model.sources.SourceDto
import com.example.data.util.ConnectivityUtil

import com.example.domain.dataSource.sourcesDataSource.SourcesDataSource
import com.example.domain.repository.sourcesRepository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    @SourcesOnlineDataSource
    private val sourcesOnlineDataSource: SourcesDataSource,
    @SourcesOfflineDataSource
    private val sourcesOfflineDataSource: SourcesDataSource,
    private val sourcesDao: SourcesDao,
    private val connectivityUtil: ConnectivityUtil
) : SourcesRepository {
    override suspend fun getSources(): List<Source?> {
        return if (connectivityUtil.checkForInternet()) {
            val newData = sourcesOnlineDataSource.getSources()
            sourcesDao.insertAll(newData.map {it?.toDto()?: SourceDto() })
            sourcesOfflineDataSource.getSources()
        } else sourcesOfflineDataSource.getSources()

    }

    override suspend fun getSourcesByCategory(category: String): List<Source?> {
        return if (connectivityUtil.checkForInternet()) {
            val newData = sourcesOnlineDataSource.getSources()
            sourcesDao.insertAll(newData.map {it?.toDto()?:SourceDto() })
            sourcesOfflineDataSource.getSourcesByCategory(category)
        } else sourcesOfflineDataSource.getSourcesByCategory(category)
    }



}