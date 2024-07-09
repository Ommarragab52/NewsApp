package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.Convertors
import com.example.data.local.dao.NewsDao
import com.example.data.local.dao.SourcesDao
import com.example.data.network.model.articles.ArticleDto
import com.example.data.network.model.sources.SourceDto

@Database(entities = [ArticleDto::class, SourceDto::class], version = 1, exportSchema = true)
@TypeConverters(Convertors::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getSourcesDao(): SourcesDao
    abstract fun getNewsDao(): NewsDao
}