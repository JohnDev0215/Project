package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.util.DLog;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AscendingLiveEdgeSlidingWindowPolicy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\"\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/avod/content/smoothstream/streamstate/retentionpolicy/AscendingLiveEdgeSlidingWindowPolicy;", "Lcom/amazon/avod/content/smoothstream/streamstate/retentionpolicy/RetentionPolicy;", "contentSessionState", "Lcom/amazon/avod/content/ContentSessionState;", "heuristics", "Lcom/amazon/avod/content/dash/quality/heuristic/Heuristics;", "downloadService", "Lcom/amazon/avod/media/downloadservice/DownloadService;", "(Lcom/amazon/avod/content/ContentSessionState;Lcom/amazon/avod/content/dash/quality/heuristic/Heuristics;Lcom/amazon/avod/media/downloadservice/DownloadService;)V", "playbackConfig", "Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;", "liveStreamingConfig", "Lcom/amazon/avod/content/config/LiveStreamingPlaybackConfig;", "(Lcom/amazon/avod/content/ContentSessionState;Lcom/amazon/avod/content/dash/quality/heuristic/Heuristics;Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;Lcom/amazon/avod/content/config/LiveStreamingPlaybackConfig;Lcom/amazon/avod/media/downloadservice/DownloadService;)V", "prevLiveWindow", "Lkotlin/Pair;", "", "shouldRetain", "", "targetFragment", "Lcom/amazon/avod/content/smoothstream/SmoothStreamingURI;", "playHeadNanos", "context", "Lcom/amazon/avod/content/ContentSessionContext;", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AscendingLiveEdgeSlidingWindowPolicy implements RetentionPolicy {
    private final ContentSessionState contentSessionState;
    private final DownloadService downloadService;
    private final Heuristics heuristics;
    private final LiveStreamingPlaybackConfig liveStreamingConfig;
    private final SmoothStreamingPlaybackConfig playbackConfig;
    private Pair<Long, Long> prevLiveWindow;

    public AscendingLiveEdgeSlidingWindowPolicy(ContentSessionState contentSessionState, Heuristics heuristics, SmoothStreamingPlaybackConfig playbackConfig, LiveStreamingPlaybackConfig liveStreamingConfig, DownloadService downloadService) {
        Intrinsics.checkNotNullParameter(contentSessionState, "contentSessionState");
        Intrinsics.checkNotNullParameter(playbackConfig, "playbackConfig");
        Intrinsics.checkNotNullParameter(liveStreamingConfig, "liveStreamingConfig");
        Intrinsics.checkNotNullParameter(downloadService, "downloadService");
        this.contentSessionState = contentSessionState;
        this.heuristics = heuristics;
        this.playbackConfig = playbackConfig;
        this.liveStreamingConfig = liveStreamingConfig;
        this.downloadService = downloadService;
        this.prevLiveWindow = new Pair<>(0L, 0L);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AscendingLiveEdgeSlidingWindowPolicy(ContentSessionState contentSessionState, Heuristics heuristics, DownloadService downloadService) {
        Intrinsics.checkNotNullParameter(contentSessionState, "contentSessionState");
        Intrinsics.checkNotNullParameter(downloadService, "downloadService");
        SmoothStreamingPlaybackConfig INSTANCE = SmoothStreamingPlaybackConfig.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
        LiveStreamingPlaybackConfig liveStreamingPlaybackConfig = LiveStreamingPlaybackConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(liveStreamingPlaybackConfig, "getInstance()");
        this(contentSessionState, heuristics, INSTANCE, liveStreamingPlaybackConfig, downloadService);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI targetFragment, long playHeadNanos, ContentSessionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (targetFragment != null) {
            try {
                if (!targetFragment.isExpired()) {
                    long mediaTimeWindowEndNanos = this.contentSessionState.getMediaTimeWindowEndNanos();
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    long nanos = mediaTimeWindowEndNanos + timeUnit.toNanos(this.liveStreamingConfig.getLiveStreamingFutureBufferSizeSeconds(this.heuristics));
                    long nanos2 = timeUnit.toNanos(this.playbackConfig.getStreamingFutureBufferSizeSeconds(this.heuristics));
                    long jMin = Math.min(nanos2, (nanos - playHeadNanos) - this.contentSessionState.getLiveDynamicFrontMargin().getCurrentFrontMarginNanos());
                    this.downloadService.setMaxPrimaryContentBufferInNanos(jMin);
                    if (playHeadNanos < this.prevLiveWindow.getSecond().longValue() - nanos2) {
                        DLog.devf("reset AscendingLiveEdgeSlidingWindowPolicy context#prevLiveWindow");
                        this.prevLiveWindow = new Pair<>(0L, 0L);
                    }
                    long jMax = Math.max(playHeadNanos - timeUnit.toNanos(this.playbackConfig.getStreamingPastBufferSizeSeconds(this.heuristics)), 0L);
                    long jMax2 = Math.max(playHeadNanos + jMin, this.prevLiveWindow.getSecond().longValue());
                    this.prevLiveWindow = new Pair<>(Long.valueOf(jMax), Long.valueOf(jMax2));
                    long presentationTimeInNanos = targetFragment.getPresentationTimeInNanos();
                    return Math.max(jMax, presentationTimeInNanos) <= Math.min(jMax2, targetFragment.getDurationInNanos() + presentationTimeInNanos);
                }
            } catch (IndexOutOfBoundsException e) {
                DLog.exceptionf(e, "AscendingLiveEdgeSlidingWindowPolicy throwing IOOBE: ", new Object[0]);
                return false;
            }
        }
        DLog.devf("AscendingLiveEdgeSlidingWindowPolicy: targetFragment %s is expired, playHeadNanos: %d ", targetFragment, Long.valueOf(playHeadNanos));
        return false;
    }
}
