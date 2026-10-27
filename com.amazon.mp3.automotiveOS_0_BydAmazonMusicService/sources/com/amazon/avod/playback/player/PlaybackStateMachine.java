package com.amazon.avod.playback.player;

import amazon.android.config.ConfigurationValue;
import android.os.Process;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerConfig;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.drm.DecryptionContextFactory;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.AVSyncDebugReporter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.PlaybackNeedsTerminationEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.ActionType;
import com.amazon.avod.playback.player.actions.InitializeAction;
import com.amazon.avod.playback.player.actions.ShutdownAction;
import com.amazon.avod.playback.player.states.PlaybackEngineState;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.amazon.avod.playback.player.states.PlaybackStateFactory;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.threading.ProfiledThread;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PlaybackStateMachine {
    private final PlaybackActionQueue mActionQueue;
    private final ErrorReportingConfiguration mErrorConfig;
    private final int mErrorSeverityLevelToUploadManifest;
    private final PlaybackEventTransport mEventTransport;
    private volatile boolean mIsShutdownEnqueued;
    private final boolean mIsUploadManifestsWhenFatalEnabled;
    private final ManifestCapturerInterface mManifestCapturer;
    private final RendererSchemeController mRendererSchemeController;
    private final boolean mShouldChangeThreadPriorityBetweenStates;
    private PlaybackEngineState mState;
    private final PlaybackStateFactory mStateFactory;
    private final Thread mWorkerThread;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        Process.setThreadPriority(-16);
        processStates();
    }

    public PlaybackStateMachine(PlaybackEventTransport playbackEventTransport, DecryptionContextFactory decryptionContextFactory, PlaybackConfig playbackConfig, VideoRenderer videoRenderer, VideoPlaybackTimeline videoPlaybackTimeline, MediaProfiler mediaProfiler, RendererScheme rendererScheme, AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        this(playbackEventTransport, decryptionContextFactory, playbackConfig, new PlaybackActionQueue(), videoRenderer, videoPlaybackTimeline, mediaProfiler, NetworkConnectionManager.getInstance(), rendererScheme, PlaybackStateMachineConfig.getInstance(), aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, manifestCapturerInterface, rendererSchemeController, aVSyncDebugReporter);
    }

    private PlaybackStateMachine(PlaybackEventTransport playbackEventTransport, DecryptionContextFactory decryptionContextFactory, PlaybackConfig playbackConfig, PlaybackActionQueue playbackActionQueue, VideoRenderer videoRenderer, VideoPlaybackTimeline videoPlaybackTimeline, MediaProfiler mediaProfiler, NetworkConnectionManager networkConnectionManager, RendererScheme rendererScheme, @Nonnull PlaybackStateMachineConfig playbackStateMachineConfig, @Nonnull AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        PlaybackStateFactory playbackStateFactory = new PlaybackStateFactory(playbackActionQueue, videoRenderer, playbackEventTransport, videoPlaybackTimeline, mediaProfiler, networkConnectionManager, decryptionContextFactory, playbackConfig, rendererScheme, aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, rendererSchemeController, aVSyncDebugReporter);
        ErrorReportingConfiguration errorReportingConfiguration = new ErrorReportingConfiguration();
        ManifestCapturerConfig manifestCapturerConfig = ManifestCapturerConfig.INSTANCE;
        this(playbackEventTransport, playbackActionQueue, playbackStateFactory, playbackStateMachineConfig, manifestCapturerInterface, errorReportingConfiguration, manifestCapturerConfig.isUploadManifestsWhenFatalEnabled(), manifestCapturerConfig.getErrorSeverityLevelToUploadManifest(), rendererSchemeController);
    }

    PlaybackStateMachine(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackActionQueue playbackActionQueue, @Nonnull PlaybackStateFactory playbackStateFactory, @Nonnull PlaybackStateMachineConfig playbackStateMachineConfig, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull ErrorReportingConfiguration errorReportingConfiguration, boolean z, int i, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mWorkerThread = new ProfiledThread(new Runnable() { // from class: com.amazon.avod.playback.player.PlaybackStateMachine$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        }, String.format(Locale.US, "%s:WorkerThread", getClass().getSimpleName()), Profiler.TraceLevel.INFO);
        this.mIsShutdownEnqueued = false;
        this.mEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mActionQueue = (PlaybackActionQueue) Preconditions.checkNotNull(playbackActionQueue, "actionQueue");
        PlaybackStateFactory playbackStateFactory2 = (PlaybackStateFactory) Preconditions.checkNotNull(playbackStateFactory, "stateFactory");
        this.mStateFactory = playbackStateFactory2;
        this.mState = playbackStateFactory2.getState(PlaybackState.Uninitialized);
        Preconditions.checkNotNull(playbackStateMachineConfig, "playbackStateMachineConfig");
        this.mShouldChangeThreadPriorityBetweenStates = playbackStateMachineConfig.shouldChangeThreadPriorityBetweenStates();
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
        this.mErrorConfig = (ErrorReportingConfiguration) Preconditions.checkNotNull(errorReportingConfiguration, "errorConfig");
        this.mIsUploadManifestsWhenFatalEnabled = z;
        this.mErrorSeverityLevelToUploadManifest = i;
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
    }

    public synchronized void startProcessing(AudioFormat audioFormat) {
        Preconditions.checkState(this.mState.getState() == PlaybackState.Uninitialized);
        this.mWorkerThread.setName("AIV.Player.PlaybackStateMachine");
        this.mWorkerThread.start();
        enqueueAction(new InitializeAction(audioFormat));
    }

    public void enqueueAction(Action action) {
        this.mActionQueue.enqueueAction(action);
    }

    public void shutdown(boolean z) {
        this.mIsShutdownEnqueued = true;
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
        DLog.logf("Starting shutdown, joining threads.");
        try {
            enqueueAction(new ShutdownAction(z));
            this.mWorkerThread.join();
        } catch (InterruptedException e) {
            DLog.exceptionf(e, "Interrupted while waiting for components to shut down.", new Object[0]);
        }
        DLog.logf("Shutdown complete in %s.", new TimeSpan(stopwatchCreateStarted.stop()));
    }

    public PlaybackEngineState getPlaybackState() {
        return this.mState;
    }

    @Nonnull
    public PlaybackStateFactory getStateFactory() {
        return this.mStateFactory;
    }

    private void processStates() {
        int httpErrorCode;
        PlaybackEngineState state = this.mStateFactory.getState(PlaybackState.Uninitialized);
        Stopwatch stopwatchCreateUnstarted = Stopwatch.createUnstarted(Tickers.androidTicker());
        Action nextAction = null;
        while (this.mState.getState() != PlaybackState.Shutdown) {
            boolean zIsRetriable = false;
            try {
                stopwatchCreateUnstarted.reset();
                stopwatchCreateUnstarted.start();
                DLog.logf("Entering playback state: %s", state.getClass().getSimpleName());
                state.onEnter(nextAction);
                this.mState = state;
                if (this.mShouldChangeThreadPriorityBetweenStates) {
                    int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$player$states$PlaybackState[state.getState().ordinal()];
                    Process.setThreadPriority(i != 1 ? i != 2 ? -16 : -4 : 0);
                }
                state = null;
                do {
                    try {
                        this.mState.onExecute();
                        nextAction = this.mActionQueue.getNextAction();
                        if (nextAction != null) {
                            PlaybackState nextState = this.mState.getNextState(nextAction);
                            if (nextState != null) {
                                state = this.mStateFactory.getState(nextState);
                            }
                            if (nextAction.getActionType() == ActionType.Shutdown && state == null) {
                                throw new PlaybackException(PlaybackException.PlaybackError.UNHANDLED_SHUTDOWN_ACTION, "Unhandled shutdown action. this should not happen!!");
                            }
                            if (state != null) {
                                DLog.logf("Current state: %s, next: %s, action: %s", this.mState.getState(), state.getState(), nextAction.getActionType());
                            }
                        }
                        if (this.mState.getState() == PlaybackState.Shutdown) {
                            break;
                        }
                    } catch (Throwable th) {
                        DLog.logf("Spent %s in playback state: %s", TimeSpan.fromMicroseconds(stopwatchCreateUnstarted.stop().elapsed(TimeUnit.MICROSECONDS)), this.mState.getClass().getSimpleName());
                        this.mState.onExit();
                        throw th;
                    }
                } while (state == null);
                DLog.logf("Spent %s in playback state: %s", TimeSpan.fromMicroseconds(stopwatchCreateUnstarted.stop().elapsed(TimeUnit.MICROSECONDS)), this.mState.getClass().getSimpleName());
                this.mState.onExit();
            } catch (MediaException e) {
                DLog.exceptionf(e, "Transitioning playback states", new Object[0]);
                long playbackTimeInNanoseconds = getPlaybackState().getPlaybackTimeInNanoseconds();
                MediaInternalErrorCode errorCode = e.getErrorCode();
                if (this.mIsShutdownEnqueued && (errorCode.equals(PlaybackException.PlaybackError.RENDERER_INITIALIZE_TIMEOUT) || errorCode.equals(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED) || errorCode.equals(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED_CODEC_CONFIG) || errorCode.equals(PlaybackException.PlaybackError.RENDERER_INITIALIZE_NULL_SURFACE) || errorCode.equals(PlaybackException.PlaybackError.RENDERER_INITIALIZE_INVALID_SURFACE))) {
                    RetriablePlaybackErrorEvent retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(new TimeSpan(playbackTimeInNanoseconds), e);
                    this.mEventTransport.postEvent(retriablePlaybackErrorEvent);
                    zIsRetriable = retriablePlaybackErrorEvent.isRetriable();
                    httpErrorCode = retriablePlaybackErrorEvent.getHttpErrorCode();
                } else if (errorCode.equals(PlaybackException.PlaybackError.CONTENT_BUFFERING_NO_DATA_CONNECTION) || errorCode.equals(PlaybackException.PlaybackError.PLAYBACK_CONTENT_BUFFERING_CONNECTION_RESTRICTED) || errorCode.equals(PlaybackException.PlaybackError.DOWNLOAD_CONTENT_BUFFERING_CONNECTION_RESTRICTED)) {
                    RetriablePlaybackErrorEvent retriablePlaybackErrorEvent2 = new RetriablePlaybackErrorEvent(new TimeSpan(playbackTimeInNanoseconds), e);
                    this.mEventTransport.postEvent(retriablePlaybackErrorEvent2);
                    zIsRetriable = retriablePlaybackErrorEvent2.isRetriable();
                    httpErrorCode = retriablePlaybackErrorEvent2.getHttpErrorCode();
                } else {
                    if (errorCode.equals(PlaybackException.PlaybackError.RENDERER_CONFIGURE_WITH_INVALID_SURFACE)) {
                        this.mEventTransport.postEvent(new PlaybackNeedsTerminationEvent(e));
                    } else if (errorCode.equals(PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER)) {
                        this.mEventTransport.postEvent(PlaybackRestartEvent.newAudioTrackDiedRestartPlayerEvent(playbackTimeInNanoseconds, e.getMessage()));
                    } else if (errorCode.equals(PlaybackException.PlaybackError.RENDERER_DECODER_STALLED)) {
                        this.mEventTransport.postEvent(PlaybackRestartEvent.newRendererDecoderStalledRestartEvent(playbackTimeInNanoseconds));
                    } else if (errorCode.equals(PlaybackException.PlaybackError.CORRUPT_FRAGMENT_ERROR)) {
                        this.mEventTransport.postEvent(PlaybackRestartEvent.newPlayerCorruptedFragmentRestartEvent(e.getMessage()));
                    } else {
                        getPlaybackState().getPlaybackStateContext().setFatalException(e);
                        if (this.mRendererSchemeController.notifyAutoEvalCanaryPlaybackFailureEvent(e)) {
                            DLog.warnf("Restarting the player as auto eval canary has failed due to: %s", e);
                            this.mEventTransport.postEvent(PlaybackRestartEvent.newAutoEvalCanaryFailedRestartEvent(TimeUnit.NANOSECONDS.toMillis(playbackTimeInNanoseconds), e.getMessage()));
                            ContentSession contentSession = getPlaybackState().getPlaybackStateContext().getContentSession();
                            if (contentSession != null) {
                                contentSession.getContext().getPlaybackEventReporter().reportMetric(QoSMetric.Information.toString(), "PlayerAutoEvalCanaryFailed", TimeSpan.ZERO, String.format(Locale.US, "Failure cause : %s", e), null);
                            }
                        } else {
                            this.mEventTransport.postEvent(new FatalPlaybackErrorEvent(new TimeSpan(playbackTimeInNanoseconds), e, getPlaybackState().getConsumptionId()));
                        }
                    }
                    httpErrorCode = 0;
                }
                postManifestIfNecessary(e, zIsRetriable, httpErrorCode);
                state = this.mStateFactory.getState(PlaybackState.Error);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.player.PlaybackStateMachine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$player$states$PlaybackState;

        static {
            int[] iArr = new int[PlaybackState.values().length];
            $SwitchMap$com$amazon$avod$playback$player$states$PlaybackState = iArr;
            try {
                iArr[PlaybackState.Paused.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$player$states$PlaybackState[PlaybackState.Playing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void postManifestIfNecessary(@Nonnull MediaException mediaException, boolean z, int i) {
        Preconditions.checkNotNull(mediaException, "exception");
        int severityFor = this.mErrorConfig.getSeverityFor(mediaException, z, i);
        if (!this.mIsUploadManifestsWhenFatalEnabled || severityFor > this.mErrorSeverityLevelToUploadManifest) {
            return;
        }
        DLog.logf("Attempt to post manifest due to exception: %s", mediaException);
        this.mManifestCapturer.uploadFromException(mediaException);
    }

    static class PlaybackStateMachineConfig extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mShouldChangeThreadPriorityBetweenStates;

        /* synthetic */ PlaybackStateMachineConfig(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Nonnull
        public static PlaybackStateMachineConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static final class SingletonHolder {
            public static final PlaybackStateMachineConfig INSTANCE = new PlaybackStateMachineConfig(null);

            private SingletonHolder() {
            }
        }

        private PlaybackStateMachineConfig() {
            this.mShouldChangeThreadPriorityBetweenStates = newBooleanConfigValue("playback_shouldChangeThreadPriorityBetweenStates", true);
        }

        public boolean shouldChangeThreadPriorityBetweenStates() {
            return this.mShouldChangeThreadPriorityBetweenStates.getValue().booleanValue();
        }
    }
}
