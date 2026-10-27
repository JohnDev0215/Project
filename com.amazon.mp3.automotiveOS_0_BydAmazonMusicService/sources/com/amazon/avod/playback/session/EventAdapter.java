package com.amazon.avod.playback.session;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.AutoEvalPlayerPerformanceEvaluator;
import com.amazon.avod.playback.HighFrameRatePlayerPerformanceEvaluator;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.config.DeviceRebootErrorConfig;
import com.amazon.avod.playback.config.EventAdapterConfig;
import com.amazon.avod.playback.event.AudioQualityChangeEvent;
import com.amazon.avod.playback.event.LiveTimeWindowRestrictedEvent;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.event.PlaybackMetadataAvailableEvent;
import com.amazon.avod.playback.event.PlaybackRightsValidatedEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.AdCompletedEvent;
import com.amazon.avod.playback.event.playback.AdStartEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.BufferStatusEvent;
import com.amazon.avod.playback.event.playback.BufferStopEvent;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.InitialLoadingEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackCompletedEvent;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.PlaybackNeedsTerminationEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.SeekEndEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.event.playback.SeekUpdateEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.playback.event.playback.TimeDataChangeEvent;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.smoothstream.RestartBeginEvent;
import com.amazon.avod.playback.smoothstream.RestartEndEvent;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class EventAdapter {
    private final AutoEvalPlayerPerformanceEvaluator mAutoEvalPlayerPerformanceEvaluator;
    private final EventAdapterConfig mConfig;
    private final DeviceRebootErrorConfig mDeviceRebootErrorConfig;
    private final BaseDrmSystem mDrmSystem;
    private boolean mForwardPlaybackEvents;
    private final HighFrameRatePlayerPerformanceEvaluator mHighFrameRatePlayerPerformanceEvaluator;
    private final PlaybackListenerProxy mListenerProxy;
    private final AmazonVideoPlayer mOwner;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private PlaybackRestartEvent mPlaybackRestartEvent;

    EventAdapter(BaseDrmSystem baseDrmSystem, PlaybackListenerProxy playbackListenerProxy, PlaybackEventTransport playbackEventTransport, AmazonVideoPlayer amazonVideoPlayer, DeviceRebootErrorConfig deviceRebootErrorConfig, @Nonnull HighFrameRatePlayerPerformanceEvaluator highFrameRatePlayerPerformanceEvaluator, @Nonnull AutoEvalPlayerPerformanceEvaluator autoEvalPlayerPerformanceEvaluator, @Nonnull EventAdapterConfig eventAdapterConfig) {
        this.mDrmSystem = baseDrmSystem;
        this.mListenerProxy = playbackListenerProxy;
        this.mPlaybackEventTransport = playbackEventTransport;
        playbackEventTransport.registerEventBusHandler(this);
        this.mOwner = amazonVideoPlayer;
        this.mDeviceRebootErrorConfig = deviceRebootErrorConfig;
        this.mHighFrameRatePlayerPerformanceEvaluator = (HighFrameRatePlayerPerformanceEvaluator) Preconditions.checkNotNull(highFrameRatePlayerPerformanceEvaluator, "highFrameRatePlayerPerformanceEvaluator");
        this.mAutoEvalPlayerPerformanceEvaluator = (AutoEvalPlayerPerformanceEvaluator) Preconditions.checkNotNull(autoEvalPlayerPerformanceEvaluator, "autoEvalPlayerPerformanceEvaluator");
        this.mForwardPlaybackEvents = true;
        this.mConfig = (EventAdapterConfig) Preconditions.checkNotNull(eventAdapterConfig, "eventAdapterConfig");
    }

    @Subscribe
    public void handleRestartBeginEvent(RestartBeginEvent restartBeginEvent) {
        Optional<String> optionalOf;
        Optional<String> optionalOf2;
        DLog.logf("Event adapter received RestartBeginEvent of type " + restartBeginEvent.getCause().getTypeString());
        this.mForwardPlaybackEvents = false;
        this.mOwner.onRestartBegin();
        PlaybackRestartEvent cause = restartBeginEvent.getCause();
        this.mPlaybackRestartEvent = cause;
        int type = cause.getType();
        if (type != 0) {
            if (type == 1) {
                this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.LIVE_ORIGIN_FAILOVER, new PlaybackEventContext(this.mOwner.getCurrentPosition()), null);
                return;
            } else if (type == 3) {
                this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.LIVE_KEY_ROTATION, new PlaybackEventContext(this.mOwner.getCurrentPosition()), null);
                return;
            } else if (type != 11) {
                return;
            }
        }
        ImmutableList<String> audioTrackIds = restartBeginEvent.getOldSpec().getAudioTrackIds();
        ImmutableList<String> audioTrackIds2 = restartBeginEvent.getNewSpec().getAudioTrackIds();
        if (audioTrackIds.size() == 0) {
            optionalOf = Optional.absent();
        } else {
            optionalOf = Optional.of(audioTrackIds.get(0));
        }
        if (audioTrackIds2.size() == 0) {
            optionalOf2 = Optional.absent();
        } else {
            optionalOf2 = Optional.of(audioTrackIds2.get(0));
        }
        this.mListenerProxy.onAudioTrackChangeStarted(optionalOf, optionalOf2);
    }

    @Subscribe
    public void handleStatusEvent(@Nonnull StatusEvent statusEvent) {
        Preconditions.checkNotNull(statusEvent, "StatusEvent");
        this.mHighFrameRatePlayerPerformanceEvaluator.processPerformanceData(statusEvent.getEventTimeStamp(), statusEvent.getRendererPerformanceData());
        this.mAutoEvalPlayerPerformanceEvaluator.processPerformanceData(statusEvent);
        this.mListenerProxy.onTimeDataChange(TimeUnit.MILLISECONDS.convert(statusEvent.getLastRenderedTimestampInNanoseconds(), TimeUnit.NANOSECONDS));
    }

    @Subscribe
    public void handleTimeDataChangeEvent(@Nonnull TimeDataChangeEvent timeDataChangeEvent) {
        Preconditions.checkNotNull(timeDataChangeEvent, "timeDataChangeEvent");
        this.mListenerProxy.onTimeDataChange(timeDataChangeEvent.getPlaybackCurrentPosition());
    }

    @Subscribe
    public void handleInitialLoadingEvent(InitialLoadingEvent initialLoadingEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.INITIAL_LOADING, getEventContext(initialLoadingEvent), null);
        }
    }

    @Subscribe
    public void handlePlaybackStartEvent(PlaybackStartEvent playbackStartEvent) {
        if (this.mForwardPlaybackEvents) {
            PlaybackEventContext eventContext = getEventContext(playbackStartEvent);
            this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.INITIAL_LOADING, eventContext);
            this.mListenerProxy.onStart(eventContext);
            this.mListenerProxy.onDynamicRangeChange(playbackStartEvent.isHdr());
            this.mOwner.notifyStartedHelper();
        }
        if (!this.mForwardPlaybackEvents) {
            onRestartEnd(true);
        }
        this.mForwardPlaybackEvents = true;
    }

    private void onRestartEnd(boolean z) {
        this.mOwner.onRestartEnd(z);
        this.mPlaybackEventTransport.postEvent(new RestartEndEvent(z));
        int type = this.mPlaybackRestartEvent.getType();
        if (type != 0) {
            if (type == 1) {
                this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.LIVE_ORIGIN_FAILOVER, new PlaybackEventContext(this.mOwner.getCurrentPosition()));
                return;
            }
            if (type == 3) {
                this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.LIVE_KEY_ROTATION, new PlaybackEventContext(this.mOwner.getCurrentPosition()));
                return;
            }
            if (type == 4) {
                this.mPlaybackEventTransport.postEvent(new BufferStopEvent(TimeSpan.fromMilliseconds(this.mOwner.getCurrentPosition()), false));
                return;
            } else if (type == 5) {
                this.mPlaybackEventTransport.postEvent(new SeekEndEvent(TimeSpan.fromMilliseconds(this.mPlaybackRestartEvent.getTimeBeforeRestartMillis()), TimeSpan.fromMilliseconds(this.mOwner.getCurrentPosition()), false, SeekAction.SeekCause.AUDIO_TRACK_RECREATION.name()));
                return;
            } else if (type != 11) {
                return;
            }
        }
        this.mListenerProxy.onAudioTrackChangeCompleted(z ? AudioTrackChangeListener.Status.SUCCESS : AudioTrackChangeListener.Status.FAILURE);
    }

    @Subscribe
    public void handlePlaybackStopEvent(PlaybackStopEvent playbackStopEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onStop(getEventContext(playbackStopEvent));
        }
    }

    @Subscribe
    public void handleSeekStartEvent(SeekStartEvent seekStartEvent) {
        PlaybackEventContext eventContext = getEventContext(seekStartEvent);
        if (!seekStartEvent.isInCachedRegion()) {
            this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.SEEK, eventContext, null);
        }
        this.mListenerProxy.onSeekStart(seekStartEvent.getTargetTime(), eventContext);
        this.mAutoEvalPlayerPerformanceEvaluator.onPlaybackStop();
    }

    @Subscribe
    public void handleSeekUpdateEvent(SeekUpdateEvent seekUpdateEvent) {
        PlaybackEventContext eventContext = getEventContext(seekUpdateEvent);
        if (seekUpdateEvent.wasInCachedRegion() && !seekUpdateEvent.isNowInCachedRegion()) {
            this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.SEEK, eventContext, null);
        } else {
            if (seekUpdateEvent.wasInCachedRegion() || !seekUpdateEvent.isNowInCachedRegion()) {
                return;
            }
            this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.SEEK, eventContext);
        }
    }

    @Subscribe
    public void handleSeekEndEvent(SeekEndEvent seekEndEvent) {
        this.mOwner.onSeekEnd();
        PlaybackEventContext eventContext = getEventContext(seekEndEvent);
        if (!seekEndEvent.isInCachedRegion()) {
            this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.SEEK, eventContext);
        }
        this.mListenerProxy.onSeekEnd(eventContext);
    }

    @Subscribe
    public void handlePauseEvent(PauseEvent pauseEvent) {
        this.mListenerProxy.onPause(getEventContext(pauseEvent));
        this.mAutoEvalPlayerPerformanceEvaluator.onPlaybackStop();
    }

    @Subscribe
    public void handleResumeEvent(ResumeEvent resumeEvent) {
        this.mListenerProxy.onResume(getEventContext(resumeEvent));
    }

    @Subscribe
    public void handleBufferStartEvent(BufferStartEvent bufferStartEvent) {
        if (!this.mForwardPlaybackEvents || bufferStartEvent.isInCachedRegion()) {
            return;
        }
        this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.UNEXPECTED, getEventContext(bufferStartEvent), bufferStartEvent.getBufferingAnalysis());
    }

    @Subscribe
    public void handleBufferStopEvent(BufferStopEvent bufferStopEvent) {
        if (!this.mForwardPlaybackEvents || bufferStopEvent.isInCachedRegion()) {
            return;
        }
        this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.UNEXPECTED, getEventContext(bufferStopEvent));
    }

    @Subscribe
    public void handleBufferStatusEvent(BufferStatusEvent bufferStatusEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onBufferProgress(bufferStatusEvent.getProgressPercent());
            if (!bufferStatusEvent.wasInCachedRegion() && bufferStatusEvent.isNowInCachedRegion()) {
                this.mListenerProxy.sendBufferEndEvent(PlaybackBufferEventType.UNEXPECTED, getEventContext(bufferStatusEvent));
            } else {
                if (!bufferStatusEvent.wasInCachedRegion() || bufferStatusEvent.isNowInCachedRegion()) {
                    return;
                }
                this.mListenerProxy.sendBufferStartEvent(PlaybackBufferEventType.UNEXPECTED, getEventContext(bufferStatusEvent), null);
            }
        }
    }

    @Subscribe
    public void handleAudioQualityChangeEvent(AudioQualityChangeEvent audioQualityChangeEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onAudioQualityChanged(audioQualityChangeEvent.getBitrate(), audioQualityChangeEvent.getAudioFormat(), getEventContext(audioQualityChangeEvent));
        }
    }

    @Subscribe
    public void handleVideoQualityChangeEvent(VideoQualityChangeEvent videoQualityChangeEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onVideoQualityChanged(videoQualityChangeEvent.getBitrate(), videoQualityChangeEvent.getResolution(), videoQualityChangeEvent.getAvailableResolutions(), getEventContext(videoQualityChangeEvent));
        }
    }

    @Subscribe
    public void handleAdStartEvent(AdStartEvent adStartEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onAdPlaybackPlaying();
        }
    }

    @Subscribe
    public void handleAdCompletedEvent(AdCompletedEvent adCompletedEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onAdPlaybackCompleted();
        }
    }

    @Subscribe
    public void handlePlaybackMetadataAvailableEvent(PlaybackMetadataAvailableEvent playbackMetadataAvailableEvent) {
        if (this.mDeviceRebootErrorConfig.shouldForwardPlaybackMetadataAvailableEventDuringRestart() || this.mForwardPlaybackEvents) {
            this.mListenerProxy.onContentMetadataAvailable(playbackMetadataAvailableEvent.getAvailableResolutions(), playbackMetadataAvailableEvent.getVideoFramerateFps());
        }
    }

    @Subscribe
    public void handlePreparedEvent(PlaybackLoadedEvent playbackLoadedEvent) {
        this.mOwner.onPrepared();
    }

    @Subscribe
    public void handleErrorEvent(FatalPlaybackErrorEvent fatalPlaybackErrorEvent) {
        MediaException playbackException = fatalPlaybackErrorEvent.getPlaybackException();
        MediaException mediaExceptionHandlePossibleAutoResetException = this.mDrmSystem.handlePossibleAutoResetException(playbackException);
        DLog.errorf("MediaException caught!  initial exception: %s, translated to: %s", playbackException, mediaExceptionHandlePossibleAutoResetException);
        if (!this.mForwardPlaybackEvents) {
            onRestartEnd(false);
        }
        this.mOwner.notifyErrorHelper(this.mDeviceRebootErrorConfig.getPlaybackError(mediaExceptionHandlePossibleAutoResetException));
        this.mOwner.terminate(false, playbackException);
    }

    @Subscribe
    public void handleErrorEvent(@Nonnull PlaybackNeedsTerminationEvent playbackNeedsTerminationEvent) {
        Preconditions.checkNotNull(playbackNeedsTerminationEvent, NotificationCompat.CATEGORY_EVENT);
        DLog.logf("terminating playback based on PlaybackNeedsTerminationEvent with cause %s", playbackNeedsTerminationEvent.getCause());
        this.mOwner.terminate(false, playbackNeedsTerminationEvent.getCause());
    }

    @Subscribe
    public void handleErrorEvent(RetriablePlaybackErrorEvent retriablePlaybackErrorEvent) {
        MediaException playbackException = retriablePlaybackErrorEvent.getPlaybackException();
        MediaInternalErrorCode errorCode = playbackException.getErrorCode();
        if (errorCode == PlaybackException.PlaybackError.CONTENT_BUFFERING_NO_DATA_CONNECTION || errorCode == PlaybackException.PlaybackError.PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED || errorCode == PlaybackException.PlaybackError.DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED) {
            this.mOwner.onContentBufferingError(playbackException, errorCode);
        }
    }

    @Subscribe
    public void handleCompletedEvent(PlaybackCompletedEvent playbackCompletedEvent) {
        if (this.mForwardPlaybackEvents) {
            if (this.mConfig.shouldJitterClosePlayerSignal() && playbackCompletedEvent.isPlayerCloseDriven()) {
                DLog.errorf("Jittering playback completed based on close player signal");
                this.mListenerProxy.onEventEnd();
            } else if (this.mConfig.shouldJitterStaticManifestEnd() && !playbackCompletedEvent.getContext().getManifest().isDynamic() && playbackCompletedEvent.getContext().getSpecification().isLiveStream()) {
                DLog.errorf("Jittering playback completed based on last frame in static live manifest");
                this.mListenerProxy.onEventEnd();
            } else {
                this.mOwner.notifyCompletedHelper();
            }
        }
    }

    @Subscribe
    public void handleRequestRestartEvent(PlaybackRestartEvent playbackRestartEvent) {
        this.mOwner.restart(playbackRestartEvent);
    }

    @Subscribe
    public void handleLiveTimeWindowRestrictedEvent(LiveTimeWindowRestrictedEvent liveTimeWindowRestrictedEvent) {
        if (this.mForwardPlaybackEvents) {
            throw null;
        }
    }

    @Subscribe
    public void handlePlaybackRightsValidatedEvent(@Nonnull PlaybackRightsValidatedEvent playbackRightsValidatedEvent) {
        if (this.mForwardPlaybackEvents) {
            this.mListenerProxy.onPlaybackRightsValidated(playbackRightsValidatedEvent.getPlaybackAuthorityId(), playbackRightsValidatedEvent.getSessionToken());
        }
    }

    private PlaybackEventContext getEventContext(PlaybackEvent playbackEvent) {
        return new PlaybackEventContext(playbackEvent.getEventTimeStamp().getTotalMilliseconds());
    }
}
