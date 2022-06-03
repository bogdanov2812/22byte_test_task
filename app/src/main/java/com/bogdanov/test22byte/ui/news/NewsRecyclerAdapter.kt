package com.bogdanov.test22byte.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.ItemSnapshotList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.test22byte.R
import com.bogdanov.test22byte.databinding.ItemNewsBinding
import com.bogdanov.test22byte.domain.model.Article

class NewsRecyclerAdapter: PagingDataAdapter<Article, NewsRecyclerAdapter.NewsViewHolder>(ArticleDiffItemCallback) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Article?){
            binding.title.text = item?.title
        }

    }

    companion object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(itemBinding)
    }
}