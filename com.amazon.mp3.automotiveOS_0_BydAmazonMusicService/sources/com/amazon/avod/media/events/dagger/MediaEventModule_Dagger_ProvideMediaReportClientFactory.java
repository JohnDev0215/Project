package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.clientapi.MediaReportClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaReportClientFactory implements Factory<MediaReportClient> {
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideMediaReportClientFactory(MediaEventModule_Dagger mediaEventModule_Dagger) {
        this.module = mediaEventModule_Dagger;
    }

    @Override // javax.inject.Provider
    public MediaReportClient get() {
        return (MediaReportClient) Preconditions.checkNotNull(this.module.provideMediaReportClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaReportClient> create(MediaEventModule_Dagger mediaEventModule_Dagger) {
        return new MediaEventModule_Dagger_ProvideMediaReportClientFactory(mediaEventModule_Dagger);
    }
}
