package com.infosystest.ui.news

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsListModule::class])
interface NewsListComponent {
    fun inject(newsListFragment: NewsListFragment?)
}