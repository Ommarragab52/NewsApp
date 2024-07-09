package com.example.data.mappers

import com.example.data.network.model.articles.ArticleDto
import com.example.domain.model.Article
import com.google.gson.Gson
//fun <T> Any.to(clazz: Class<T>):T{
//    return Gson().to(clazz)
//}
//fun <T> Any.from(clazz: Class<T>):T{
//    return Gson().from(clazz)
//}
fun ArticleDto.toEntity(): Article {
    return Article(
        id = this.id,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        description = this.description,
        author = this.author,
        content = this.content,
        source = this.source?.toEntity(),
        title = this.title,
        sources = this.sources
    )
}

fun Article.toDto(): ArticleDto {
    return ArticleDto(
        id = this.id,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        description = this.description,
        author = this.author,
        content = this.content,
        source = this.source?.toDto(),
        title = this.title,
        sources = this.sources
    )
}