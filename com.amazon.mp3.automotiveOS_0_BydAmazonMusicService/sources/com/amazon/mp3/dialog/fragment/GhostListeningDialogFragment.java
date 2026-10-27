package com.amazon.mp3.dialog.fragment;

import com.amazon.mp3.ghostlistening.GhostListeningMetricsProvider;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.ghostlistening.DefaultGhostListeningDialog;
import com.amazon.music.media.playback.ControlSource;

/* JADX INFO: loaded from: classes3.dex */
public final class GhostListeningDialogFragment extends DefaultGhostListeningDialog {
    private static boolean sShouldShow;

    public static void setShouldShow(final boolean shouldShow) {
        sShouldShow = shouldShow;
    }

    public static boolean shouldShow() {
        return sShouldShow;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MetricsLogger.ghostListeningDialogViewed();
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public void onPositiveClick() {
        PlaybackControllerProvider.getController(ControlSource.APP_UI).play();
        GhostListeningMetricsProvider.getContinueListeningMetric(true).publish();
    }

    @Override // com.amazon.music.ghostlistening.GhostListeningDialog
    public void onNegativeClick() {
        GhostListeningMetricsProvider.getDismissMetric().publish();
    }
}
