package com.amazon.avod.media.events.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideScheduledExecutorServiceFactory implements Factory<ScheduledExecutorService> {
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideScheduledExecutorServiceFactory(MediaEventModule_Dagger mediaEventModule_Dagger) {
        this.module = mediaEventModule_Dagger;
    }

    @Override // javax.inject.Provider
    public ScheduledExecutorService get() {
        return (ScheduledExecutorService) Preconditions.checkNotNull(this.module.provideScheduledExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ScheduledExecutorService> create(MediaEventModule_Dagger mediaEventModule_Dagger) {
        return new MediaEventModule_Dagger_ProvideScheduledExecutorServiceFactory(mediaEventModule_Dagger);
    }
}
