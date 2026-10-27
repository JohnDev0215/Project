package com.amazon.mp3.playback.fragment;

import android.os.SystemClock;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NPSOverlayMetricsReporter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 A2\u00020\u0001:\u0001AB\u0007¢\u0006\u0004\b?\u0010@J\u0012\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\u0014\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0007J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0002R\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\"\u0010\u001a\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\"\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001d\u0010\u001f\"\u0004\b \u0010!R\"\u0010\"\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0012\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R\u0017\u0010&\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R$\u0010+\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020\u0007068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>¨\u0006B"}, d2 = {"Lcom/amazon/mp3/playback/fragment/NPSOverlayMetricsReporter;", "", "", "fromPlaybackEventListener", "", "sendMetrics", "isStage", "Lcom/amazon/mp3/playback/fragment/NPSViews;", "currentView", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContentName;", "getContentNameFromOverlayType", "NPSView", "startTimer", "stopTimer", "sendStart", "sendDwellTimeMetrics", "", "firstLyricsLineStartTime", "J", "getFirstLyricsLineStartTime", "()J", "setFirstLyricsLineStartTime", "(J)V", "positionOnNextPreviousButtonClick", "getPositionOnNextPreviousButtonClick", "setPositionOnNextPreviousButtonClick", "duration", "getDuration", "setDuration", "isViewVisible", "Z", "()Z", "setViewVisible", "(Z)V", "lastReportedStartTime", "getLastReportedStartTime", "setLastReportedStartTime", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "getMediaItem", "()Lcom/amazon/music/media/playback/MediaItem;", "setMediaItem", "(Lcom/amazon/music/media/playback/MediaItem;)V", "Lcom/amazon/mp3/playback/fragment/NPSViews;", "getCurrentView", "()Lcom/amazon/mp3/playback/fragment/NPSViews;", "setCurrentView", "(Lcom/amazon/mp3/playback/fragment/NPSViews;)V", "", "listOfOverlayToSendOverlayMetrics", "Ljava/util/List;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "onPlayStateChangedListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "onPlaybackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NPSOverlayMetricsReporter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static NPSOverlayMetricsReporter NPSOverlayMetricsReporter;
    private NPSViews currentView;
    private long duration;
    private long firstLyricsLineStartTime;
    private boolean isViewVisible;
    private long lastReportedStartTime = -1;
    private final List<NPSViews> listOfOverlayToSendOverlayMetrics;
    private MediaItem mediaItem;
    private final OnPlayStateChangedListener onPlayStateChangedListener;
    private final OnPlaybackEventListener onPlaybackEventListener;
    private final ActionValidatedPlaybackController playbackController;
    private long positionOnNextPreviousButtonClick;

    /* JADX INFO: compiled from: NPSOverlayMetricsReporter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NPSViews.values().length];
            iArr[NPSViews.LYRICS_AUTOMATED_PREVIEW.ordinal()] = 1;
            iArr[NPSViews.LYRICS_PREVIEW.ordinal()] = 2;
            iArr[NPSViews.LYRICS_FULL_SCREEN.ordinal()] = 3;
            iArr[NPSViews.XRAY_FULL_SCREEN.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NPSOverlayMetricsReporter() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        this.listOfOverlayToSendOverlayMetrics = CollectionsKt__CollectionsKt.listOf((Object[]) new NPSViews[]{NPSViews.LYRICS_FULL_SCREEN, NPSViews.LYRICS_AUTOMATED_PREVIEW, NPSViews.LYRICS_PREVIEW, NPSViews.XRAY_FULL_SCREEN});
        OnPlayStateChangedListener onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                NPSOverlayMetricsReporter.m1404onPlayStateChangedListener$lambda1(this.f$0);
            }
        };
        this.onPlayStateChangedListener = onPlayStateChangedListener;
        OnPlaybackEventListener onPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter$$ExternalSyntheticLambda1
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                NPSOverlayMetricsReporter.m1405onPlaybackEventListener$lambda3(this.f$0, i, controlSource);
            }
        };
        this.onPlaybackEventListener = onPlaybackEventListener;
        controller.addOnPlaybackEventListener(onPlaybackEventListener);
        controller.addOnPlayStateChangedListener(onPlayStateChangedListener);
        this.mediaItem = controller.getCurrentMediaItem();
    }

    /* JADX INFO: compiled from: NPSOverlayMetricsReporter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/playback/fragment/NPSOverlayMetricsReporter$Companion;", "", "()V", "NPSOverlayMetricsReporter", "Lcom/amazon/mp3/playback/fragment/NPSOverlayMetricsReporter;", "getNPSOverlayMetricsReporter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NPSOverlayMetricsReporter getNPSOverlayMetricsReporter() {
            if (NPSOverlayMetricsReporter.NPSOverlayMetricsReporter == null) {
                NPSOverlayMetricsReporter.NPSOverlayMetricsReporter = new NPSOverlayMetricsReporter();
            }
            NPSOverlayMetricsReporter nPSOverlayMetricsReporter = NPSOverlayMetricsReporter.NPSOverlayMetricsReporter;
            if (nPSOverlayMetricsReporter != null) {
                return nPSOverlayMetricsReporter;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter");
        }
    }

    public final void setFirstLyricsLineStartTime(long j) {
        this.firstLyricsLineStartTime = j;
    }

    public final void setPositionOnNextPreviousButtonClick(long j) {
        this.positionOnNextPreviousButtonClick = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayStateChangedListener$lambda-1, reason: not valid java name */
    public static final void m1404onPlayStateChangedListener$lambda1(NPSOverlayMetricsReporter this$0) {
        NPSViews nPSViews;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            return;
        }
        if (this$0.isViewVisible && (nPSViews = this$0.currentView) != null) {
            this$0.stopTimer(nPSViews, true);
            if (nPSViews == NPSViews.LYRICS_FULL_SCREEN || nPSViews == NPSViews.XRAY_FULL_SCREEN) {
                this$0.startTimer(nPSViews);
            }
        }
        this$0.mediaItem = currentMediaItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlaybackEventListener$lambda-3, reason: not valid java name */
    public static final void m1405onPlaybackEventListener$lambda3(NPSOverlayMetricsReporter this$0, int i, ControlSource noName_1) {
        NPSViews nPSViews;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (i == 4 || i == 27) {
            if (this$0.isViewVisible && (nPSViews = this$0.currentView) != null) {
                this$0.stopTimer(nPSViews, true);
                this$0.startTimer(nPSViews);
            }
            this$0.mediaItem = this$0.playbackController.getCurrentMediaItem();
        }
    }

    public final void startTimer(NPSViews NPSView) {
        Intrinsics.checkNotNullParameter(NPSView, "NPSView");
        if (this.isViewVisible) {
            return;
        }
        this.isViewVisible = true;
        this.currentView = NPSView;
        this.lastReportedStartTime = SystemClock.uptimeMillis();
    }

    public static /* synthetic */ void stopTimer$default(NPSOverlayMetricsReporter nPSOverlayMetricsReporter, NPSViews nPSViews, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        nPSOverlayMetricsReporter.stopTimer(nPSViews, z);
    }

    public final void stopTimer(NPSViews NPSView, boolean fromPlaybackEventListener) {
        Intrinsics.checkNotNullParameter(NPSView, "NPSView");
        this.isViewVisible = false;
        this.currentView = NPSView;
        if (this.lastReportedStartTime != -1) {
            this.duration += SystemClock.uptimeMillis() - this.lastReportedStartTime;
            sendMetrics(fromPlaybackEventListener);
        }
        this.lastReportedStartTime = -1L;
    }

    private final void sendMetrics(boolean fromPlaybackEventListener) {
        MediaItemId mediaItemId;
        if (this.currentView == null || isStage() || !CollectionsKt___CollectionsKt.contains(this.listOfOverlayToSendOverlayMetrics, this.currentView)) {
            this.duration = 0L;
            return;
        }
        if (this.currentView == NPSViews.LYRICS_AUTOMATED_PREVIEW) {
            if (fromPlaybackEventListener) {
                if (this.positionOnNextPreviousButtonClick < this.firstLyricsLineStartTime) {
                    return;
                }
            } else if (this.playbackController.getPositionMillis() < this.firstLyricsLineStartTime) {
                return;
            }
        }
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithPageType = UiContentViewEvent.clientEventBuilder().withContentName(getContentNameFromOverlayType(this.currentView)).withPageType(PageType.NOW_PLAYING.getMetricsValue());
        MediaItem mediaItem = this.mediaItem;
        String id = null;
        if (mediaItem != null && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            id = mediaItemId.getId();
        }
        MetricsLogger.sendEvent(clientEventBuilderWithPageType.withEntityId(String.valueOf(id)).withEntityType(EntityType.TRACK).withEntityIdType(EntityIdType.ASIN).build());
        this.duration = 0L;
    }

    public final void sendDwellTimeMetrics(boolean sendStart) {
        MediaItemId mediaItemId;
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithPageType = UiContentViewEvent.clientEventBuilder().withContentName(sendStart ? ContentName.NOW_PLAYING_START : ContentName.NOW_PLAYING_STOP).withPageType(PageType.NOW_PLAYING.getMetricsValue());
        MediaItem mediaItem = this.mediaItem;
        String id = null;
        if (mediaItem != null && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            id = mediaItemId.getId();
        }
        clientEventBuilderWithPageType.withEntityId(String.valueOf(id)).withEntityType(EntityType.TRACK).withEntityIdType(EntityIdType.ASIN).build();
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    private final ContentName getContentNameFromOverlayType(NPSViews currentView) {
        int i = currentView == null ? -1 : WhenMappings.$EnumSwitchMapping$0[currentView.ordinal()];
        if (i == 1) {
            return ContentName.LYRICS_VIEWED_PREVIEW;
        }
        if (i == 2) {
            return ContentName.LYRICS_VIEWED_MANUAL;
        }
        if (i == 3) {
            return ContentName.LYRICS_VIEWED_FULLSCREEN;
        }
        if (i != 4) {
            return null;
        }
        return ContentName.XRAY_VIEWED_FULLSCREEN;
    }
}
