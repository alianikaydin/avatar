package com.alian.application.avatarapp.features.avatar.di;

import android.view.View;

import com.alian.application.avatarapp.features.avatar.AvatarFragment;
import com.alian.application.avatarapp.features.avatar.api.AvatarAPIBinderModule;

import org.simpleframework.xml.Root;

import dagger.Module;
import dagger.Provides;
@Module(includes = {AvatarBinderModule.class,
        AvatarAPIBinderModule.class})
class AvatarModule {
    @Provides
    @Root
    static View provideRootView(AvatarFragment fragment) {
        return fragment.getView();
    }


}
