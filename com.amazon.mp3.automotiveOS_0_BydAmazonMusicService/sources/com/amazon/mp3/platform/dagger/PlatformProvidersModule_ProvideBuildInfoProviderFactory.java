package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.BuildInfoProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideBuildInfoProviderFactory implements Factory<BuildInfoProvider> {
    private final PlatformProvidersModule module;

    public PlatformProvidersModule_ProvideBuildInfoProviderFactory(PlatformProvidersModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public BuildInfoProvider get() {
        return provideBuildInfoProvider(this.module);
    }

    public static PlatformProvidersModule_ProvideBuildInfoProviderFactory create(PlatformProvidersModule module) {
        return new PlatformProvidersModule_ProvideBuildInfoProviderFactory(module);
    }

    public static BuildInfoProvider provideBuildInfoProvider(PlatformProvidersModule instance) {
        return (BuildInfoProvider) Preconditions.checkNotNullFromProvides(instance.provideBuildInfoProvider());
    }
}
