package com.alian.application.avatarapp.core.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SchedulerIO implements JobScheduler {

    public static SchedulerIO getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public @NonNull
    Scheduler background() {
        return Schedulers.io();
    }

    @Override
    public @NonNull
    Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public @NonNull
    Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler immediate() {
        return Schedulers.trampoline();
    }

    private static final class InstanceHolder {
        static final SchedulerIO INSTANCE = new SchedulerIO();
    }
}

