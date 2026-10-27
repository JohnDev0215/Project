package com.amazon.avod.drm;

import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DrmLicenseCleaner_Factory implements Factory<DrmLicenseCleaner> {
    private final Provider<BaseDrmSystem> drmSystemProvider;
    private final Provider<ExecutorService> executorProvider;
    private final Provider<PlayReadyLicensingService> licensingServiceProvider;

    public DrmLicenseCleaner_Factory(Provider<BaseDrmSystem> provider, Provider<PlayReadyLicensingService> provider2, Provider<ExecutorService> provider3) {
        this.drmSystemProvider = provider;
        this.licensingServiceProvider = provider2;
        this.executorProvider = provider3;
    }

    @Override // javax.inject.Provider
    public DrmLicenseCleaner get() {
        return new DrmLicenseCleaner(this.drmSystemProvider.get(), this.licensingServiceProvider.get(), this.executorProvider.get());
    }

    public static Factory<DrmLicenseCleaner> create(Provider<BaseDrmSystem> provider, Provider<PlayReadyLicensingService> provider2, Provider<ExecutorService> provider3) {
        return new DrmLicenseCleaner_Factory(provider, provider2, provider3);
    }
}
