package com.infosystest.network.repository

import android.content.Context
import com.infosystest.network.api.RestService
import com.infosystest.ui.NewsListResponse
import io.reactivex.Observable
/**
 * Created by kuljeetsingh
 * <p>
 * Repository class is (will be) our single source of truth for all data.
 * The repository is essentially a proxy / collection of all data sources.
 * The repository contains business logic to determine which source of data should be used - network, database or cache
 * (as the data consumers should not need to be concerned where data comes from save for perhaps forcing
 * a refresh from the server.)
 * The repository may be broken down into separate components if the class becomes too large.
 */
class Repository(context: Context) {

    companion object {
        @Volatile
        private var repository: Repository? = null
        private var restApi: RestService? = null

        fun getRepository(context: Context): Repository? {
            if (repository == null) {
                synchronized(Repository::class.java) {
                    if (repository == null) repository = Repository(context)
                }
            }

            return repository
        }
    }

    init {
        if (repository != null) {
            throw RuntimeException("Use getRepository() method to get the single instance of this class.")
        }
        restApi = RestApiProvider.getRestApi(context)
    }


    fun getNewsList(
      ): Observable<List<NewsListResponse>>? {
        return restApi?.getNewsList()
    }
}