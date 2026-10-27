package com.amazon.mp3.starlight.module;

import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StarlightModule_GetHybridQueueStateProviderFactory implements Factory<HybridQueueStateProvider> {
    private final StarlightModule module;

    public StarlightModule_GetHybridQueueStateProviderFactory(StarlightModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public HybridQueueStateProvider get() {
        return getHybridQueueStateProvider(this.module);
    }

    public static StarlightModule_GetHybridQueueStateProviderFactory create(StarlightModule module) {
        return new StarlightModule_GetHybridQueueStateProviderFactory(module);
    }

    public static HybridQueueStateProvider getHybridQueueStateProvider(StarlightModule instance) {
        return (HybridQueueStateProvider) Preconditions.checkNotNullFromProvides(instance.getHybridQueueStateProvider());
    }
}
