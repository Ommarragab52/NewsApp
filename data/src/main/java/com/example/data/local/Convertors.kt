package com.example.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.network.model.articles.ArticleDto
import com.example.data.network.model.sources.SourceDto
import com.google.gson.Gson
@ProvidedTypeConverter
class Convertors {
    @TypeConverter
    fun formSource(source: SourceDto): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceJson: String): SourceDto {
        return Gson().fromJson(sourceJson, SourceDto::class.java)
    }

    @TypeConverter
    fun formArticle(article: ArticleDto): String {
        return Gson().toJson(article)
    }

    @TypeConverter
    fun toArticle(articleJson: String): ArticleDto {
        return Gson().fromJson(articleJson, ArticleDto::class.java)
    }
}