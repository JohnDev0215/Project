package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.playback.AdPlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPausedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPlayingEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackStartedEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AloysiusPlaybackReporterInterface {
    String getAppInstanceId();

    void handleAdPlaybackStartedEvent(AdPlaybackStartedEvent adPlaybackStartedEvent);

    void handleBufferStartEvent(BufferStartEvent bufferStartEvent);

    void handlePauseEvent(PauseEvent pauseEvent);

    void handlePlaybackLoadedEvent(AdPlaybackLoadedEvent adPlaybackLoadedEvent);

    void handlePlaybackLoadedEvent(PlaybackLoadedEvent playbackLoadedEvent);

    void handlePlaybackPausedEvent(AdPlaybackPausedEvent adPlaybackPausedEvent);

    void handlePlaybackPlayingEvent(AdPlaybackPlayingEvent adPlaybackPlayingEvent);

    void handlePlaybackStartEvent(PlaybackStartEvent playbackStartEvent);

    void handlePlaybackStopEvent(@Nullable TimeSpan timeSpan);

    void handleResumeEvent(ResumeEvent resumeEvent);

    void handleSeekStart(SeekStartEvent seekStartEvent);
}
