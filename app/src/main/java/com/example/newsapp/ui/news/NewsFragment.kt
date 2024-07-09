package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Source
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.CONSTANTS
import com.example.newsapp.ui.showMessage
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    private var category: String? = null
    private lateinit var adapter: ArticlesAdapter
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category = arguments?.getString(CONSTANTS.CATEGORY)
        val activity = requireActivity() as? AppCompatActivity
        activity?.supportActionBar?.title = category?.replaceFirstChar { it.uppercase() }
        initViews()
        initObservers()
        viewModel.getSources(category)
        onArticleClick()
    }

    private fun onArticleClick() {
        adapter.onArticleClickListener = ArticlesAdapter.OnArticleClickListener { article ->
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article = article)
            findNavController().navigate(action)
        }
    }

    private fun initObservers() {
//        viewModel.showLoading.observe(viewLifecycleOwner
//        ) {
//            viewBinding.progressBar.isVisible = it
//        }
        viewModel.sourcesList.observe(viewLifecycleOwner) {
            bindTabs(it)
        }
        viewModel.articlesList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            handleError(it)
        }
    }

    private fun initViews() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
        adapter = ArticlesAdapter()
        viewBinding.recyclerView.adapter = adapter
    }


    private fun bindTabs(sources: List<Source?>?) {
        if (sources == null) return
        sources.forEach { source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
//                    tab.setCustomView(R.layout.tab_custom_view)
//                    tab.customView?.findViewById<TextView>(R.id.tab_title)?.text=source?.name
            viewBinding.tabLayout.addTab(tab)
        }
        viewBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                viewModel.getNews(source.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                viewModel.getNews(source.id)
            }
        })
        viewBinding.tabLayout.getTabAt(0)?.select()
    }


    private fun handleError(errorView: ErrorView) {
        showMessage(
            errorView.message ?: "something went wrong",
            posActionName = "Try Again",
            posAction = { dialog, _ ->
                errorView.onTryAgainClickListener?.onTryAgainClick()
                dialog.dismiss()
            },
            negActionName = "Cancel",
            neAction = { dialog, _ ->
                dialog.dismiss()
            }
        )
    }
}