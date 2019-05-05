package com.alian.application.avatarapp.features.avatar.di;

import com.alian.application.avatarapp.features.avatar.AvatarFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface AvatarContributesModule {
    @ContributesAndroidInjector(modules = AvatarModule.class)
    AvatarFragment bindAvatarFragment();
}
