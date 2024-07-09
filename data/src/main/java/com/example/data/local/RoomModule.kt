package com.example.data.local

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.NewsDao
import com.example.data.local.dao.SourcesDao
import com.example.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesNewsDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "news")
            .addTypeConverter(Convertors())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao {
        return newsDatabase.getNewsDao()
    }

    @Provides
    @Singleton
    fun providesSourcesDao(
        newsDatabase: NewsDatabase
    ): SourcesDao {
        return newsDatabase.getSourcesDao()
    }
}