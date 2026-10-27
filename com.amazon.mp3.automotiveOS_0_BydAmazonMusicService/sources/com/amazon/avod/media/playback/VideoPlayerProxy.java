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
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VideoPlayerProxy implements VideoPlayer {
    protected abstract VideoPlayer getDelegate();

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(VideoSpecification videoSpecification, File file) throws MediaException {
        getDelegate().prepareAsync(videoSpecification, file);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void terminate(boolean z, @Nullable MediaException mediaException) {
        getDelegate().terminate(z, mediaException);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        getDelegate().setRenderingSettings(videoRenderingSettings);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void notifyUserLaunchedPlayback() {
        getDelegate().notifyUserLaunchedPlayback();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        getDelegate().start();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        getDelegate().pause();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        getDelegate().seekTo(j);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        getDelegate().seekToUTC(j);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        return getDelegate().getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return getDelegate().getCurrentAbsolutePosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        return getDelegate().getCurrentPositionUTC();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        return getDelegate().getBufferPercentage();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        return getDelegate().getDuration();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        return getDelegate().getPlayerStatistics();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return getDelegate().getPlaybackExperienceController();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(AudioTrackChangeListener audioTrackChangeListener) {
        getDelegate().addListener(audioTrackChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackContentEventListener playbackContentEventListener) {
        getDelegate().addListener(playbackContentEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackEventListener playbackEventListener) {
        getDelegate().addListener(playbackEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackSessionBufferEventListener playbackSessionBufferEventListener) {
        getDelegate().addListener(playbackSessionBufferEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        getDelegate().addListener(playbackQualityChangedEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackStateEventListener playbackStateEventListener) {
        getDelegate().addListener(playbackStateEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(@Nonnull PlaybackAdStateChangeListener playbackAdStateChangeListener) {
        getDelegate().addListener(playbackAdStateChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackTimeDataEventListener playbackTimeDataEventListener) {
        getDelegate().addListener(playbackTimeDataEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(AudioTrackChangeListener audioTrackChangeListener) {
        getDelegate().removeListener(audioTrackChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        getDelegate().removeListener(playbackQualityChangedEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void clearAllListeners() {
        getDelegate().clearAllListeners();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nullable
    public ContentSession getContentSession() {
        return getDelegate().getContentSession();
    }
}
