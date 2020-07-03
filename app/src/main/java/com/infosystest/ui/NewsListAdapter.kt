package com.infosystest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infosystest.databinding.NewsListItemBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private val newsListViewModels = ArrayList<NewsListResponse.RowsEntity>()
    fun updateRepoListViewModels(repoListViewModels: List<NewsListResponse.RowsEntity>) {
        this.newsListViewModels.clear()
        this.newsListViewModels.addAll(repoListViewModels)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return newsListViewModels.size
    }

    override fun getItemId(position: Int): Long {
        return newsListViewModels[position].imageHref.toString().toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repoListViewModel = newsListViewModels[position]
        holder.bind(repoListViewModel)

    }


    class ViewHolder(
        private val binding: NewsListItemBinding
    )// Set in constructor since these do not change
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsListResponse: NewsListResponse.RowsEntity) {
            binding.viewModel = newsListResponse
        }

        companion object {

            fun createViewHolder(
                parent: ViewGroup
            ): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}