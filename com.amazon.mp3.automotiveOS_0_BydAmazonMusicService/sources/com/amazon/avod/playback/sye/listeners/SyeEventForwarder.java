package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.event.playback.AudioTrackChangeEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.sye.AudioCodecKt;
import com.amazon.avod.playback.sye.SyeDomainHolder;
import com.amazon.avod.playback.sye.SyeDomainVendingMachine;
import com.amazon.avod.playback.sye.events.SyeErrorEvent;
import com.amazon.avod.playback.sye.events.SyePlaybackStartEvent;
import com.amazon.avod.playback.sye.events.SyeUDPBlockEvent;
import com.amazon.avod.qos.reporter.HeuristicPlatform;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener;
import com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener;
import com.netinsight.sye.syeClient.playerListeners.IErrorListener;
import com.netinsight.sye.syeClient.playerListeners.IStateChangeListener;
import com.netinsight.sye.syeClient.playerListeners.IStatusListener;
import com.netinsight.sye.syeClient.playerListeners.ITeardownListener;
import com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener;
import com.netinsight.sye.syeClient.status.SyeFrontendInfo;
import com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SyeEventForwarder implements IStateChangeListener, IVideoTrackListener, IAudioTrackListener, ITeardownListener, IErrorListener, IEgressInfoListener, IStatusListener, ISeekActionListener {
    private ScheduledFuture<?> mAISTimeoutFuture;
    private final AloysiusPlaybackReporter mAloysiusPlaybackReporter;
    private final SyeConfig mConfig;
    private final ScheduledExecutorService mExecutor;
    private final PlaybackEventTransport mPlaybackEventBus;
    private final PlaybackListenerProxy mPlaybackListenerProxy;
    private final PlaybackMediaEventReporters mReporters;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;
    private final ISyePlayer mSyePlayer;
    private final SyeTimelineHolder mTimelineHolder;
    private final VideoSpecification mVideoSpecification;
    private boolean mHasStarted = false;
    private boolean mInterrupted = false;
    private boolean mSeekStarted = false;
    private boolean mTimeToFirstFrameReported = false;
    private volatile boolean mHasContactedEgress = false;
    private volatile boolean mHasContactedFrontend = false;
    private Stopwatch mEgressContactingStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAvailableAudioTracks(@Nonnull List<ISyeAudioTrack> list) {
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onErrorRetry(@Nonnull SyePlayerError syePlayerError, @Nonnull String str, int i) {
    }

    public SyeEventForwarder(@Nonnull ISyePlayer iSyePlayer, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull SyeTimelineHolder syeTimelineHolder, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull SyeConfig syeConfig, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull VideoSpecification videoSpecification) {
        this.mSyePlayer = (ISyePlayer) Preconditions.checkNotNull(iSyePlayer, "syePlayer");
        this.mPlaybackListenerProxy = (PlaybackListenerProxy) Preconditions.checkNotNull(playbackListenerProxy, "playbackListenerProxy");
        this.mPlaybackEventBus = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mTimelineHolder = (SyeTimelineHolder) Preconditions.checkNotNull(syeTimelineHolder, "timelineHolder");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        this.mConfig = (SyeConfig) Preconditions.checkNotNull(syeConfig, "config");
        PlaybackMediaEventReporters playbackMediaEventReporters2 = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "reporters");
        this.mReporters = playbackMediaEventReporters2;
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "specification");
        this.mAloysiusPlaybackReporter = playbackMediaEventReporters2.getPlaybackReporter();
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onFrontendSuccess(@Nonnull SyeFrontendInfo syeFrontendInfo) {
        this.mHasContactedFrontend = true;
        DLog.logf("SyeEventForwarder#onFrontendSuccess : %s, %s", syeFrontendInfo, this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId());
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onFrontendError(@Nonnull SyePlayerError syePlayerError, @Nonnull SyeFrontendInfo syeFrontendInfo, @Nonnull String str) {
        DLog.warnf("SyeEventForwarder#onFrontendError %s : %s : %s", syePlayerError, str, syeFrontendInfo);
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStatusListener
    public void onStreamingError(@Nonnull SyePlayerError syePlayerError, @Nonnull String str) {
        DLog.warnf("SyeEventForwarder#onStreamingError %s : %s", syePlayerError, str);
        this.mPlaybackEventBus.postEvent(new SyeErrorEvent(syePlayerError, str, false));
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStateChangeListener
    public void onStateChange(@Nonnull SyePlayerState syePlayerState, @Nonnull SyePlayerState syePlayerState2) {
        DLog.logf("SyeEventForwarder#onStateChange from %s -> %s", syePlayerState, syePlayerState2);
        long currentPosUtcMillis = this.mTimelineHolder.getCurrentPosUtcMillis();
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(currentPosUtcMillis);
        int[] iArr = AnonymousClass1.$SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState;
        if (iArr[syePlayerState.ordinal()] == 1) {
            clearAISHandleTimeout();
        }
        switch (iArr[syePlayerState2.ordinal()]) {
            case 1:
                if (!this.mHasContactedEgress) {
                    this.mPlaybackEventBus.postEvent(new SyeUDPBlockEvent());
                    this.mPlaybackListenerProxy.onError(SyeMediaErrorCodeExtra.UDP_BLOCKED);
                } else {
                    setAISHandleTimeout();
                }
                if (this.mHasStarted && !this.mInterrupted) {
                    this.mPlaybackListenerProxy.onBufferStart(PlaybackBufferEventType.UNEXPECTED, new PlaybackEventContext(currentPosUtcMillis), null);
                    BufferStartEvent bufferStartEvent = new BufferStartEvent(timeSpanFromMilliseconds, false, null);
                    this.mPlaybackEventBus.postEvent(bufferStartEvent);
                    this.mAloysiusPlaybackReporter.handleBufferStartEvent(bufferStartEvent);
                    this.mInterrupted = true;
                    break;
                }
                break;
            case 2:
                if (!this.mHasStarted) {
                    this.mPlaybackListenerProxy.onBufferStart(PlaybackBufferEventType.INITIAL_LOADING, new PlaybackEventContext(currentPosUtcMillis), null);
                }
                break;
            case 3:
                if (this.mHasStarted) {
                    this.mPlaybackListenerProxy.onResume(new PlaybackEventContext(currentPosUtcMillis));
                    ResumeEvent resumeEvent = new ResumeEvent(timeSpanFromMilliseconds);
                    this.mPlaybackEventBus.postEvent(resumeEvent);
                    this.mAloysiusPlaybackReporter.handleResumeEvent(resumeEvent);
                    if (this.mSeekStarted) {
                        this.mPlaybackListenerProxy.onSeekEnd(new PlaybackEventContext(currentPosUtcMillis));
                        this.mSeekStarted = false;
                    }
                    if (this.mInterrupted) {
                        this.mPlaybackListenerProxy.onBufferEnd(PlaybackBufferEventType.UNEXPECTED, new PlaybackEventContext(currentPosUtcMillis));
                    }
                    this.mInterrupted = false;
                }
                if (!this.mHasStarted && syePlayerState == SyePlayerState.Loading) {
                    SyePlaybackStartEvent syePlaybackStartEvent = new SyePlaybackStartEvent(timeSpanFromMilliseconds);
                    this.mPlaybackEventBus.postEvent(syePlaybackStartEvent);
                    this.mAloysiusPlaybackReporter.handleSyePlaybackStartEvent(syePlaybackStartEvent);
                    this.mHasStarted = true;
                    break;
                }
                break;
            case 4:
                this.mPlaybackListenerProxy.onPause(new PlaybackEventContext(currentPosUtcMillis));
                PauseEvent pauseEvent = new PauseEvent(timeSpanFromMilliseconds);
                this.mPlaybackEventBus.postEvent(pauseEvent);
                this.mAloysiusPlaybackReporter.handlePauseEvent(pauseEvent);
                break;
            case 5:
            case 6:
                if (this.mHasStarted && !this.mInterrupted) {
                    this.mPlaybackListenerProxy.onBufferStart(PlaybackBufferEventType.UNEXPECTED, new PlaybackEventContext(currentPosUtcMillis), null);
                    BufferStartEvent bufferStartEvent2 = new BufferStartEvent(timeSpanFromMilliseconds, false, null);
                    this.mPlaybackEventBus.postEvent(bufferStartEvent2);
                    this.mAloysiusPlaybackReporter.handleBufferStartEvent(bufferStartEvent2);
                    this.mInterrupted = true;
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.sye.listeners.SyeEventForwarder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState;

        static {
            int[] iArr = new int[SyePlayerState.values().length];
            $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState = iArr;
            try {
                iArr[SyePlayerState.AIS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState[SyePlayerState.Loading.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState[SyePlayerState.Playing.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState[SyePlayerState.Stopped.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState[SyePlayerState.Stalled.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$netinsight$sye$syeClient$generated$enums$SyePlayerState[SyePlayerState.Error.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener
    public void onVideoStreamChange(@Nonnull ISyeVideoStreamInfo iSyeVideoStreamInfo) {
        VideoResolution videoResolution = new VideoResolution(iSyeVideoStreamInfo.getWidth(), iSyeVideoStreamInfo.getHeight());
        this.mPlaybackListenerProxy.onVideoQualityChanged(iSyeVideoStreamInfo.getBitrate(), videoResolution, new VideoResolution[]{videoResolution}, new PlaybackEventContext(this.mTimelineHolder.getCurrentPosUtcMillis()));
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.ITeardownListener
    public void onTeardown() {
        long currentPosUtcMillis = this.mTimelineHolder.getCurrentPosUtcMillis();
        this.mPlaybackListenerProxy.onStop(new PlaybackEventContext(currentPosUtcMillis));
        this.mPlaybackEventBus.postEvent(new PlaybackStopEvent(TimeSpan.fromMilliseconds(currentPosUtcMillis), "SyeVideoPlayer", DrmScheme.NONE.toString()));
        this.mPlaybackListenerProxy.onTerminated();
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onError(@Nonnull SyePlayerError syePlayerError, @Nonnull String str) {
        DLog.errorf("%s: %s", syePlayerError, str);
        if (SyePlayerError.TimeshiftOutOfRange.equals(syePlayerError)) {
            DLog.warnf("Encountered %s, attempting to play from live", syePlayerError);
            this.mSyePlayer.playFromLive(this.mSyeDomainVendingMachine.currentSyeDomain().getChannelId());
            return;
        }
        String consumptionId = this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId();
        SyeDomainHolder syeDomainHolderNextSyeDomain = this.mSyeDomainVendingMachine.nextSyeDomain();
        if (syeDomainHolderNextSyeDomain != null) {
            DLog.logf("SyeEventForwarder#onError, switching to SyeDomain %s : %s", syeDomainHolderNextSyeDomain.getSyeDomain().getBaseUrl(), syeDomainHolderNextSyeDomain.getSyeDomain().getName());
            DLog.logf("SyeEventForwarder#onError, switching consumptionId from %s : to %s", consumptionId, syeDomainHolderNextSyeDomain.getConsumptionId());
            this.mReporters.getPlaybackReporter().updateConsumptionId(syeDomainHolderNextSyeDomain.getConsumptionId());
            this.mSyePlayer.updateSyeSystem(new SyeSystem(syeDomainHolderNextSyeDomain.getSyeDomain(), syeDomainHolderNextSyeDomain.getToken()));
            this.mSyePlayer.playResume();
            if (this.mHasContactedFrontend && this.mHasContactedEgress) {
                this.mReporters.getAloysiusFailoverReporter().handleLiveStreamingRestartEvent(LiveStreamingRestartEvent.newOriginSwitchEvent(this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId(), null, System.currentTimeMillis(), false, null, null, consumptionId));
                return;
            }
            return;
        }
        if (this.mHasContactedFrontend && !this.mHasContactedEgress) {
            DLog.warnf("SyeEventForwarder#onError, SyeDomains are exhausted, Egress not contacted, notifying UDP_BLOCKED");
            this.mPlaybackEventBus.postEvent(new SyeUDPBlockEvent());
            this.mPlaybackListenerProxy.onError(SyeMediaErrorCodeExtra.UDP_BLOCKED);
        } else {
            DLog.warnf("SyeEventForwarder#onError, SyeDomains are exhausted, notifying error");
            this.mPlaybackEventBus.postEvent(new SyeErrorEvent(syePlayerError, str, true));
            this.mPlaybackListenerProxy.onError(new SyeMediaErrorCode(syePlayerError, str));
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAudioStreamChange(@Nonnull ISyeAudioStreamInfo iSyeAudioStreamInfo) {
        long currentPosUtcMillis = this.mTimelineHolder.getCurrentPosUtcMillis();
        this.mPlaybackListenerProxy.onAudioQualityChanged(iSyeAudioStreamInfo.getSampleRate(), AudioCodecKt.fromSyeAudioCodec(iSyeAudioStreamInfo.getCodec(), iSyeAudioStreamInfo.getCodecProfile()), new PlaybackEventContext(currentPosUtcMillis));
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(currentPosUtcMillis);
        String language = iSyeAudioStreamInfo.getLanguage();
        this.mReporters.getAloysiusInteractionReporter().reportAudioTrackChange();
        this.mPlaybackEventBus.postEvent(new AudioTrackChangeEvent(timeSpanFromMilliseconds, language, this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId(), language));
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public void onEgressAllocated(int i) {
        if (this.mHasStarted || this.mEgressContactingStopwatch.isRunning()) {
            return;
        }
        this.mEgressContactingStopwatch.start();
        this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.playback.sye.listeners.SyeEventForwarder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEgressAllocated$0();
            }
        }, this.mConfig.getEgressContactTimeThresholdMillis(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onEgressAllocated$0() {
        if (this.mHasContactedEgress) {
            return;
        }
        this.mPlaybackEventBus.postEvent(new SyeUDPBlockEvent());
        this.mPlaybackListenerProxy.onError(SyeMediaErrorCodeExtra.UDP_BLOCKED);
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public void onEgressContact(int i) {
        if (this.mHasStarted) {
            return;
        }
        DLog.logf("SyeEventForwarder#onEgressContact after %s ms", Long.valueOf(this.mEgressContactingStopwatch.elapsed(TimeUnit.MILLISECONDS)));
        this.mHasContactedEgress = true;
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener
    public synchronized void onTimeToFirstFrame(int i, int i2) {
        DLog.logf("SyeEventForwarder#onTimeToFirstFrame - egressTTFFMillis %s,  totalTTFFMillis %s", Integer.valueOf(i), Integer.valueOf(i2));
        if (!this.mHasStarted) {
            if (this.mTimeToFirstFrameReported) {
                DLog.logf("SyeEventForwarder#onTimeToFirstFrame - Dropping duplicate call");
                return;
            }
            this.mTimeToFirstFrameReported = true;
            long currentPosUtcMillis = this.mTimelineHolder.getCurrentPosUtcMillis();
            TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(currentPosUtcMillis);
            PlaybackListenerProxy playbackListenerProxy = this.mPlaybackListenerProxy;
            PlaybackDataSource playbackDataSource = PlaybackDataSource.STREAMING;
            playbackListenerProxy.onPrepared(playbackDataSource);
            this.mPlaybackListenerProxy.onStart(new PlaybackEventContext(currentPosUtcMillis));
            this.mPlaybackListenerProxy.onBufferEnd(PlaybackBufferEventType.INITIAL_LOADING, new PlaybackEventContext(currentPosUtcMillis));
            this.mPlaybackListenerProxy.onStarted(playbackDataSource);
            PlaybackLoadedEvent playbackLoadedEvent = new PlaybackLoadedEvent(timeSpanFromMilliseconds, this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId());
            this.mPlaybackEventBus.postEvent(playbackLoadedEvent);
            this.mAloysiusPlaybackReporter.handlePlaybackLoadedEvent(playbackLoadedEvent);
            if (this.mVideoSpecification.getPlaybackSettings() != null) {
                this.mReporters.getAloysiusPlayerHeuristicReporter().reportAloysiusPlayerHeuristicEvent(this.mVideoSpecification.getPlaybackSettings().settingsId.orNull(), HeuristicPlatform.Miyagi);
            }
            DLog.logf("SyeEventForwarder#onTimeToFirstFrame - Player started");
        } else {
            DLog.logf("SyeEventForwarder#onTimeToFirstFrame - Player restarted");
        }
    }

    private void setAISHandleTimeout() {
        this.mAISTimeoutFuture = this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.playback.sye.listeners.SyeEventForwarder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setAISHandleTimeout$1();
            }
        }, this.mConfig.getMaxRetryTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAISHandleTimeout$1() {
        this.mPlaybackEventBus.postEvent(new SyeUDPBlockEvent());
        this.mPlaybackListenerProxy.onError(SyeMediaErrorCodeExtra.UDP_BLOCKED);
        clearAISHandleTimeout();
    }

    private void clearAISHandleTimeout() {
        ScheduledFuture<?> scheduledFuture = this.mAISTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mAISTimeoutFuture = null;
        }
    }

    @Override // com.amazon.avod.playback.sye.listeners.ISeekActionListener
    public void onSeekStart(long j) {
        long currentPosUtcMillis = this.mTimelineHolder.getCurrentPosUtcMillis();
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(currentPosUtcMillis);
        TimeSpan timeSpanFromMilliseconds2 = TimeSpan.fromMilliseconds(j);
        if (this.mHasStarted) {
            this.mReporters.getAloysiusInteractionReporter().reportSeekEvent(timeSpanFromMilliseconds, timeSpanFromMilliseconds2);
            this.mPlaybackListenerProxy.onSeekStart(timeSpanFromMilliseconds, new PlaybackEventContext(currentPosUtcMillis));
            this.mAloysiusPlaybackReporter.handleSeekStart(new SeekStartEvent(timeSpanFromMilliseconds, timeSpanFromMilliseconds2, false));
            this.mSeekStarted = true;
        }
    }
}
