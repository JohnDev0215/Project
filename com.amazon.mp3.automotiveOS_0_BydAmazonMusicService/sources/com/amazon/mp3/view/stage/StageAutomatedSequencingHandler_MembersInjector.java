package com.amazon.mp3.view.stage;

import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class StageAutomatedSequencingHandler_MembersInjector implements MembersInjector<StageAutomatedSequencingHandler> {
    public static void injectOverlayTypeStateProvider(StageAutomatedSequencingHandler instance, OverlayTypeStateProvider overlayTypeStateProvider) {
        instance.overlayTypeStateProvider = overlayTypeStateProvider;
    }

    public static void injectOverlayLyricsStateProvider(StageAutomatedSequencingHandler instance, OverlayLyricsStateProvider overlayLyricsStateProvider) {
        instance.overlayLyricsStateProvider = overlayLyricsStateProvider;
    }
}
