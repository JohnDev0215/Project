package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetOverlayLyricsStateProviderFactory implements Factory<OverlayLyricsStateProvider> {
    private final StageModule module;

    public StageModule_GetOverlayLyricsStateProviderFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public OverlayLyricsStateProvider get() {
        return getOverlayLyricsStateProvider(this.module);
    }

    public static StageModule_GetOverlayLyricsStateProviderFactory create(StageModule module) {
        return new StageModule_GetOverlayLyricsStateProviderFactory(module);
    }

    public static OverlayLyricsStateProvider getOverlayLyricsStateProvider(StageModule instance) {
        return (OverlayLyricsStateProvider) Preconditions.checkNotNullFromProvides(instance.getOverlayLyricsStateProvider());
    }
}
