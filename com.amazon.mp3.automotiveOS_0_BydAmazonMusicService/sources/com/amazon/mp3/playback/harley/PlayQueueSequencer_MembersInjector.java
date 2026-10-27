package com.amazon.mp3.playback.harley;

import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayQueueSequencer_MembersInjector implements MembersInjector<PlayQueueSequencer> {
    public static void injectMHybridQueueStateProvider(PlayQueueSequencer instance, HybridQueueStateProvider mHybridQueueStateProvider) {
        instance.mHybridQueueStateProvider = mHybridQueueStateProvider;
    }

    public static void injectMOverlayTypeStateProvider(PlayQueueSequencer instance, OverlayTypeStateProvider mOverlayTypeStateProvider) {
        instance.mOverlayTypeStateProvider = mOverlayTypeStateProvider;
    }

    public static void injectMOverlayLyricsStateProvider(PlayQueueSequencer instance, OverlayLyricsStateProvider mOverlayLyricsStateProvider) {
        instance.mOverlayLyricsStateProvider = mOverlayLyricsStateProvider;
    }

    public static void injectMStageAutomatedSequencingHandler(PlayQueueSequencer instance, StageAutomatedSequencingHandler mStageAutomatedSequencingHandler) {
        instance.mStageAutomatedSequencingHandler = mStageAutomatedSequencingHandler;
    }
}
