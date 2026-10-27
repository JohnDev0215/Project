package com.amazon.video.sdk.avod.playbackclient.control;

import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.video.sdk.avod.playbackclient.listeners.AudioTrackChangeListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackAdStateChangeListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackQualityChangedEventListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackSessionBufferEventListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackStateEventListenerProxy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackEventDispatch {
    private final AudioTrackChangeListenerProxy mAudioTrackChangeListenerProxy;
    private final PlaybackAdStateChangeListenerProxy mPlaybackAdStateChangeListenerProxy;
    private final PlaybackSpeedScrubResultListenerProxy mPlaybackSpeedScrubResultListenerProxy;
    private final PlaybackProgressEventListenerProxy mProgressEventListenerProxy;
    private final PlaybackQualityChangedEventListenerProxy mQualityChangedEventListenerProxy;
    private final PlaybackSessionBufferEventListenerProxy mSessionBufferEventListenerProxy;
    private final PlaybackStateEventListenerProxy mStateEventListenerProxy;

    public void initialize() {
        throw null;
    }

    PlaybackEventDispatch(@Nonnull PlaybackSessionBufferEventListenerProxy playbackSessionBufferEventListenerProxy, @Nonnull PlaybackStateEventListenerProxy playbackStateEventListenerProxy, @Nonnull PlaybackQualityChangedEventListenerProxy playbackQualityChangedEventListenerProxy, @Nonnull PlaybackProgressEventListenerProxy playbackProgressEventListenerProxy, @Nonnull AudioTrackChangeListenerProxy audioTrackChangeListenerProxy, @Nonnull PlaybackSpeedScrubResultListenerProxy playbackSpeedScrubResultListenerProxy, @Nonnull PlaybackAdStateChangeListenerProxy playbackAdStateChangeListenerProxy) {
        this.mSessionBufferEventListenerProxy = (PlaybackSessionBufferEventListenerProxy) Preconditions.checkNotNull(playbackSessionBufferEventListenerProxy, "playbackSessionBufferEventListenerProxy");
        this.mStateEventListenerProxy = (PlaybackStateEventListenerProxy) Preconditions.checkNotNull(playbackStateEventListenerProxy, "playbackStateEventListenerProxy");
        this.mQualityChangedEventListenerProxy = (PlaybackQualityChangedEventListenerProxy) Preconditions.checkNotNull(playbackQualityChangedEventListenerProxy, "playbackQualityChangedEventListenerProxy");
        this.mProgressEventListenerProxy = (PlaybackProgressEventListenerProxy) Preconditions.checkNotNull(playbackProgressEventListenerProxy, "playbackProgressEventListenerProxy");
        this.mAudioTrackChangeListenerProxy = (AudioTrackChangeListenerProxy) Preconditions.checkNotNull(audioTrackChangeListenerProxy, "audioTrackChangeListenerProxy");
        this.mPlaybackSpeedScrubResultListenerProxy = (PlaybackSpeedScrubResultListenerProxy) Preconditions.checkNotNull(playbackSpeedScrubResultListenerProxy, "playbackSpeedScrubResultListenerProxy");
        this.mPlaybackAdStateChangeListenerProxy = (PlaybackAdStateChangeListenerProxy) Preconditions.checkNotNull(playbackAdStateChangeListenerProxy, "playbackAdStateChangeListenerProxy");
    }

    public void addPlaybackStateEventListener(PlaybackStateEventListener playbackStateEventListener) {
        this.mStateEventListenerProxy.addListener(playbackStateEventListener);
    }

    public void removePlaybackStateEventListener(PlaybackStateEventListener playbackStateEventListener) {
        this.mStateEventListenerProxy.removeListener(playbackStateEventListener);
    }

    public void addPlaybackProgressEventListener(PlaybackProgressEventListener playbackProgressEventListener) {
        this.mProgressEventListenerProxy.addListener(playbackProgressEventListener);
    }

    public void removePlaybackProgressEventListener(PlaybackProgressEventListener playbackProgressEventListener) {
        this.mProgressEventListenerProxy.removeListener(playbackProgressEventListener);
    }

    public void addAudioTrackChangeListener(AudioTrackChangeListener audioTrackChangeListener) {
        this.mAudioTrackChangeListenerProxy.addListener(audioTrackChangeListener);
    }

    public void removeAudioTrackChangeListener(AudioTrackChangeListener audioTrackChangeListener) {
        this.mAudioTrackChangeListenerProxy.removeListener(audioTrackChangeListener);
    }
}
