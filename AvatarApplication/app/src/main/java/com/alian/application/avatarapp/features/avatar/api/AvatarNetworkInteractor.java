package com.alian.application.avatarapp.features.avatar.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface AvatarNetworkInteractor {

    Observable<ResponseBody> getImage(int id);


}
