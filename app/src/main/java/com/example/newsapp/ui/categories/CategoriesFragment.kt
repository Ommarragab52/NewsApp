package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.ui.CONSTANTS
import com.example.newsapp.ui.news.NewsFragment

class CategoriesFragment : Fragment() {
    private lateinit var viewBinding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onButtonClick()
    }

    private fun onButtonClick() {
        viewBinding.sports.setOnClickListener {
            onCategoryClick(CONSTANTS.SPORTS)
        }
        viewBinding.health.setOnClickListener {
            onCategoryClick(CONSTANTS.HEALTH)
        }
        viewBinding.business.setOnClickListener {
            onCategoryClick(CONSTANTS.BUSINESS)
        }
        viewBinding.politics.setOnClickListener {
            onCategoryClick(CONSTANTS.POLITICS)
        }
        viewBinding.environment.setOnClickListener {
            onCategoryClick(CONSTANTS.ENTERTAINMENT)
        }
        viewBinding.science.setOnClickListener {
            onCategoryClick(CONSTANTS.SCIENCE)
        }

    }

    private fun onCategoryClick(category: String) {
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToNewsFragment(category)
        findNavController().navigate(action)

    }

}