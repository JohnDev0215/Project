package com.amazon.avod.media.guice;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideExecutorServiceFactory implements Factory<ExecutorService> {
    private final Provider<MediaSystemSharedContext> contextProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideExecutorServiceFactory(MediaModule_Dagger mediaModule_Dagger, Provider<MediaSystemSharedContext> provider) {
        this.module = mediaModule_Dagger;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ExecutorService get() {
        return (ExecutorService) Preconditions.checkNotNull(this.module.provideExecutorService(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExecutorService> create(MediaModule_Dagger mediaModule_Dagger, Provider<MediaSystemSharedContext> provider) {
        return new MediaModule_Dagger_ProvideExecutorServiceFactory(mediaModule_Dagger, provider);
    }
}
