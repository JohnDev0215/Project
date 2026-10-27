package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class TimelineUtils {
    private final TimeSpan mRequiredCacheSizeForPlayback;
    private final TimeSpan mResumeMarginFromEnd;
    private final TimeSpan mSeekingBeyondEndMargin;

    /* JADX WARN: Illegal instructions before constructor call */
    public TimelineUtils() {
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        this(smoothStreamingPlaybackConfig.getLastResumablePositionFromEndOfContent(), smoothStreamingPlaybackConfig.getRequiredCacheSizeForProgressivePlayback(), TimeSpan.fromSeconds(2.0d));
    }

    TimelineUtils(TimeSpan timeSpan, TimeSpan timeSpan2, TimeSpan timeSpan3) {
        this.mResumeMarginFromEnd = timeSpan;
        this.mRequiredCacheSizeForPlayback = timeSpan2;
        this.mSeekingBeyondEndMargin = timeSpan3;
    }

    public TimeSpan getLastSeekablePosition(ContentSession contentSession) {
        return getLastValidPosition(contentSession, this.mSeekingBeyondEndMargin);
    }

    public TimeSpan getLastResumablePosition(ContentSession contentSession) {
        ContentSessionContext context = contentSession.getContext();
        Preconditions.checkNotNull(context, "context");
        if (context.getSpecification().isLiveStream()) {
            return TimeSpan.MAX_VALUE;
        }
        return getLastValidPosition(contentSession, this.mResumeMarginFromEnd);
    }

    TimeSpan getLastValidPosition(ContentSession contentSession, TimeSpan timeSpan) {
        TimeSpan duration;
        Preconditions.checkState(contentSession != null);
        ContentSessionContext context = contentSession.getContext();
        if (context.isDownload()) {
            duration = contentSession.getLastAvailableTime();
            timeSpan = TimeSpan.add(this.mRequiredCacheSizeForPlayback, timeSpan);
        } else {
            duration = context.getDuration();
        }
        if (duration.compareTo(timeSpan) < 0) {
            return TimeSpan.ZERO;
        }
        return TimeSpan.difference(duration, timeSpan);
    }
}
