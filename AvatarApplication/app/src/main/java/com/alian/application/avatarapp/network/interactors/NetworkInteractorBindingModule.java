package com.alian.application.avatarapp.network.interactors;

import com.alian.application.avatarapp.features.avatar.api.AvatarNetworkInteractor;

import dagger.Binds;
import dagger.Module;

@Module
public interface NetworkInteractorBindingModule {
    @Binds
    AvatarNetworkInteractor bindAvatarNetworkInteractor(AvatarNetworkInteractorImpl impl);
}
