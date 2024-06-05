package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.network.ApiManger
import com.example.newsapp.network.model.articles.Article
import com.example.newsapp.network.model.articles.ArticlesResponse
import com.example.newsapp.network.model.sources.Source
import com.example.newsapp.network.model.sources.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<ErrorView>()
    val sourcesList = MutableLiveData<List<Source?>?>()
    val articlesList = MutableLiveData<List<Article?>?>()
    fun getSources(category: String?) {
        showLoading.postValue(true)
        ApiManger.getApis().getSources(category = category)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showLoading.postValue(false)
                    if (response.isSuccessful) {
                        //show tabs in fragment
                        sourcesList.postValue(response.body()?.sources)
                    } else {
                        val errorBodyJsonString = response.errorBody()?.string()
                        val responseError =
                            Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                        responseError.message?.let {
                            showError.postValue(ErrorView(
                                message = it,
                                onTryAgainClickListener = {
                                    getSources(category)
                                }
                            ))
                        }
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showLoading.postValue(false)
                    t.localizedMessage?.let {
                        showError.postValue(ErrorView(
                            message = it,
                            onTryAgainClickListener = {
                                getSources(category)
                            }
                        ))
                    }
                }
            }
            )
    }
    fun getNews(sourceId: String?) {
        showLoading.postValue(true)
        if (sourceId != null) {
            ApiManger.getApis().getNews(source = sourceId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        showLoading.postValue(false)
                        if (response.isSuccessful) {
                            // show data in RecyclerView
                            articlesList.postValue(response.body()?.articles)
                        } else {
                            val jsonErrorBodyResponse = response.errorBody()?.string()
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
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        showLoading.postValue(false)
                        t.localizedMessage?.let {
                            showError.postValue(
                                ErrorView(
                                    message = it,
                                    onTryAgainClickListener = {
                                        getNews(sourceId)

                                    }
                                ))
                        }
                    }
                })
        }
    }

}