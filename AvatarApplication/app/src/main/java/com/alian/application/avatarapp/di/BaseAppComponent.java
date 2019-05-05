package com.alian.application.avatarapp.di;


import android.app.Application;

import com.alian.application.avatarapp.AvatarApplication;

import dagger.BindsInstance;

public interface BaseAppComponent {

    void inject(AvatarApplication application);


    /**
     * @param <B> actual Builder class
     * @param <C> actual Component class
     */
    interface Builder<C extends BaseAppComponent, B extends Builder<C, B>> {

        @BindsInstance
        B application(Application application);

        C build();
    }
}
