package com.amazon.avod.media.guice;

import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import com.amazon.avod.media.service.PlayReadyAvodLicensingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvidePlayReadyLicensingServiceFactory implements Factory<PlayReadyLicensingService> {
    private final MediaModule_Dagger module;
    private final Provider<PlayReadyAvodLicensingService> serviceProvider;

    public MediaModule_Dagger_ProvidePlayReadyLicensingServiceFactory(MediaModule_Dagger mediaModule_Dagger, Provider<PlayReadyAvodLicensingService> provider) {
        this.module = mediaModule_Dagger;
        this.serviceProvider = provider;
    }

    @Override // javax.inject.Provider
    public PlayReadyLicensingService get() {
        return (PlayReadyLicensingService) Preconditions.checkNotNull(this.module.providePlayReadyLicensingService(this.serviceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PlayReadyLicensingService> create(MediaModule_Dagger mediaModule_Dagger, Provider<PlayReadyAvodLicensingService> provider) {
        return new MediaModule_Dagger_ProvidePlayReadyLicensingServiceFactory(mediaModule_Dagger, provider);
    }
}
