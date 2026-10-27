package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.DeviceInfoProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideDeviceInfoProviderFactory implements Factory<DeviceInfoProvider> {
    private final PlatformProvidersModule module;

    public PlatformProvidersModule_ProvideDeviceInfoProviderFactory(PlatformProvidersModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public DeviceInfoProvider get() {
        return provideDeviceInfoProvider(this.module);
    }

    public static PlatformProvidersModule_ProvideDeviceInfoProviderFactory create(PlatformProvidersModule module) {
        return new PlatformProvidersModule_ProvideDeviceInfoProviderFactory(module);
    }

    public static DeviceInfoProvider provideDeviceInfoProvider(PlatformProvidersModule instance) {
        return (DeviceInfoProvider) Preconditions.checkNotNullFromProvides(instance.provideDeviceInfoProvider());
    }
}
