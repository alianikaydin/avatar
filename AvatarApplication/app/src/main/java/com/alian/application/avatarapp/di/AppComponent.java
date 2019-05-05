package com.alian.application.avatarapp.di;
import com.alian.application.avatarapp.network.di.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AppModule.class,
        ContributeModule.class,
        NetworkModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
})
@Singleton
public interface AppComponent extends BaseAppComponent {

    @Component.Builder
    interface Builder extends BaseAppComponent.Builder<AppComponent, Builder> {

    }
}
