package com.alian.application.avatarapp.features.avatar.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface AvatarOrchestrator {
    @NonNull
    Observable<ResponseBody> getImage(int id);

}
