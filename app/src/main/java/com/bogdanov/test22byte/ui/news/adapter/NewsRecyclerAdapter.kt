package com.bogdanov.test22byte.ui.news.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bogdanov.test22byte.R
import com.bogdanov.test22byte.databinding.ItemNewsBinding
import com.bogdanov.test22byte.domain.model.Article
import com.bumptech.glide.Glide

class NewsRecyclerAdapter(
    private val openUrl: (String) -> Unit
): PagingDataAdapter<Article, NewsRecyclerAdapter.NewsViewHolder>(ArticleDiffItemCallback) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding, private val openUrl: (String) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Article?){
            binding.linearLayout.setOnClickListener { openUrl(item?.link ?: "") }
            if (item != null){
                binding.title.text = item.title
                binding.subTitle.text = item.subtitle
                binding.author.text = item.publisher
                binding.date.text = removeSecondsInTime(item.date)

                Glide.with(itemView)
                    .load(Uri.parse(item.photo))
                    .centerCrop()
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .placeholder(R.drawable.ic_baseline_downloading_24)
                    .into(binding.photo)

            }
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
        return NewsViewHolder(itemBinding, openUrl)
    }

    fun removeSecondsInTime(date: String) = date.substringBeforeLast(":")
}