package com.alian.application.avatarapp.network.interactors;

import com.alian.application.avatarapp.features.avatar.api.AvatarNetworkInteractor;
import com.alian.application.avatarapp.network.item.RestRequestObjects;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

class AvatarNetworkInteractorImpl implements AvatarNetworkInteractor {

    private RestRequestObjects restRequestObjects;


    @Inject
    AvatarNetworkInteractorImpl(Retrofit retrofit) {
        restRequestObjects = retrofit.create(RestRequestObjects.class);
    }

    @Override
    public Observable<ResponseBody> getImage(int id) {
        Map<String, String> params = new HashMap<>();
        params.put("img", String.valueOf(id));
        return restRequestObjects.getImage(params);
    }

}
