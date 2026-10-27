package com.amazon.mp3.video.stories;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.video.stories.AutoPlayMetricsReporter;
import com.amazon.mp3.video.stories.metrics.EntityRenderingType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryAutoPlayEventHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayEventHandler;", "", "autoPlayMetricsReporter", "Lcom/amazon/mp3/video/stories/AutoPlayMetricsReporter;", "(Lcom/amazon/mp3/video/stories/AutoPlayMetricsReporter;)V", "autoPlayStateManager", "Lcom/amazon/mp3/video/stories/AutoPlayStateManager;", "handleAutoPlayEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/mp3/video/stories/AutoPlayEvent;", "handleAutoPlayStartedEvent", "handlePauseEvent", "handlePlayEvent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoryAutoPlayEventHandler {
    private final AutoPlayMetricsReporter autoPlayMetricsReporter;
    private final AutoPlayStateManager autoPlayStateManager;

    /* JADX INFO: compiled from: VideoStoryAutoPlayEventHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutoPlayEvent.values().length];
            iArr[AutoPlayEvent.PLAY.ordinal()] = 1;
            iArr[AutoPlayEvent.PAUSE.ordinal()] = 2;
            iArr[AutoPlayEvent.PLAYBACK_STARTED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VideoStoryAutoPlayEventHandler(AutoPlayMetricsReporter autoPlayMetricsReporter) {
        Intrinsics.checkNotNullParameter(autoPlayMetricsReporter, "autoPlayMetricsReporter");
        this.autoPlayMetricsReporter = autoPlayMetricsReporter;
        this.autoPlayStateManager = new AutoPlayStateManager();
    }

    public final void handleAutoPlayEvent(AutoPlayEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            handlePlayEvent();
        } else if (i == 2) {
            handlePauseEvent();
        } else if (i == 3) {
            handleAutoPlayStartedEvent();
        }
        this.autoPlayStateManager.updateState(event);
    }

    private final void handleAutoPlayStartedEvent() {
        if (this.autoPlayStateManager.getIsNewPlayback()) {
            this.autoPlayMetricsReporter.reportVideoStoryIPDMSMetrics(System.currentTimeMillis() - this.autoPlayStateManager.getPlaybackRequestedTime());
        }
    }

    private final void handlePauseEvent() {
        if (this.autoPlayStateManager.getIsPlaying()) {
            AutoPlayMetricsReporter.DefaultImpls.reportVideoStoryAutoPlayDurationMetrics$default(this.autoPlayMetricsReporter, System.currentTimeMillis() - this.autoPlayStateManager.getPlaybackRequestedTime(), false, 2, null);
        }
        if (this.autoPlayStateManager.getShouldPlay()) {
            this.autoPlayMetricsReporter.reportVideoStoryContentViewMetrics(this.autoPlayStateManager.getIsPlaying() ? EntityRenderingType.AUTO_PLAY : EntityRenderingType.THUMBNAIL);
        }
    }

    private final void handlePlayEvent() {
        Long initialPlaybackStartedTime = this.autoPlayStateManager.getInitialPlaybackStartedTime();
        if (initialPlaybackStartedTime != null) {
            long jLongValue = initialPlaybackStartedTime.longValue();
            if (this.autoPlayStateManager.getIsPlaying()) {
                this.autoPlayMetricsReporter.reportVideoStoryAutoPlayDurationMetrics(System.currentTimeMillis() - jLongValue, true);
            }
        }
        this.autoPlayStateManager.setInitialPlaybackStartedTime$DigitalMusicAndroid3P_automotiveProdRelease(null);
    }
}
