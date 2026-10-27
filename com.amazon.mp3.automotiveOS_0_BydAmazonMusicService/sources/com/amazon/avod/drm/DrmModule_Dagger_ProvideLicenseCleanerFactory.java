package com.amazon.avod.drm;

import com.amazon.avod.media.framework.MediaComponent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DrmModule_Dagger_ProvideLicenseCleanerFactory implements Factory<MediaComponent> {
    private final Provider<DrmLicenseCleaner> cleanerProvider;
    private final DrmModule_Dagger module;

    public DrmModule_Dagger_ProvideLicenseCleanerFactory(DrmModule_Dagger drmModule_Dagger, Provider<DrmLicenseCleaner> provider) {
        this.module = drmModule_Dagger;
        this.cleanerProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaComponent get() {
        return (MediaComponent) Preconditions.checkNotNull(this.module.provideLicenseCleaner(this.cleanerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaComponent> create(DrmModule_Dagger drmModule_Dagger, Provider<DrmLicenseCleaner> provider) {
        return new DrmModule_Dagger_ProvideLicenseCleanerFactory(drmModule_Dagger, provider);
    }
}
