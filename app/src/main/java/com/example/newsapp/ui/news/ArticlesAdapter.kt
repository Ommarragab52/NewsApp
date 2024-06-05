package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.network.model.articles.Article

class ArticlesAdapter(private var articles: List<Article?>? = null) :
    Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    class ArticlesViewHolder( val itemArticleBinding: ItemArticleBinding) :
        ViewHolder(itemArticleBinding.root) {
        fun bind(article: Article) {
            itemArticleBinding.articleModel = article
            itemArticleBinding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val itemArticleBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articles?.get(position)
        if (article != null) {
            holder.bind(article)
        }
        holder.itemArticleBinding.root.setOnClickListener {
            onArticleClickListener?.onClick(article!!)
        }

    }
    var onArticleClickListener:OnArticleClickListener?=null
    fun interface OnArticleClickListener{
        fun onClick(article: Article)
    }
    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    fun updateData(articles: List<Article?>?) {
        this.articles = articles
        notifyDataSetChanged()
    }
}