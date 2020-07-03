package com.infosystest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infosystest.databinding.NewsListItemBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private val repoListViewModels = ArrayList<NewsListResponse.RowsEntity>()
    fun updateRepoListViewModels(repoListViewModels: List<NewsListResponse.RowsEntity>) {
        this.repoListViewModels.clear()
        this.repoListViewModels.addAll(repoListViewModels)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return repoListViewModels.size
    }

    override fun getItemId(position: Int): Long {
        return repoListViewModels[position].imageHref.toString().toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repoListViewModel = repoListViewModels[position]
        holder.bind(repoListViewModel)

    }


    class ViewHolder(
        private val binding: NewsListItemBinding
    )// Set in constructor since these do not change
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsListResponse: NewsListResponse.RowsEntity) {
            binding.viewModel = newsListResponse
            binding.rootLayout.setOnClickListener {
                if (binding.bottomContainer.visibility == View.VISIBLE) {
                    binding.bottomContainer.visibility = View.GONE
                } else {
                    binding.bottomContainer.visibility = View.VISIBLE
                }
            }
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