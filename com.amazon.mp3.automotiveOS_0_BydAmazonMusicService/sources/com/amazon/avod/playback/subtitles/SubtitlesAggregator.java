package com.amazon.avod.playback.subtitles;

import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlesAggregator {
    long mDroppedSubtitleSamplesCount;
    long mEndTimeInNanos;
    long mStartDownloadCount;
    long mStartTimeInNanos;
    long mStopDownloadCount;
    long mSuccessfullySubmittedSubtitleSamplesCount;
    private VideoPlaybackTimeline mVideoPlaybackTimeline;

    public void initialize(@Nonnull VideoPlaybackTimeline videoPlaybackTimeline) {
        this.mVideoPlaybackTimeline = (VideoPlaybackTimeline) Preconditions.checkNotNull(videoPlaybackTimeline, "videoPlaybackTimeline");
    }

    public void recordStartTimeInNanos() {
        this.mStartTimeInNanos = this.mVideoPlaybackTimeline.getCurrentPlayTimeInNanos();
        this.mStartDownloadCount++;
    }

    public void recordEndTimeInNanos() {
        this.mEndTimeInNanos = this.mVideoPlaybackTimeline.getCurrentPlayTimeInNanos();
        this.mStopDownloadCount++;
    }

    public void incrementSuccessfulSubmittedSubtitleCount() {
        this.mSuccessfullySubmittedSubtitleSamplesCount++;
    }

    public void incrementDroppedSubtitleSampleCount() {
        this.mDroppedSubtitleSamplesCount++;
    }

    public void reset() {
        this.mVideoPlaybackTimeline = null;
        this.mSuccessfullySubmittedSubtitleSamplesCount = 0L;
        this.mDroppedSubtitleSamplesCount = 0L;
        this.mStartTimeInNanos = 0L;
        this.mEndTimeInNanos = 0L;
        this.mStartDownloadCount = 0L;
        this.mStopDownloadCount = 0L;
    }

    public String getQosSummary() {
        if (this.mSuccessfullySubmittedSubtitleSamplesCount == 0) {
            return "No data - No samples successfully submitted.";
        }
        float f = (this.mDroppedSubtitleSamplesCount / (r0 + r2)) * 100.0f;
        Locale locale = Locale.US;
        return String.format(locale, "Subtitle download start time in nanoseconds: %s|Subtitle download end time in nanoseconds: %s|Number of caption starts invoked: %s|Number of caption stops invoked: %s|Number of successfully submitted samples: %s|Number of dropped samples: %sPercentage of dropped samples: %s", Long.valueOf(this.mStartTimeInNanos), Long.valueOf(this.mEndTimeInNanos), Long.toString(this.mStartDownloadCount), Long.toString(this.mStopDownloadCount), Long.toString(this.mSuccessfullySubmittedSubtitleSamplesCount), Long.toString(this.mDroppedSubtitleSamplesCount), String.format(locale, "%.2f", Float.valueOf(f)));
    }
}
