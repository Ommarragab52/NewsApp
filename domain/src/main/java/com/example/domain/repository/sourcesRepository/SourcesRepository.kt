package com.example.domain.repository.sourcesRepository

import com.example.domain.model.Source

interface SourcesRepository {
    suspend fun getSources():List<Source?>
    suspend fun getSourcesByCategory(category:String):List<Source?>
}