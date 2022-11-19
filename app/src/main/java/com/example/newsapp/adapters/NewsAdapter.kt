package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            if (article.urlToImage != null)
                Glide.with(binding.root).load(article.urlToImage).into(binding.ivArticleImage)
            else
                binding.ivArticleImage.setImageResource(R.drawable.nullimage)
            binding.tvTitle.text = article.title
            if (article.description != null)
                binding.tvDescription.text = article.description
            else
                binding.tvDescription.text = "لا يوجد وصف لهذا الخبر حتي الان"
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source!!.name
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val bindingItem = ItemArticlePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClickAdapter?.onItemClick(position, article)
        }
    }

    var onItemClickAdapter: OnItemClickAdapter? = null

    interface OnItemClickAdapter {
        fun onItemClick(position: Int, article: Article)
    }

    override fun getItemCount(): Int = differ.currentList.size
}