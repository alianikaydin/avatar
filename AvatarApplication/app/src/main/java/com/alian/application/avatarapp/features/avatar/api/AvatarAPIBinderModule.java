package com.alian.application.avatarapp.features.avatar.api;

import dagger.Binds;
import dagger.Module;

@Module
public interface AvatarAPIBinderModule {
    @Binds
    AvatarOrchestrator bindAvatarOrchestor (AvatarOrchestratorImpl impl);
}
