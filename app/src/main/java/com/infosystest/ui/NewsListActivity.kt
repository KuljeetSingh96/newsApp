package com.infosystest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosystest.R
import com.infosystest.databinding.NewsListActivityBinding
import javax.inject.Inject

class NewsListActivity : AppCompatActivity() {

    //dagger injection
    @Inject
    lateinit var presenter: NewsListPresenter
    private lateinit var binding: NewsListActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = createRepoListViewModel()
        DaggerNewsListComponent.builder()
            .newsListModule(NewsListModule(this, viewModel))
            .build().inject(this)
        setupBinding(viewModel)
        setupRecyclerView()
        presenter.getNewsList()
        binding.swipeToRefresh.setOnRefreshListener {
            presenter.getNewsList()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun createRepoListViewModel(): NewsListViewModel {
        return ViewModelProviders.of(this).get(NewsListViewModel::class.java)
    }

    private fun setupBinding(viewModel: NewsListViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        val adapter = NewsListAdapter()
        binding.newsList.layoutManager = LinearLayoutManager(this)
        binding.newsList.adapter = adapter
        presenter.newsListViewModel.newsListData.observe(this, Observer<List<NewsListResponse.RowsEntity>> {
            adapter.updateRepoListViewModels(it)
        })
        presenter.newsListViewModel.shimmerViewVisibility.observe(this, Observer<Int> {
            if (it == View.VISIBLE) {
                binding.shimmerViewContainer.startShimmer()
            } else {
                binding.shimmerViewContainer.stopShimmer()
            }
        })
    }

    fun onRetryClicked(view: View) {
        presenter.getNewsList()
    }

    override fun onDestroy() {
        presenter.onDispose()
        super.onDestroy()
    }
}
