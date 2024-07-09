package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String? = null,
    var country: String? = null,
    var name: String? = null,
    var description: String? = null,
    var language: String? = null,
    var category: String? = null,
    var url: String? = null
) : Parcelable