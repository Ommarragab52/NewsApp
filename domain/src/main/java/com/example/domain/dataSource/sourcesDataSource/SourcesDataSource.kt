package com.example.domain.dataSource.sourcesDataSource

import com.example.domain.model.Source

interface SourcesDataSource {
    suspend fun getSources():List<Source?>
    suspend fun getSourcesByCategory(category:String):List<Source?>
}