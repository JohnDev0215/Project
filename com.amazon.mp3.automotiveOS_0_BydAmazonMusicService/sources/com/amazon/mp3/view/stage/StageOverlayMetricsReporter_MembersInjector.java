package com.amazon.mp3.view.stage;

import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class StageOverlayMetricsReporter_MembersInjector implements MembersInjector<StageOverlayMetricsReporter> {
    public static void injectOverlayTypeStateProvider(StageOverlayMetricsReporter instance, OverlayTypeStateProvider overlayTypeStateProvider) {
        instance.overlayTypeStateProvider = overlayTypeStateProvider;
    }
}
