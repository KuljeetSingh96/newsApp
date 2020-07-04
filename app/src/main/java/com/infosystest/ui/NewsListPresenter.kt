package com.infosystest.ui

import android.view.View
import androidx.annotation.VisibleForTesting
import com.infosystest.network.repository.Repository
import com.infosystest.network.schedulers.SchedulerProvider
import io.reactivex.disposables.Disposable
import java.util.*

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
            ?.map(this::mapNewsResponseToListItems)
            ?.observeOn(getSchedulerProvider().ui())
            ?.subscribe(
                { newsListResponse -> onRepoListSuccess(newsListResponse) },
                { _ -> onNetworkFailure() })
    }
    private fun mapNewsResponseToListItems(newsListResponse: NewsListResponse): List<NewsListResponse.RowsEntity>? {
        val newsItems: List<NewsListResponse.RowsEntity> = ArrayList()
        if(newsListResponse.rows!!.isNotEmpty()){
            var listSize = newsListResponse.rows!!.size
            for (index in 0 until listSize) {
                var rowsEntity = newsListResponse.rows!![index]
                if(rowsEntity.title.isNullOrBlank()&&rowsEntity.description.isNullOrBlank()){
                    continue;
                }
                newsItems.toMutableList().add(rowsEntity)
            }
        }
        return newsItems
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
    internal fun onRepoListSuccess(newsListResponse: List<NewsListResponse.RowsEntity>?) {
        when {
            (newsListResponse!!.isNotEmpty()) -> {
                newsListViewModel.newsListData.postValue(newsListResponse)
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