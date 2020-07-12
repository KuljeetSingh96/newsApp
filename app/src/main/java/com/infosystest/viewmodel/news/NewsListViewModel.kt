package com.infosystest.viewmodel.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infosystest.model.news.NewsListResponse

class NewsListViewModel : ViewModel() {
    var errorRetryVisibility: MutableLiveData<Int> = MutableLiveData()
    var newsListVisibility: MutableLiveData<Int> = MutableLiveData()
    var shimmerViewVisibility: MutableLiveData<Int> = MutableLiveData()

    var newsListData: MutableLiveData<List<NewsListResponse.RowsEntity>> = MutableLiveData()
}