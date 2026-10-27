package com.amazon.avod.media.guice;

import com.amazon.avod.drm.widevine.WidevineLicensingService;
import com.amazon.avod.media.service.WidevineAvodLicensingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideWidevineLicensingServiceFactory implements Factory<WidevineLicensingService> {
    private final MediaModule_Dagger module;
    private final Provider<WidevineAvodLicensingService> serviceProvider;

    public MediaModule_Dagger_ProvideWidevineLicensingServiceFactory(MediaModule_Dagger mediaModule_Dagger, Provider<WidevineAvodLicensingService> provider) {
        this.module = mediaModule_Dagger;
        this.serviceProvider = provider;
    }

    @Override // javax.inject.Provider
    public WidevineLicensingService get() {
        return (WidevineLicensingService) Preconditions.checkNotNull(this.module.provideWidevineLicensingService(this.serviceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WidevineLicensingService> create(MediaModule_Dagger mediaModule_Dagger, Provider<WidevineAvodLicensingService> provider) {
        return new MediaModule_Dagger_ProvideWidevineLicensingServiceFactory(mediaModule_Dagger, provider);
    }
}
