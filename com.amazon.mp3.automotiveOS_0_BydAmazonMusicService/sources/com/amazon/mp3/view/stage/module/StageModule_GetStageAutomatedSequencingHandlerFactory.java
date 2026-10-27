package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetStageAutomatedSequencingHandlerFactory implements Factory<StageAutomatedSequencingHandler> {
    private final StageModule module;

    public StageModule_GetStageAutomatedSequencingHandlerFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public StageAutomatedSequencingHandler get() {
        return getStageAutomatedSequencingHandler(this.module);
    }

    public static StageModule_GetStageAutomatedSequencingHandlerFactory create(StageModule module) {
        return new StageModule_GetStageAutomatedSequencingHandlerFactory(module);
    }

    public static StageAutomatedSequencingHandler getStageAutomatedSequencingHandler(StageModule instance) {
        return (StageAutomatedSequencingHandler) Preconditions.checkNotNullFromProvides(instance.getStageAutomatedSequencingHandler());
    }
}
