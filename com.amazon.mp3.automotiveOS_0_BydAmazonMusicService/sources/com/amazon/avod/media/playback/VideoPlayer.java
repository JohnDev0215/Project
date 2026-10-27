package com.amazon.avod.media.playback;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackEventListener;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import com.amazon.avod.playback.player.actions.SeekAction;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPlayer {
    void addListener(AudioTrackChangeListener audioTrackChangeListener);

    void addListener(@Nonnull PlaybackAdStateChangeListener playbackAdStateChangeListener);

    void addListener(PlaybackContentEventListener playbackContentEventListener);

    void addListener(PlaybackEventListener playbackEventListener);

    void addListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener);

    void addListener(PlaybackSessionBufferEventListener playbackSessionBufferEventListener);

    void addListener(PlaybackStateEventListener playbackStateEventListener);

    void addListener(PlaybackTimeDataEventListener playbackTimeDataEventListener);

    void clearAllListeners();

    @Deprecated
    int getBufferPercentage();

    ContentSession getContentSession();

    long getCurrentAbsolutePosition();

    long getCurrentPosition();

    long getCurrentPositionUTC();

    long getDuration();

    PlaybackExperienceController getPlaybackExperienceController();

    @Nonnull
    PlayerStatistics getPlayerStatistics();

    void notifyUserLaunchedPlayback();

    void pause();

    void prepareAsync(VideoSpecification videoSpecification, File file) throws MediaException;

    void removeListener(AudioTrackChangeListener audioTrackChangeListener);

    void removeListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener);

    void seekTo(long j);

    default void seekToManifestPosition(long j, SeekAction.SeekCause seekCause) {
    }

    void seekToUTC(long j);

    void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings);

    void start();

    void terminate(boolean z, @Nullable MediaException mediaException);
}
