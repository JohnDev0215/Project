package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.drm.DecryptionContext;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.ShutdownAction;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ShutdownState extends PlaybackEngineState {
    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() {
    }

    public ShutdownState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        super(playbackStateDependencies, playbackStateContext, Tickers.androidTicker());
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Shutdown;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) {
        DrmScheme drmScheme;
        Preconditions.checkState(action instanceof ShutdownAction, "Only a ShutdownAction should be used to enter the ShutdownState.");
        ShutdownAction shutdownAction = (ShutdownAction) action;
        DLog.logf("Entering shutdown state current time (ms): %d", Long.valueOf(getPlaybackTimeInNanoseconds() / PlaybackEngineState.NANOSECONDS_IN_A_MILLISECOND));
        if (this.mPlaybackStateContext.getDecryptionContext() != null) {
            drmScheme = this.mPlaybackStateContext.getDecryptionContext().getDrmScheme();
        } else {
            drmScheme = DrmScheme.NONE;
        }
        String str = null;
        ContentSessionContext context = this.mPlaybackStateContext.getContentSession() != null ? this.mPlaybackStateContext.getContentSession().getContext() : null;
        if (context != null) {
            str = (context.getManifest().isDynamic() ? "isDynamic" : "isStatic") + "-" + String.valueOf(context.getManifest().isPatternTemplateManifest()) + "-" + (context.getSpecification().isLiveStream() ? "isLive" : "isFeature") + "-" + (context.isManifestRefreshEnabledForLive() ? "refreshableManifest" : "fixedManifest");
        }
        TimeSpan timeSpan = new TimeSpan(getPlaybackTimeInNanoseconds());
        postEvent(new PlaybackStopEvent(timeSpan, getRendererSchemeString(), drmScheme.toString(), null, null, str));
        getPlaybackEventReporter().handlePlaybackStopEvent(timeSpan);
        reportRendererDebugInfo();
        PlaybackSessionProtocol playbackSessionProtocol = getPlaybackSessionProtocol();
        if (playbackSessionProtocol != null) {
            playbackSessionProtocol.stop();
            playbackSessionProtocol.dispose();
        }
        MediaProfiler profiler = getProfiler();
        try {
            profiler.start("VideoRenderer.dispose");
            getRenderer().dispose(shutdownAction.shouldRetainReusableComponents());
            profiler.stop("VideoRenderer.dispose");
        } finally {
            DecryptionContext decryptionContext = this.mPlaybackStateContext.getDecryptionContext();
            if (decryptionContext != null) {
                profiler.start("DecryptionContext.shutdown");
                decryptionContext.shutdown();
                profiler.stop("DecryptionContext.shutdown");
            }
        }
    }

    @Nonnull
    private String getRendererSchemeString() {
        RendererScheme rendererScheme = this.mPlaybackStateDependencies.getRendererScheme();
        StringBuilder sb = new StringBuilder();
        sb.append(rendererScheme.getSchemeType().getShorthand());
        AvailabilityStatus availabilityStatus = rendererScheme.getAvailabilityStatus();
        if (availabilityStatus.getSupportedProfile() != null && availabilityStatus.isAutoResolved()) {
            sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            sb.append(rendererScheme.getAvailabilityStatus().getSupportedProfile());
        }
        return sb.toString();
    }

    private void reportRendererDebugInfo() {
        String rendererDebugInfo = getRenderer().getRendererDebugInfo();
        if (rendererDebugInfo.length() > 0) {
            this.mPlaybackStateContext.getContentSession().getContext().getPlaybackEventReporter().reportMetric(QoSMetric.Information.toString(), "RendererDebugInformation", TimeSpan.ZERO, rendererDebugInfo, null);
            this.mPlaybackStateDependencies.getEventTransport().postEvent(new AloysiusDiagnosticEvent("RendererDebugInformation", rendererDebugInfo, AloysiusDiagnosticsState.Discrete));
        }
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
        ContentSession contentSession;
        RendererSchemeController rendererSchemeController = this.mPlaybackStateDependencies.getRendererSchemeController();
        if (this.mPlaybackStateContext.getFatalException() == null && this.mPlaybackStateContext.getPlaybackStarted() && rendererSchemeController.notifyAutoEvalCanaryPlaybackSuccessEvent() && (contentSession = this.mPlaybackStateContext.getContentSession()) != null) {
            contentSession.getContext().getPlaybackEventReporter().reportMetric(QoSMetric.Information.toString(), "PlayerAutoEvalCanaryPassed", TimeSpan.ZERO, null, null);
        }
    }
}
