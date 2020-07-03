package com.infosystest.ui

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsListModule::class])
interface NewsListComponent {
    fun inject(newsListActivity: NewsListActivity?)
}