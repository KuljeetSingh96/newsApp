package com.infosystest.network.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler

interface ISchedulerProvider {
    @NonNull
     fun computation(): Scheduler

    @NonNull
     fun ui(): Scheduler

    @NonNull
    fun io(): Scheduler
}