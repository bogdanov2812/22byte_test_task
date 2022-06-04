package com.bogdanov.test22byte.ui.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.test22byte.R
import com.bogdanov.test22byte.databinding.FragmentNewsBinding
import com.bogdanov.test22byte.ui.news.adapter.FooterLoadStateAdapter
import com.bogdanov.test22byte.ui.news.adapter.NewsRecyclerAdapter
import com.bogdanov.test22byte.util.CustomItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()

    private val newsAdapter = NewsRecyclerAdapter{
        findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment, bundleOf("url" to it))
    }

    private val footerAdapter = FooterLoadStateAdapter {newsAdapter.retry()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.item_divider, null))

        binding.newsList.apply {
            adapter = newsAdapter.withLoadStateFooter(footerAdapter)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(CustomItemDecoration(resources.getDimension(R.dimen.recycler_view_items_margin).toInt()))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.articlesFlow.distinctUntilChanged().collectLatest {
                newsAdapter.submitData( it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}