package com.example.data

import com.example.data.dataSourceImpl.newsDataSourceimpl.NewsOfflineDataSourceImpl
import com.example.data.dataSourceImpl.newsDataSourceimpl.NewsOnlineDataSourceImpl
import com.example.data.dataSourceImpl.sourcesDataSourceImpl.SourcesOfflineDataSourceImpl
import com.example.data.dataSourceImpl.sourcesDataSourceImpl.SourcesOnlineDataSourceImpl
import com.example.data.repositoryImpl.NewsRepositoryImpl
import com.example.data.repositoryImpl.SourcesRepositoryImpl
import com.example.domain.dataSource.newsDataSource.NewsDataSource
import com.example.domain.dataSource.sourcesDataSource.SourcesDataSource
import com.example.domain.repository.newsRepository.NewsRepository
import com.example.domain.repository.sourcesRepository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    @SourcesOnlineDataSource
    abstract fun bindSourcesOnlineDataSource(
        sourcesOnlineDataSource: SourcesOnlineDataSourceImpl
    ): SourcesDataSource

    @Binds
    @SourcesOfflineDataSource
    abstract fun bindSourcesOfflineDataSource(
        sourcesOfflineDataSource: SourcesOfflineDataSourceImpl
    ): SourcesDataSource

    @Binds
    @NewsOnlineDataSource
    abstract fun bindNewsOnlineDataSource(
        newsOnlineDataSource: NewsOnlineDataSourceImpl
    ): NewsDataSource

    @Binds
    @NewsOfflineDataSource
    abstract fun bindNewsOfflineDataSource(
        newsOfflineDataSourceImpl: NewsOfflineDataSourceImpl
    ): NewsDataSource

    @Binds
    abstract fun bindSourcesRepository(
        sourcesRepositoryImpl: SourcesRepositoryImpl
    ): SourcesRepository

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SourcesOnlineDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SourcesOfflineDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOnlineDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOfflineDataSource
