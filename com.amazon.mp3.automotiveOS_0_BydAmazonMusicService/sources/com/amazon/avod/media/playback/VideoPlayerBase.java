package com.amazon.avod.media.playback;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackEventListener;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VideoPlayerBase implements VideoPlayer {
    private final PlaybackListenerProxy mEventListenerProxy;

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Deprecated
    public abstract /* synthetic */ int getBufferPercentage();

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nullable
    public ContentSession getContentSession() {
        return null;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void notifyUserLaunchedPlayback() {
    }

    protected abstract void terminatePlayback(boolean z, @Nullable MediaException mediaException);

    protected VideoPlayerBase(PlaybackListenerProxy playbackListenerProxy) {
        this.mEventListenerProxy = (PlaybackListenerProxy) Preconditions.checkNotNull(playbackListenerProxy);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(AudioTrackChangeListener audioTrackChangeListener) {
        getEventProxy().addListener(audioTrackChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackContentEventListener playbackContentEventListener) {
        getEventProxy().addListener(playbackContentEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackEventListener playbackEventListener) {
        getEventProxy().addListener(playbackEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        getEventProxy().addListener(playbackQualityChangedEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackSessionBufferEventListener playbackSessionBufferEventListener) {
        getEventProxy().addListener(playbackSessionBufferEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackStateEventListener playbackStateEventListener) {
        getEventProxy().addListener(playbackStateEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(@Nonnull PlaybackAdStateChangeListener playbackAdStateChangeListener) {
        getEventProxy().addListener(playbackAdStateChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void addListener(PlaybackTimeDataEventListener playbackTimeDataEventListener) {
        getEventProxy().addListener(playbackTimeDataEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(AudioTrackChangeListener audioTrackChangeListener) {
        getEventProxy().removeListener(audioTrackChangeListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void removeListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        getEventProxy().removeListener(playbackQualityChangedEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public final void terminate(boolean z, @Nullable MediaException mediaException) {
        terminatePlayback(z, mediaException);
        notifyTerminated();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void clearAllListeners() {
        getEventProxy().clearAllListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyCompleted() {
        DLog.logf("Notifying completion.");
        getEventProxy().onCompletion();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyError(MediaErrorCode mediaErrorCode) {
        DLog.logf("Notifying error %s.", mediaErrorCode);
        getEventProxy().onError(mediaErrorCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyPrepared(PlaybackDataSource playbackDataSource) {
        DLog.logf("Notifying prepared.");
        getEventProxy().onPrepared(playbackDataSource);
    }

    protected void notifyStarted(PlaybackDataSource playbackDataSource) {
        DLog.logf("Notifying started.");
        getEventProxy().onStarted(playbackDataSource);
    }

    private void notifyTerminated() {
        DLog.logf("Notifying terminated.");
        getEventProxy().onTerminated();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PlaybackListenerProxy getEventProxy() {
        return this.mEventListenerProxy;
    }
}
