package com.example.newsapp.ui.news

import android.os.Message

data class ErrorView(
    val message: String?=null,
    val throwable: Throwable?=null,
    val onTryAgainClickListener: OnTryAgainClickListener?=null
)

fun interface OnTryAgainClickListener {
    fun onTryAgainClick()
}

