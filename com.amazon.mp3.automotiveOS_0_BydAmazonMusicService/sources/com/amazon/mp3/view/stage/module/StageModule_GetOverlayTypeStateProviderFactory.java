package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetOverlayTypeStateProviderFactory implements Factory<OverlayTypeStateProvider> {
    private final StageModule module;

    public StageModule_GetOverlayTypeStateProviderFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public OverlayTypeStateProvider get() {
        return getOverlayTypeStateProvider(this.module);
    }

    public static StageModule_GetOverlayTypeStateProviderFactory create(StageModule module) {
        return new StageModule_GetOverlayTypeStateProviderFactory(module);
    }

    public static OverlayTypeStateProvider getOverlayTypeStateProvider(StageModule instance) {
        return (OverlayTypeStateProvider) Preconditions.checkNotNullFromProvides(instance.getOverlayTypeStateProvider());
    }
}
