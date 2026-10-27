package com.amazon.avod.media.ads;

import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.CacheStatus;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.playback.PlaybackEventReporter;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdClip {
    @Nullable
    String getAdId();

    @Nullable
    AdSkipInfo getAdSkipInfo();

    @Nullable
    String getAdSystem();

    @Nonnull
    CacheStatus getCacheStatus();

    @Nullable
    String getContentType();

    @Nonnull
    TimeSpan getDuration();

    @Nonnull
    List<Object> getExtensions();

    @Nullable
    String getInfoUrl();

    @Nullable
    VideoPlayer getPlayer();

    boolean isPlayed();

    boolean isPrepared();

    void preload(TimeSpan timeSpan, boolean z, String str);

    void scheduleTimelineEventsWithPrimaryPlayer(@Nonnull TimelineMonitor timelineMonitor, @Nonnull VideoPlayer videoPlayer, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull String str);

    void sendMuteEvent(String str);

    void sendPauseEvent(String str);

    void sendResumeEvent(String str);

    void sendUnmuteEvent(String str);
}
