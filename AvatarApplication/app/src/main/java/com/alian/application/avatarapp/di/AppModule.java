package com.alian.application.avatarapp.di;


import android.app.Application;
import android.content.Context;

import com.alian.application.avatarapp.core.scheduler.JobScheduler;
import com.alian.application.avatarapp.core.scheduler.SchedulerIO;
import com.smarthome.core.main.core.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AppModule.Binder.class})
class AppModule {

    @Provides
    @Singleton
    static JobScheduler provideJobScheduler() {
        return new SchedulerIO();
    }

    @Provides
    @Singleton
    @ApplicationContext
    static Context provideContext(Application application) {
        return application;
    }


    @Module
    interface Binder {

    }
}
