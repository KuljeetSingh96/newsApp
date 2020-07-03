package com.infosystest.network.repository

import android.content.Context
import com.infosystest.network.api.ApiConstants
import com.infosystest.network.api.RestService
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApiProvider {
companion object{

    fun getRestApi(context: Context): RestService {
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val cache = Cache(context.cacheDir, cacheSize)
        val okHttpClient = getOkHttpClient(cache)
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.REPO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(RestService::class.java)
    }

    private fun getOkHttpClient(cache: Cache): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.cache(cache)
        builder.interceptors().add(httpLoggingInterceptor)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder .addNetworkInterceptor(networkCacheInterceptor)
        return builder.build()
    }
    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        var cacheControl = CacheControl.Builder()
            .maxAge(2, TimeUnit.HOURS)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

}