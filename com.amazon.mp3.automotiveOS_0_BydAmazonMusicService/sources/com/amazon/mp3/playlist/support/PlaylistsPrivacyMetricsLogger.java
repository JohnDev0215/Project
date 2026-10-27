package com.amazon.mp3.playlist.support;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistsPrivacyMetricsLogger.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/playlist/support/PlaylistsPrivacyMetricsLogger;", "", "()V", "sendMakePrivateDialogMetric", "", "isPositiveButton", "", "sendShareDialogMetricEvent", "sendUiClickMetric", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistsPrivacyMetricsLogger {
    public static final PlaylistsPrivacyMetricsLogger INSTANCE = new PlaylistsPrivacyMetricsLogger();

    private PlaylistsPrivacyMetricsLogger() {
    }

    public final void sendShareDialogMetricEvent(boolean isPositiveButton) {
        sendUiClickMetric(isPositiveButton ? ActionType.SELECT_MAKE_PLAYLIST_PUBLIC : ActionType.SELECT_KEEP_PLAYLIST_PRIVATE, PageType.PLAYLIST_SHARE_WARNING_DIALOG);
    }

    public final void sendMakePrivateDialogMetric(boolean isPositiveButton) {
        sendUiClickMetric(isPositiveButton ? ActionType.SELECT_MAKE_PLAYLIST_PRIVATE : ActionType.SELECT_KEEP_PLAYLIST_PUBLIC, PageType.PLAYLIST_MAKE_PRIVATE_WARNING_DIALOG);
    }

    private final void sendUiClickMetric(ActionType actionType, PageType pageType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build();
        if (uiClickEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiClickEventBuild);
        }
    }
}
