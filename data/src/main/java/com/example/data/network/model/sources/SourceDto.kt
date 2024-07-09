package com.example.data.network.model.sources

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.local.Convertors
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@TypeConverters(Convertors::class)
data class SourceDto(
    @PrimaryKey(false)
    val id: String = "",
    var country: String? = null,
    var name: String? = null,
    var description: String? = null,
    var language: String? = null,
    var category: String? = null,
    var url: String? = null
) : Parcelable