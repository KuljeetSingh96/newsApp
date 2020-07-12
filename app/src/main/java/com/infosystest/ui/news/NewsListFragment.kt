package com.infosystest.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosystest.databinding.NewsListFragmentBinding
import com.infosystest.model.news.NewsListResponse
import com.infosystest.viewmodel.news.NewsListViewModel
import javax.inject.Inject

class NewsListFragment : Fragment() {
    private lateinit var viewDataBinding: NewsListFragmentBinding
    private lateinit var newsViewModel: NewsListViewModel
    @Inject
    lateinit var presenter: NewsListPresenter

    companion object {

        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        setupBinding(inflater, container)
        DaggerNewsListComponent.builder()
            .newsListModule(
                NewsListModule(
                    context!!,
                    newsViewModel
                )
            )
            .build().inject(this)
        return viewDataBinding.root
    }

    private fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?) {
        viewDataBinding = NewsListFragmentBinding.inflate(inflater, container, false).apply {
            newsViewModel =
                ViewModelProviders.of(this@NewsListFragment).get(NewsListViewModel::class.java)
            viewModel = newsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        presenter.getNewsList()
        viewDataBinding.swipeToRefresh.setOnRefreshListener {
            presenter.getNewsList()
            viewDataBinding.swipeToRefresh.isRefreshing = false
        }
        viewDataBinding.retryButton.setOnClickListener {
            onRetryClicked()
        }
    }

    private fun setupRecyclerView() {
        val adapter = NewsListAdapter()
        viewDataBinding.newsList.layoutManager = LinearLayoutManager(context)
        viewDataBinding.newsList.adapter = adapter
        presenter.newsListViewModel.newsListData.observe(this, Observer<List<NewsListResponse.RowsEntity>> {
            adapter.updateRepoListViewModels(it)
        })
        presenter.newsListViewModel.shimmerViewVisibility.observe(this, Observer<Int> {
            if (it == View.VISIBLE) {
                viewDataBinding.shimmerViewContainer.startShimmer()
            } else {
                viewDataBinding.shimmerViewContainer.stopShimmer()
            }
        })
    }

    private fun onRetryClicked() {
        presenter.getNewsList()
    }

    override fun onDestroy() {
        presenter.onDispose()
        super.onDestroy()
    }
}
