package com.amazon.avod.media.guice;

import com.amazon.avod.media.framework.platform.power.AndroidLockFactory;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvidePowerManagementLockFactoryFactory implements Factory<PowerManagementLockFactory> {
    private final Provider<AndroidLockFactory> factoryProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvidePowerManagementLockFactoryFactory(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidLockFactory> provider) {
        this.module = mediaModule_Dagger;
        this.factoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public PowerManagementLockFactory get() {
        return (PowerManagementLockFactory) Preconditions.checkNotNull(this.module.providePowerManagementLockFactory(this.factoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PowerManagementLockFactory> create(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidLockFactory> provider) {
        return new MediaModule_Dagger_ProvidePowerManagementLockFactoryFactory(mediaModule_Dagger, provider);
    }
}
