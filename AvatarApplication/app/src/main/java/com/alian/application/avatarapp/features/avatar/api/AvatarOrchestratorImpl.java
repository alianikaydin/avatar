package com.alian.application.avatarapp.features.avatar.api;

import android.support.annotation.NonNull;

import com.alian.application.avatarapp.core.scheduler.JobScheduler;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class AvatarOrchestratorImpl implements AvatarOrchestrator {
    private @NonNull
    AvatarNetworkInteractor avatarNetworkInteractor;
    private final @NonNull
    JobScheduler jobScheduler;

    @Inject
    AvatarOrchestratorImpl(@NonNull AvatarNetworkInteractor avatarNetworkInteractor,
                           @NonNull JobScheduler jobScheduler) {
        this.avatarNetworkInteractor = avatarNetworkInteractor;
        this.jobScheduler = jobScheduler;
    }

    @NonNull
    @Override
    public Observable<ResponseBody> getImage(int id) {
        return avatarNetworkInteractor.getImage(id)
                .subscribeOn(jobScheduler.background())
                .observeOn(jobScheduler.main());
    }
}
