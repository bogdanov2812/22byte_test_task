package com.bogdanov.test22byte.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.test22byte.databinding.PagingFooterLayoutBinding

class FooterLoadStateAdapter(
    private val tryAgainAction: () -> Unit
) : LoadStateAdapter<FooterLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PagingFooterLayoutBinding.inflate(inflater, parent, false)
        return LoadStateViewHolder(binding, tryAgainAction)
    }

    class LoadStateViewHolder(
        private val binding: PagingFooterLayoutBinding,
        private val tryAgainAction: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            tryAgainButton.isVisible = loadState is LoadState.Error

            progressBar.isVisible = loadState is LoadState.Loading

        }
    }

}