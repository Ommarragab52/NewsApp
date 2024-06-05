package com.example.newsapp.ui.detailsFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.network.model.articles.Article


class DetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentDetailsBinding
    private lateinit var article: Article
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgument()
        handleAppbar()
    }

    private fun handleAppbar() {
        (requireContext() as AppCompatActivity).supportActionBar?.title =
            article.source?.name?.replaceFirstChar {
                it.uppercase()
            }
    }

    private fun getArgument() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("article", Article::class.java)?.let {
                article = it
            }
        } else {
            article = arguments?.getParcelable("article") ?: Article()
        }
        initData(article)
    }

    private fun initData(article: Article?) {
        viewBinding.title.text = article?.title

    }

}