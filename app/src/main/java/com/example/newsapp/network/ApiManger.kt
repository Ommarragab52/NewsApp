package com.example.newsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManger {
    private var retrofit :Retrofit?=null
    private fun getInstance():Retrofit{
        if (retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    fun getApis():WebServices{
      return  getInstance().create(WebServices::class.java)
    }
}