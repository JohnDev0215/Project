package com.amazon.mp3.cloudqueue.sequencer;

import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class CloudQueueSequencer_MembersInjector implements MembersInjector<CloudQueueSequencer> {
    public static void injectHybridQueueStateProvider(CloudQueueSequencer instance, HybridQueueStateProvider hybridQueueStateProvider) {
        instance.hybridQueueStateProvider = hybridQueueStateProvider;
    }

    public static void injectOverlayTypeStateProvider(CloudQueueSequencer instance, OverlayTypeStateProvider overlayTypeStateProvider) {
        instance.overlayTypeStateProvider = overlayTypeStateProvider;
    }

    public static void injectOverlayLyricsStateProvider(CloudQueueSequencer instance, OverlayLyricsStateProvider overlayLyricsStateProvider) {
        instance.overlayLyricsStateProvider = overlayLyricsStateProvider;
    }

    public static void injectStageAutomatedSequencingHandler(CloudQueueSequencer instance, StageAutomatedSequencingHandler stageAutomatedSequencingHandler) {
        instance.stageAutomatedSequencingHandler = stageAutomatedSequencingHandler;
    }
}
