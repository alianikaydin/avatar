package com.alian.application.avatarapp.core.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface JobScheduler {
    /**
     * method for obtaining a background JobScheduler
     */
    @NonNull
    Scheduler background();

    /**
     * method for obtaining a computation JobScheduler
     */
    @NonNull
    Scheduler computation();

    /**
     * method for obtaining a main thread JobScheduler
     */
    @NonNull
    Scheduler main();

    /**
     * immediate scheduler, no thread switching
     */
    @NonNull
    Scheduler immediate();
}
