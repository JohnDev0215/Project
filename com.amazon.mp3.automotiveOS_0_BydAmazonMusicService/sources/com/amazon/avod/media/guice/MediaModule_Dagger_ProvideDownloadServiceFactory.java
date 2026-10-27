package com.amazon.avod.media.guice;

import com.amazon.avod.media.downloadservice.DownloadService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideDownloadServiceFactory implements Factory<DownloadService> {
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideDownloadServiceFactory(MediaModule_Dagger mediaModule_Dagger) {
        this.module = mediaModule_Dagger;
    }

    @Override // javax.inject.Provider
    public DownloadService get() {
        return (DownloadService) Preconditions.checkNotNull(this.module.provideDownloadService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DownloadService> create(MediaModule_Dagger mediaModule_Dagger) {
        return new MediaModule_Dagger_ProvideDownloadServiceFactory(mediaModule_Dagger);
    }
}
