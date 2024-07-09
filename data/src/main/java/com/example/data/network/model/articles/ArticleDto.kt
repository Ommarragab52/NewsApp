package com.example.data.network.model.articles

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.local.Convertors
import com.example.data.network.model.sources.SourceDto
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@TypeConverters(Convertors::class)
data class ArticleDto(
    @PrimaryKey(true)
    var id: Int? = null,
    var publishedAt: String? = null,
    var author: String? = null,
    var urlToImage: String? = null,
    var description: String? = null,
    var title: String? = null,
    var url: String? = null,
    @TypeConverters(Convertors::class)
    var source: SourceDto? = null,
    var content: String? = null,
    var sources: String? = null
) : Parcelable