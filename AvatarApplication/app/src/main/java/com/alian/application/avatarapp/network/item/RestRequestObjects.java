package com.alian.application.avatarapp.network.item;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestRequestObjects {
    @GET("/300")
    Observable<ResponseBody> getImage(@QueryMap Map<String, String> params);

}
