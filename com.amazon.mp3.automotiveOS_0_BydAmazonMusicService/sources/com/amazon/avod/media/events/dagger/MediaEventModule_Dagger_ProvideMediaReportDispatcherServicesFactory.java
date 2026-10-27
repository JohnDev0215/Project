package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaReportDispatcherServicesFactory implements Factory<MediaReportDispatcherServices> {
    private final Provider<MediaReportClient> mediaReportClientProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideMediaReportDispatcherServicesFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaReportClient> provider) {
        this.module = mediaEventModule_Dagger;
        this.mediaReportClientProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaReportDispatcherServices get() {
        return (MediaReportDispatcherServices) Preconditions.checkNotNull(this.module.provideMediaReportDispatcherServices(this.mediaReportClientProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaReportDispatcherServices> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaReportClient> provider) {
        return new MediaEventModule_Dagger_ProvideMediaReportDispatcherServicesFactory(mediaEventModule_Dagger, provider);
    }
}
