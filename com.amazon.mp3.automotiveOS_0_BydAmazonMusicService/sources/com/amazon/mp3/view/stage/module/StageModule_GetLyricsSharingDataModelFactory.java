package com.amazon.mp3.view.stage.module;

import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StageModule_GetLyricsSharingDataModelFactory implements Factory<LyricsSharingDataModel> {
    private final StageModule module;

    public StageModule_GetLyricsSharingDataModelFactory(StageModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public LyricsSharingDataModel get() {
        return getLyricsSharingDataModel(this.module);
    }

    public static StageModule_GetLyricsSharingDataModelFactory create(StageModule module) {
        return new StageModule_GetLyricsSharingDataModelFactory(module);
    }

    public static LyricsSharingDataModel getLyricsSharingDataModel(StageModule instance) {
        return (LyricsSharingDataModel) Preconditions.checkNotNullFromProvides(instance.getLyricsSharingDataModel());
    }
}
