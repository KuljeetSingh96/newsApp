package com.infosystest.ui

import android.content.Context
import com.infosystest.network.repository.Repository
import com.infosystest.network.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsListModule(context: Context, private val viewModel: NewsListViewModel) {
    private val repository: Repository = Repository.getRepository(context)!!
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun getPresenter(): NewsListPresenter {
        return NewsListPresenter(viewModel, repository,schedulerProvider)
    }
}