package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.network.model.sources.SourceDto

@Dao
interface SourcesDao {
    @Query("SELECT * FROM SourceDto")
    suspend fun getSources(): List<SourceDto>

    @Query("SELECT * FROM SourceDto WHERE category = :category")
    suspend fun getSourcesByCategory(category: String): List<SourceDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sources: List<SourceDto>)
}