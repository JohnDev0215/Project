package com.amazon.avod.media.ads;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdBreak {
    void adBreakScheduled();

    @Nullable
    AdClip findNextUnplayedClip();

    @Nonnull
    TimeSpan getAbsoluteStartTime();

    @Nonnull
    AdPositionType getAdPositionType();

    @Nullable
    String getAdSystem();

    @Nonnull
    List<AdClip> getClips();

    @Nonnull
    TimeSpan getDuration();

    @Nullable
    String getId();

    @Nonnull
    TimeSpan getRelativeStartTime();

    @Nonnull
    @Deprecated
    TimeSpan getStartTime();

    boolean isAdBreakScheduled();

    boolean isAdBreakSeekOverScheduled();

    boolean isPlayed();

    void preload(TimeSpan timeSpan, boolean z, String str);

    void scheduleAdBreakSeekOver(TimeSpan timeSpan, @Nonnull VideoPlayer videoPlayer, @Nonnull TimelineMonitor timelineMonitor, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter);

    void sendAdBreakEnd();

    void sendAdBreakStart();

    void setAdTriggeredTimeStamp(@Nonnull TimeSpan timeSpan);
}
