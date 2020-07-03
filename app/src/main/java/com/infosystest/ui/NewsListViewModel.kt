package com.infosystest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsListViewModel : ViewModel() {
    var errorRetryVisibility: MutableLiveData<Int> = MutableLiveData()
    var newsListVisibility: MutableLiveData<Int> = MutableLiveData()
    var shimmerViewVisibility: MutableLiveData<Int> = MutableLiveData()

    var newsListData: MutableLiveData<List<NewsListResponse.RowsEntity>> = MutableLiveData()
}