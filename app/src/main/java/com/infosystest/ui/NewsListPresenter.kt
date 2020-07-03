package com.infosystest.ui

import android.view.View
import androidx.annotation.VisibleForTesting
import com.infosystest.network.repository.Repository
import com.infosystest.network.schedulers.SchedulerProvider
import io.reactivex.disposables.Disposable

class NewsListPresenter(
    val newsListViewModel: NewsListViewModel,
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) {
    private var disposable: Disposable? = null

    fun getNewsList() {
        loadShimmerView()
        disposable = repository.getNewsList(
        )?.subscribeOn(getSchedulerProvider().io())
            ?.observeOn(getSchedulerProvider().ui())
            ?.subscribe(
                { newsListResponse -> onRepoListSuccess(newsListResponse) },
                { _ -> onNetworkFailure() })
    }

    private fun loadShimmerView() {
        newsListViewModel.shimmerViewVisibility.value = View.VISIBLE
        newsListViewModel.newsListVisibility.value = View.GONE
        newsListViewModel.errorRetryVisibility.value = View.GONE
    }

    @VisibleForTesting
    internal fun onNetworkFailure() {
        loadRetryView()
    }

    @VisibleForTesting
    internal fun loadRetryView() {
        newsListViewModel.shimmerViewVisibility.value = View.GONE
        newsListViewModel.newsListVisibility.value = View.GONE
        newsListViewModel.errorRetryVisibility.value = View.VISIBLE
    }

    @VisibleForTesting
    internal fun onRepoListSuccess(newsListResponse: NewsListResponse) {
        when {
            (newsListResponse.rows!!.isNotEmpty()) -> {
                newsListViewModel.newsListData.postValue(newsListResponse.rows)
                loadListView()
            }
            else -> loadRetryView()
        }
    }

    @VisibleForTesting
    internal fun loadListView() {
        newsListViewModel.shimmerViewVisibility.value = View.GONE
        newsListViewModel.newsListVisibility.value = View.VISIBLE
        newsListViewModel.errorRetryVisibility.value = View.GONE
    }


    @VisibleForTesting
    internal fun getSchedulerProvider(): SchedulerProvider {
        return schedulerProvider
    }

    fun onDispose() {
        disposable?.dispose()
    }
}