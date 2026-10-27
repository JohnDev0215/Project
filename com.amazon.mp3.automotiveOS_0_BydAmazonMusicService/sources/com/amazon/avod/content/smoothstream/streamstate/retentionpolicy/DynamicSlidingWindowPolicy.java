package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicSlidingWindowPolicy implements RetentionPolicy {
    private final SmoothStreamingPlaybackConfig mConfig;
    private final ContentSessionState mContentSessionState;
    private final Heuristics mHeuristics;
    private final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;

    public DynamicSlidingWindowPolicy(ContentSessionState contentSessionState, Heuristics heuristics) {
        this(contentSessionState, SmoothStreamingPlaybackConfig.INSTANCE, LiveStreamingPlaybackConfig.getInstance(), heuristics);
    }

    DynamicSlidingWindowPolicy(@Nonnull ContentSessionState contentSessionState, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, @Nonnull Heuristics heuristics) {
        this.mContentSessionState = (ContentSessionState) Preconditions.checkNotNull(contentSessionState, "contentSessionState");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mLiveStreamingPlaybackConfig = (LiveStreamingPlaybackConfig) Preconditions.checkNotNull(liveStreamingPlaybackConfig, "liveStreamingPlaybackConfig");
        this.mHeuristics = (Heuristics) Preconditions.checkNotNull(heuristics, "heuristics");
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI smoothStreamingURI, long j, @Nonnull ContentSessionContext contentSessionContext) {
        try {
            if (smoothStreamingURI.isExpired()) {
                DLog.devf("DynamicSlidingWindowPolicy: targetFragment %s is expired, playheadInNanos: %d ", smoothStreamingURI, Long.valueOf(j));
                return false;
            }
            long mediaTimeWindowEndNanos = this.mContentSessionState.getMediaTimeWindowEndNanos();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long nanos = mediaTimeWindowEndNanos + timeUnit.toNanos(this.mLiveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(this.mHeuristics));
            long jMin = Math.min(timeUnit.toNanos(this.mConfig.getStreamingFutureBufferSizeSeconds(this.mHeuristics)), (nanos - j) - this.mContentSessionState.getLiveDynamicFrontMargin().getCurrentFrontMarginNanos());
            long jMax = Math.max(j - timeUnit.toNanos(this.mConfig.getStreamingPastBufferSizeSeconds(this.mHeuristics)), 0L);
            long j2 = j + jMin;
            long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
            long durationInNanos = smoothStreamingURI.getDurationInNanos() + presentationTimeInNanos;
            if (jMax > durationInNanos || presentationTimeInNanos > j2) {
                DLog.devf("DynamicSlidingWindowPolicy: targetFragment %s should be deleted, playHeadInNanoseconds %d, currentLiveThresholdInNanoseconds %d, lookAheadInNanoseconds %d, windowStartInNanoseconds %d, windowEndInNanoseconds %d, fragmentRange %s", smoothStreamingURI, Long.valueOf(j), Long.valueOf(nanos), Long.valueOf(jMin), Long.valueOf(jMax), Long.valueOf(j2), String.format(Locale.US, "[%s-%s]", Long.toString(presentationTimeInNanos), Long.toString(durationInNanos)));
            }
            return jMax <= durationInNanos && presentationTimeInNanos <= j2;
        } catch (IndexOutOfBoundsException e) {
            DLog.exceptionf(e, "DynamicSlidingWindowPolicy throwing IOOBE: ", new Object[0]);
            return false;
        }
    }
}
