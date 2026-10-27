package com.amazon.avod.drm;

import com.amazon.avod.drm.playready.DrmSystemImpl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DrmModule_Dagger_ProvideBaseDrmSystemFactory implements Factory<BaseDrmSystem> {
    private final DrmModule_Dagger module;
    private final Provider<DrmSystemImpl> systemProvider;

    public DrmModule_Dagger_ProvideBaseDrmSystemFactory(DrmModule_Dagger drmModule_Dagger, Provider<DrmSystemImpl> provider) {
        this.module = drmModule_Dagger;
        this.systemProvider = provider;
    }

    @Override // javax.inject.Provider
    public BaseDrmSystem get() {
        return (BaseDrmSystem) Preconditions.checkNotNull(this.module.provideBaseDrmSystem(this.systemProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BaseDrmSystem> create(DrmModule_Dagger drmModule_Dagger, Provider<DrmSystemImpl> provider) {
        return new DrmModule_Dagger_ProvideBaseDrmSystemFactory(drmModule_Dagger, provider);
    }
}
