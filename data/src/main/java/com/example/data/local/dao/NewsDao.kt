package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.network.model.articles.ArticleDto

@Dao
interface NewsDao {
    @Query("SELECT * FROM ArticleDto ")
    suspend fun getNews(): List<ArticleDto>

    @Query("SELECT * FROM ArticleDto WHERE sources = :sources")
    suspend fun getNewsBySource(sources: String): List<ArticleDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<ArticleDto>)

}