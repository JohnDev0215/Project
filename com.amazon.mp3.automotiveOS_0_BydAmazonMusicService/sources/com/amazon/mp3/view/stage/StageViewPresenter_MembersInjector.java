package com.amazon.mp3.view.stage;

import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayXrayStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class StageViewPresenter_MembersInjector implements MembersInjector<StageViewPresenter> {
    public static void injectOverlayTypeStateProvider(StageViewPresenter instance, OverlayTypeStateProvider overlayTypeStateProvider) {
        instance.overlayTypeStateProvider = overlayTypeStateProvider;
    }

    public static void injectOverlayXrayStateProvider(StageViewPresenter instance, OverlayXrayStateProvider overlayXrayStateProvider) {
        instance.overlayXrayStateProvider = overlayXrayStateProvider;
    }

    public static void injectOverlayLyricsStateProvider(StageViewPresenter instance, OverlayLyricsStateProvider overlayLyricsStateProvider) {
        instance.overlayLyricsStateProvider = overlayLyricsStateProvider;
    }

    public static void injectStageAutomatedSequencingHandler(StageViewPresenter instance, StageAutomatedSequencingHandler stageAutomatedSequencingHandler) {
        instance.stageAutomatedSequencingHandler = stageAutomatedSequencingHandler;
    }

    public static void injectStageOverflowMenuSecondaryIconStateProvider(StageViewPresenter instance, StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider) {
        instance.stageOverflowMenuSecondaryIconStateProvider = stageOverflowMenuSecondaryIconStateProvider;
    }

    public static void injectLyricsSharingDataModel(StageViewPresenter instance, LyricsSharingDataModel lyricsSharingDataModel) {
        instance.lyricsSharingDataModel = lyricsSharingDataModel;
    }
}
