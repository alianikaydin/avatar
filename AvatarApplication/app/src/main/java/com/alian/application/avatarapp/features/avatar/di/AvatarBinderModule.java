package com.alian.application.avatarapp.features.avatar.di;

import com.alian.application.avatarapp.features.avatar.AvatarContract;
import com.alian.application.avatarapp.features.avatar.AvatarFragment;
import com.alian.application.avatarapp.features.avatar.AvatarPresenter;

import dagger.Binds;
import dagger.Module;

@Module
interface AvatarBinderModule {
    @Binds
    AvatarContract.View bindAvatarView(AvatarFragment impl);

    @Binds
    AvatarContract.Presenter bindAvatarPresenter(AvatarPresenter impl);
}
