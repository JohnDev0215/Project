package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory implements Factory<StageOverflowMenuSecondaryIconStateProvider> {
    private final StageModule module;

    public StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public StageOverflowMenuSecondaryIconStateProvider get() {
        return getStageOverflowMenuSecondaryIconStateProvider(this.module);
    }

    public static StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory create(StageModule module) {
        return new StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory(module);
    }

    public static StageOverflowMenuSecondaryIconStateProvider getStageOverflowMenuSecondaryIconStateProvider(StageModule instance) {
        return (StageOverflowMenuSecondaryIconStateProvider) Preconditions.checkNotNullFromProvides(instance.getStageOverflowMenuSecondaryIconStateProvider());
    }
}
