package com.infosystest.ui.news

import android.content.Context
import com.infosystest.network.repository.Repository
import com.infosystest.network.schedulers.SchedulerProvider
import com.infosystest.viewmodel.news.NewsListViewModel
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import javax.inject.Singleton

@Module
class NewsListModule(context: Context, private val viewModel: NewsListViewModel) {
    private val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
    private val cache = Cache(context.cacheDir, cacheSize)
    private val repository: Repository = Repository.getRepository(cache)!!
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun getPresenter(): NewsListPresenter {
        return NewsListPresenter(
            viewModel,
            repository,
            schedulerProvider
        )
    }
}