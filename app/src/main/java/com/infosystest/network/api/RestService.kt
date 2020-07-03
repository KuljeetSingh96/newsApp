package com.infosystest.network.api


import com.infosystest.ui.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {
    //FETCH TRENDING NEWS
    @GET(ApiConstants.GET_NEWS_LIST)
     fun getNewsList(): Observable<NewsListResponse>

}