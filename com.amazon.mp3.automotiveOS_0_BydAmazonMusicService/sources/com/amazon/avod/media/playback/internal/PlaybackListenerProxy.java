package com.amazon.avod.media.playback.internal;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.internal.listener.AudioTrackChangeListenerSet;
import com.amazon.avod.media.playback.internal.listener.LiveEventStateListenerSet;
import com.amazon.avod.media.playback.internal.listener.LiveTimeWindowEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackAdStateChangeListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackContentEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackPerformanceEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackQualityChangedEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackRightsValidatedListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackSessionBufferEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackStateEventListenerSet;
import com.amazon.avod.media.playback.internal.listener.PlaybackTimeDataChangeEventListenerSet;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.LiveEventStateListener;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackEventListener;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.playback.PlaybackRightsValidatedListener;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackListenerProxy implements AudioTrackChangeListener, PlaybackContentEventListener, PlaybackStateEventListener, PlaybackSessionBufferEventListener, PlaybackQualityChangedEventListener, PlaybackEventListener, PlaybackAdStateChangeListener, PlaybackTimeDataEventListener, LiveEventStateListener, PlaybackRightsValidatedListener {
    private final AudioTrackChangeListenerSet mAudioTrackChangeListenerSet;
    private final PlaybackSessionBufferEventListenerSet mBufferEventListenerSet;
    private final LiveEventStateListenerSet mLiveEventStateListenerSet;
    private final LiveTimeWindowEventListenerSet mLiveTimeWindowEventListenerSet;
    private final Object mMutex;
    private final PlaybackPerformanceEventListenerSet mPerformanceEventListenerSet;
    private final PlaybackAdStateChangeListenerSet mPlaybackAdStateChangeListenerSet;
    private final PlaybackContentEventListenerSet mPlaybackContentEventListenerSet;
    private final PlaybackEventListenerSet mPlaybackEventListenerSet;
    private final PlaybackRightsValidatedListenerSet mPlaybackRightsValidatedListenerSet;
    private final PlaybackTimeDataChangeEventListenerSet mPlaybackTimeDataChangeEventListenerSet;
    private final PlaybackQualityChangedEventListenerSet mQualityEventListenerSet;
    private final PlaybackStateEventListenerSet mStateEventListenerSet;

    public PlaybackListenerProxy() {
        this(new AudioTrackChangeListenerSet(), new PlaybackContentEventListenerSet(), new PlaybackStateEventListenerSet(), new PlaybackSessionBufferEventListenerSet(), new PlaybackQualityChangedEventListenerSet(), new PlaybackEventListenerSet(), new PlaybackPerformanceEventListenerSet(), new PlaybackAdStateChangeListenerSet(), new LiveTimeWindowEventListenerSet(), new PlaybackTimeDataChangeEventListenerSet(), new LiveEventStateListenerSet(), new PlaybackRightsValidatedListenerSet());
    }

    PlaybackListenerProxy(AudioTrackChangeListenerSet audioTrackChangeListenerSet, PlaybackContentEventListenerSet playbackContentEventListenerSet, PlaybackStateEventListenerSet playbackStateEventListenerSet, PlaybackSessionBufferEventListenerSet playbackSessionBufferEventListenerSet, PlaybackQualityChangedEventListenerSet playbackQualityChangedEventListenerSet, PlaybackEventListenerSet playbackEventListenerSet, PlaybackPerformanceEventListenerSet playbackPerformanceEventListenerSet, PlaybackAdStateChangeListenerSet playbackAdStateChangeListenerSet, LiveTimeWindowEventListenerSet liveTimeWindowEventListenerSet, PlaybackTimeDataChangeEventListenerSet playbackTimeDataChangeEventListenerSet, LiveEventStateListenerSet liveEventStateListenerSet, PlaybackRightsValidatedListenerSet playbackRightsValidatedListenerSet) {
        this.mMutex = new Object();
        this.mAudioTrackChangeListenerSet = audioTrackChangeListenerSet;
        this.mPlaybackContentEventListenerSet = playbackContentEventListenerSet;
        this.mStateEventListenerSet = playbackStateEventListenerSet;
        this.mQualityEventListenerSet = playbackQualityChangedEventListenerSet;
        this.mBufferEventListenerSet = playbackSessionBufferEventListenerSet;
        this.mPlaybackEventListenerSet = playbackEventListenerSet;
        this.mPerformanceEventListenerSet = playbackPerformanceEventListenerSet;
        this.mPlaybackAdStateChangeListenerSet = playbackAdStateChangeListenerSet;
        this.mLiveTimeWindowEventListenerSet = liveTimeWindowEventListenerSet;
        this.mPlaybackTimeDataChangeEventListenerSet = playbackTimeDataChangeEventListenerSet;
        this.mLiveEventStateListenerSet = liveEventStateListenerSet;
        this.mPlaybackRightsValidatedListenerSet = playbackRightsValidatedListenerSet;
    }

    public void clearAllListeners() {
        this.mAudioTrackChangeListenerSet.clear();
        this.mPlaybackContentEventListenerSet.clear();
        this.mStateEventListenerSet.clear();
        this.mQualityEventListenerSet.clear();
        this.mBufferEventListenerSet.clear();
        this.mPlaybackEventListenerSet.clear();
        this.mPerformanceEventListenerSet.clear();
        this.mPlaybackAdStateChangeListenerSet.clear();
        this.mLiveTimeWindowEventListenerSet.clear();
        this.mLiveEventStateListenerSet.clear();
    }

    public void addListener(AudioTrackChangeListener audioTrackChangeListener) {
        synchronized (this.mMutex) {
            this.mAudioTrackChangeListenerSet.addListener(audioTrackChangeListener);
        }
    }

    public void addListener(PlaybackContentEventListener playbackContentEventListener) {
        synchronized (this.mMutex) {
            this.mPlaybackContentEventListenerSet.addListener(playbackContentEventListener);
        }
    }

    public void addListener(PlaybackEventListener playbackEventListener) {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.addListener(playbackEventListener);
        }
    }

    public void addListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        synchronized (this.mMutex) {
            this.mQualityEventListenerSet.addListener(playbackQualityChangedEventListener);
        }
    }

    public void addListener(PlaybackSessionBufferEventListener playbackSessionBufferEventListener) {
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.addListener(playbackSessionBufferEventListener);
        }
    }

    public void addListener(PlaybackStateEventListener playbackStateEventListener) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.addListener(playbackStateEventListener);
        }
    }

    public void addListener(@Nonnull PlaybackAdStateChangeListener playbackAdStateChangeListener) {
        synchronized (this.mMutex) {
            this.mPlaybackAdStateChangeListenerSet.addListener(playbackAdStateChangeListener);
        }
    }

    public void addListener(PlaybackTimeDataEventListener playbackTimeDataEventListener) {
        synchronized (this.mMutex) {
            this.mPlaybackTimeDataChangeEventListenerSet.addListener(playbackTimeDataEventListener);
        }
    }

    public void removeListener(AudioTrackChangeListener audioTrackChangeListener) {
        synchronized (this.mMutex) {
            this.mAudioTrackChangeListenerSet.removeListener(audioTrackChangeListener);
        }
    }

    public void removeListener(PlaybackQualityChangedEventListener playbackQualityChangedEventListener) {
        synchronized (this.mMutex) {
            this.mQualityEventListenerSet.removeListener(playbackQualityChangedEventListener);
        }
    }

    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeStarted(@Nonnull Optional<String> optional, @Nonnull Optional<String> optional2) {
        synchronized (this.mMutex) {
            this.mAudioTrackChangeListenerSet.onAudioTrackChangeStarted(optional, optional2);
        }
    }

    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeCompleted(@Nonnull AudioTrackChangeListener.Status status) {
        synchronized (this.mMutex) {
            this.mAudioTrackChangeListenerSet.onAudioTrackChangeCompleted(status);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentMetadataAvailable(VideoResolution[] videoResolutionArr, double d) {
        synchronized (this.mMutex) {
            this.mPlaybackContentEventListenerSet.onContentMetadataAvailable(videoResolutionArr, d);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentDownloaded() {
        synchronized (this.mMutex) {
            this.mPlaybackContentEventListenerSet.onContentDownloaded();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onVideoQualityChanged(int i, VideoResolution videoResolution, VideoResolution[] videoResolutionArr, PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mQualityEventListenerSet.onVideoQualityChanged(i, videoResolution, videoResolutionArr, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onAudioQualityChanged(int i, AudioFormat audioFormat, PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mQualityEventListenerSet.onAudioQualityChanged(i, audioFormat, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onDynamicRangeChange(boolean z) {
        synchronized (this.mMutex) {
            this.mQualityEventListenerSet.onDynamicRangeChange(z);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.onBufferStart(playbackBufferEventType, playbackEventContext, bufferingAnalysis);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float f) {
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.onBufferProgress(f);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.onBufferEnd(playbackBufferEventType, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onStart(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onResume(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onPause(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onSeekStart(timeSpan, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onSeekEnd(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.LiveEventStateListener
    public void onEventEnd() {
        synchronized (this.mMutex) {
            this.mLiveEventStateListenerSet.onEventEnd();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onStop(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onRestartSuccess() {
        synchronized (this.mMutex) {
            this.mStateEventListenerSet.onRestartSuccess();
        }
    }

    public void sendBufferStartEvent(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        DLog.enterf("Type: %s", playbackBufferEventType);
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.onBufferStart(playbackBufferEventType, playbackEventContext, bufferingAnalysis);
        }
    }

    public void sendBufferEndEvent(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        synchronized (this.mMutex) {
            this.mBufferEventListenerSet.onBufferEnd(playbackBufferEventType, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onCompletion() {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.onCompletion();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onError(MediaErrorCode mediaErrorCode) {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.onError(mediaErrorCode);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onPrepared(PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.onPrepared(playbackDataSource);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onStarted(@Nonnull PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.onStarted(playbackDataSource);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onTerminated() {
        synchronized (this.mMutex) {
            this.mPlaybackEventListenerSet.onTerminated();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackLoaded() {
        synchronized (this.mMutex) {
            this.mPlaybackAdStateChangeListenerSet.onAdPlaybackLoaded();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPlaying() {
        synchronized (this.mMutex) {
            this.mPlaybackAdStateChangeListenerSet.onAdPlaybackPlaying();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPaused() {
        synchronized (this.mMutex) {
            this.mPlaybackAdStateChangeListenerSet.onAdPlaybackPaused();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackCompleted() {
        synchronized (this.mMutex) {
            this.mPlaybackAdStateChangeListenerSet.onAdPlaybackCompleted();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackTimeDataEventListener
    public void onTimeDataChange(long j) {
        synchronized (this.mMutex) {
            this.mPlaybackTimeDataChangeEventListenerSet.onTimeDataChange(j);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackRightsValidatedListener
    public void onPlaybackRightsValidated(@Nonnull String str, @Nullable String str2) {
        synchronized (this.mMutex) {
            this.mPlaybackRightsValidatedListenerSet.onPlaybackRightsValidated(str, str2);
        }
    }
}
