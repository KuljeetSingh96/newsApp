package com.infosystest.network.schedulers

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


// Prevent direct instantiation.
open class SchedulerProvider : ISchedulerProvider {

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    @NonNull
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @NonNull
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    companion object {

        @Nullable
        private var schedulerProvider: SchedulerProvider? = null

            fun getInstance(): SchedulerProvider {
                if (schedulerProvider == null) {
                    schedulerProvider = SchedulerProvider()
                }
                return schedulerProvider as SchedulerProvider
            }
    }
}