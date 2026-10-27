package com.amazon.avod.playback;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPerformanceReport {
    private final String mDetails;
    private final String mReason;
    private final PlaybackPerformanceSeverity mSeverity;
    private final TimeSpan mTimeStamp;

    public enum PlaybackPerformanceSeverity {
        NONE("No performance problems."),
        MINOR("Minor performance problems - few dropped frame or small intra-frame delays. These are usually not noticeable by the user."),
        MAJOR("Major performance problems - a number of dropped frames, intra-frame delays, minor audio distortions, clicks and possible audio and video synchronization issues. These are clearly noticeable by the user."),
        CRITICAL("Critical performance problems - the content has constant dropped frames, large intra-frame delays, audio and video synchronization issues. The playback is not watchable.");

        private final String mDescription;

        PlaybackPerformanceSeverity(String str) {
            this.mDescription = str;
        }

        public String getDescription() {
            return this.mDescription;
        }
    }

    public PlaybackPerformanceReport(@Nonnull TimeSpan timeSpan, @Nonnull PlaybackPerformanceSeverity playbackPerformanceSeverity, @Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(timeSpan);
        Preconditions.checkNotNull(playbackPerformanceSeverity);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mTimeStamp = timeSpan;
        this.mSeverity = playbackPerformanceSeverity;
        this.mReason = str;
        this.mDetails = str2;
    }

    @Nonnull
    public String getDetails() {
        return this.mDetails;
    }

    @Nonnull
    public String getReason() {
        return this.mReason;
    }

    @Nonnull
    public PlaybackPerformanceSeverity getSeverity() {
        return this.mSeverity;
    }

    @Nonnull
    public TimeSpan getTimeStamp() {
        return this.mTimeStamp;
    }
}
