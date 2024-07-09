package com.example.data.mappers

import com.example.data.network.model.sources.SourceDto
import com.example.domain.model.Source

fun SourceDto.toEntity(): Source {
    return Source(
        url = this.url,
        description = this.description,
        name = this.name,
        category = this.category,
        language = this.language,
        country = this.country,
        id = this.id
    )
}

fun Source.toDto(): SourceDto {
    return SourceDto(
        url = this.url,
        description = this.description,
        name = this.name,
        category = this.category,
        language = this.language,
        country = this.country,
        id = this.id ?: ""
    )
}