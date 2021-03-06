package com.alian.application.avatarapp.network.di;

import android.content.Context;

import com.alian.application.avatarapp.BuildConfig;
import com.alian.application.avatarapp.core.scheduler.JobExecutor;
import com.alian.application.avatarapp.network.interactors.NetworkInteractorBindingModule;
import com.alian.application.avatarapp.utils.NetworkConnectionValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smarthome.core.main.core.di.scope.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes ={
        NetworkInteractorBindingModule.class})

public class NetworkModule {


    private Context context;

    @Singleton
    @Provides
    Retrofit getImagesBaseRetrofit(@ApplicationContext Context context) {
        this.context = context;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        File httpCacheDirectory = new File(context.getFilesDir(),"avatar_direction");
        if(!httpCacheDirectory.exists()){
            httpCacheDirectory.mkdirs();
        }
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR).cache(cache).build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .callbackExecutor(new JobExecutor())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callbackExecutor(new JobExecutor())
                .build();
    }

    private Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365,TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if(NetworkConnectionValidator.isNetworkAvailable(context)){
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkConnectionValidator.isNetworkAvailable(context)) {
                int maxAge = 60  * 60; // read from cache
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };


}
