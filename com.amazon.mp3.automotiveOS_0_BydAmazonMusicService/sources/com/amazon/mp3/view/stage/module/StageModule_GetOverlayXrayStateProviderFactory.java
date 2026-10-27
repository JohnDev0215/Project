package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.stage.overlay.OverlayXrayStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetOverlayXrayStateProviderFactory implements Factory<OverlayXrayStateProvider> {
    private final StageModule module;

    public StageModule_GetOverlayXrayStateProviderFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public OverlayXrayStateProvider get() {
        return getOverlayXrayStateProvider(this.module);
    }

    public static StageModule_GetOverlayXrayStateProviderFactory create(StageModule module) {
        return new StageModule_GetOverlayXrayStateProviderFactory(module);
    }

    public static OverlayXrayStateProvider getOverlayXrayStateProvider(StageModule instance) {
        return (OverlayXrayStateProvider) Preconditions.checkNotNullFromProvides(instance.getOverlayXrayStateProvider());
    }
}
