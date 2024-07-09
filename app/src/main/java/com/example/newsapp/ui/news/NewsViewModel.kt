package com.example.newsapp.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Source
import com.example.data.network.model.articles.ArticlesResponse
import com.example.data.network.model.sources.SourcesResponse
import com.example.domain.model.Article
import com.example.domain.repository.newsRepository.NewsRepository
import com.example.domain.repository.sourcesRepository.SourcesRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val sourcesRepository: SourcesRepository
) : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<ErrorView>()
    val sourcesList = MutableLiveData<List<Source?>?>()
    val articlesList = MutableLiveData<List<Article?>?>()
    fun getSources(category: String?) {
        viewModelScope.launch {
            showLoading.postValue(true)
            try {
                var sources: List<Source?>
                withContext(Dispatchers.IO) {
                    sources = category?.let { sourcesRepository.getSourcesByCategory(it) }?: listOf()
                }
                sourcesList.postValue(sources)

            } catch (e: HttpException) {
                val errorBodyJson = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBodyJson, SourcesResponse::class.java)
                showError.postValue(ErrorView(
                    message = errorResponse.message,
                    onTryAgainClickListener = {
                        getSources(category)
                    }
                ))
            } catch (e: Exception) {
                showError.postValue(ErrorView(
                    message = e.message,
                    onTryAgainClickListener = {
                        getSources(category)
                    }
                ))
            } finally {
                showLoading.postValue(false)
            }

        }
    }

    fun getNews(sourceId: String?) {
        viewModelScope.launch {
            showLoading.postValue(true)
            try {
                var news: List<Article?>?
                withContext(Dispatchers.IO) {
                    news = newsRepository.getNewsBySource(sources = sourceId ?: "")
                }
                articlesList.postValue(news)

            } catch (httpException: HttpException) {
                val jsonErrorBodyResponse = httpException.response()?.errorBody()?.string()
                val errorBodyResponse =
                    Gson().fromJson(jsonErrorBodyResponse, ArticlesResponse::class.java)
                errorBodyResponse.message?.let {
                    showError.postValue(
                        ErrorView(
                            message = it,
                            onTryAgainClickListener = {
                                getNews(sourceId)
                            }
                        ))
                }
            } catch (e: Exception) {
                showError.postValue(
                    ErrorView(
                        message = e.message,
                        onTryAgainClickListener = {
                            getNews(sourceId)
                        }
                    ))
            } finally {
                showLoading.postValue(false)
            }
        }


    }

}