package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdClipVideoPlayerUnsetEvent;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.framework.volume.VolumeChangeListener;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.AdCompletedEvent;
import com.amazon.avod.playback.event.playback.AdStartEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekEndEvent;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.ServerInsertedStreamPmetMetrics;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedAdClipState extends AdClipState {

    @Nonnull
    private final AdEventTransport mAdEventTransport;
    private final DiagnosticsOverlayToggler mDiagnosticsToggler;
    private final AtomicBoolean mIsAdSkipInProgress;
    private final AtomicBoolean mIsStartOfAdClip;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final PlaybackMediaEventReporters mPlaybackMediaEventReporters;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    private final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;
    private long mSkipTargetPositionMillis;
    private final TimelineMonitor mTimelineMonitor;
    final VolumeChangeListener mVolumeChangeListener;
    private final VolumeManager mVolumeManager;

    public ServerInsertedAdClipState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor, @Nonnull VolumeManager volumeManager, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        super(adPlaybackStateMachineContext, diagnosticsOverlayToggler, adEventTransport, playbackMediaEventReporters);
        this.mIsAdSkipInProgress = new AtomicBoolean(false);
        this.mIsStartOfAdClip = new AtomicBoolean(false);
        this.mVolumeChangeListener = new VolumeChangeListener() { // from class: com.amazon.avod.media.ads.internal.state.ServerInsertedAdClipState.1
            @Override // com.amazon.avod.media.framework.volume.VolumeChangeListener
            public void onVolumeChange(int i, int i2) {
                AdManagerBasedAdClip currentAdClip = ServerInsertedAdClipState.this.getContext().getCurrentAdClip();
                if (currentAdClip == null) {
                    return;
                }
                if (i == ServerInsertedAdClipState.this.mVolumeManager.getMinimumVolume() && i2 != ServerInsertedAdClipState.this.mVolumeManager.getMinimumVolume()) {
                    currentAdClip.sendUnmuteEvent(ServerInsertedAdClipState.this.getContext().getOfferType());
                }
                if (i == ServerInsertedAdClipState.this.mVolumeManager.getMinimumVolume() || i2 != ServerInsertedAdClipState.this.mVolumeManager.getMinimumVolume()) {
                    return;
                }
                currentAdClip.sendMuteEvent(ServerInsertedAdClipState.this.getContext().getOfferType());
            }
        };
        this.mDiagnosticsToggler = diagnosticsOverlayToggler;
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mPlaybackMediaEventReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters");
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mVolumeManager = (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mServerInsertedManifestTimelineManager = (ServerInsertedManifestTimelineManager) Preconditions.checkNotNull(serverInsertedManifestTimelineManager, "ServerInsertedManifestTimelineManager");
        this.mPmetMetricReporter = PlaybackPmetMetricReporter.getInstance();
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdClipState, com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        if (getContext().getCurrentAdBreak() != null && trigger.getType() == StandardPlayerTriggerType.SEEK) {
            TimeSpan seekTarget = ((SeekTrigger) Preconditions.checkNotNull(CastUtils.castTo(trigger, SeekTrigger.class), "onlySeekTriggers are handled.")).getSeekTarget();
            DLog.logf("ServerInsertedPlaybackSession: Skipping ad clip to position: %s", seekTarget);
            this.mIsAdSkipInProgress.set(true);
            this.mSkipTargetPositionMillis = seekTarget.getTotalMilliseconds();
            getContext().getPrimaryPlayer().seekToManifestPosition(this.mSkipTargetPositionMillis, SeekAction.SeekCause.AD_SKIP);
            getContext().getPrimaryPlayer().start();
            return;
        }
        AdManagerBasedAdClip adManagerBasedAdClip = (AdManagerBasedAdClip) CastUtils.castTo(getContext().getCurrentAdBreak().findNextUnplayedClip(), AdManagerBasedAdClip.class);
        if (adManagerBasedAdClip == null) {
            doTrigger(new SimpleTrigger(AdEnabledPlayerTriggerType.NO_MORE_ADS_SKIP_TRANSITION));
            return;
        }
        if (trigger.getType() == AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED) {
            this.mIsStartOfAdClip.set(true);
            scheduleEndTriggerForSSAI(adManagerBasedAdClip);
            setUpListenersForSSAI(adManagerBasedAdClip);
            getContext().setCurrentAdClip(adManagerBasedAdClip);
            reportAdQoS(adManagerBasedAdClip);
            this.mPlaybackEventTransport.postEvent(new AdStartEvent());
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdClipState, com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (this.mIsAdSkipInProgress.getAndSet(false)) {
            DLog.warnf("ServerInsertedPlaybackSession: Ad clip skip attempt ignored");
            this.mPmetMetricReporter.reportServerInsertedStreamMetrics(ServerInsertedStreamPmetMetrics.AD_CLIP_SKIP_IGNORED);
        }
        AdManagerBasedAdClip currentAdClip = getContext().getCurrentAdClip();
        if (currentAdClip == null) {
            return;
        }
        if (trigger != null && trigger.getType() == StandardPlayerTriggerType.SEEK) {
            currentAdClip.sendSkipClipEvent(getContext().getOfferType());
        }
        this.mPlaybackEventTransport.postEvent(new AdCompletedEvent());
        if (trigger != null && trigger.getType() == AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED) {
            resetListeners();
            getContext().getPrimaryEventReporter().reportMetric(constructMetricsBuilder(currentAdClip).eventSubtype("AdClipEnd"));
            this.mPlaybackMediaEventReporters.getDiagnosticsReporter().handleDiagnosticsEvent(new AloysiusDiagnosticEvent("AdClipEnd", String.format(Locale.US, "AdId: %s", currentAdClip.getAdId()), AloysiusDiagnosticsState.Discrete));
            currentAdClip.setPlayed(true);
            getContext().setCurrentAdClip(null);
            return;
        }
        this.mAdEventTransport.postEvent(new AdClipVideoPlayerUnsetEvent(currentAdClip.getPlayer()));
        if (trigger != null) {
            if (trigger.getType() == StandardPlayerTriggerType.COMPLETED) {
                currentAdClip.sendCompleteEvent(getContext().getOfferType());
            } else if (trigger.getType() == StandardPlayerTriggerType.SHUTDOWN) {
                currentAdClip.sendCloseEvent(getContext().getOfferType());
            } else {
                trigger.getType();
                AdEnabledPlayerTriggerType adEnabledPlayerTriggerType = AdEnabledPlayerTriggerType.WAIT_FOR_PREPARE;
            }
        }
        getContext().getPrimaryEventReporter().reportMetric(constructMetricsBuilder(currentAdClip).eventSubtype("AdClipEnd"));
        if (currentAdClip.getPresentation() != null) {
            this.mDiagnosticsToggler.toggle(currentAdClip.getPresentation().getDiagnosticsController(), getContext().getPrimaryDiagnosticsController());
            currentAdClip.releasePresentation();
        }
        currentAdClip.setPlayed(true);
        getContext().setCurrentAdClip(null);
    }

    @VisibleForTesting
    private MetricsBuilder constructMetricsBuilder(AdManagerBasedAdClip adManagerBasedAdClip) {
        return new MetricsBuilder().adId(adManagerBasedAdClip.getAdId()).eventType("AdEvent");
    }

    @Subscribe
    public void handlePlaybackSeekEndEvent(@Nonnull SeekEndEvent seekEndEvent) {
        if (this.mIsAdSkipInProgress.getAndSet(false)) {
            long totalMilliseconds = seekEndEvent.getTargetSeekTime().getTotalMilliseconds();
            long j = this.mSkipTargetPositionMillis;
            if (totalMilliseconds != j) {
                String str = String.format("ServerInsertedPlaybackSession: Ad clip skip attempted with seek to %s but actual seek was to %s", Long.valueOf(j), Long.valueOf(totalMilliseconds));
                DLog.warnf(str);
                this.mPmetMetricReporter.reportServerInsertedStreamMetrics(ServerInsertedStreamPmetMetrics.AD_CLIP_SKIP_SEEK_INCORRECT);
                getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdClipSkipError", null, str, null);
            }
        }
    }

    @Subscribe
    public void handlePlaybackResumeEvent(@Nonnull ResumeEvent resumeEvent) {
        if (this.mIsStartOfAdClip.getAndSet(false) || getContext().getCurrentAdClip() == null) {
            return;
        }
        getContext().getCurrentAdClip().sendResumeEvent(getContext().getOfferType());
    }

    @Subscribe
    public void handlePlaybackPauseEvent(@Nonnull PauseEvent pauseEvent) {
        if (getContext().getCurrentAdClip() == null) {
            return;
        }
        this.mIsStartOfAdClip.set(false);
        getContext().getCurrentAdClip().sendPauseEvent(getContext().getOfferType());
    }

    private void reportAdQoS(@Nonnull AdManagerBasedAdClip adManagerBasedAdClip) {
        Preconditions.checkNotNull(adManagerBasedAdClip, "nextClip");
        MetricsBuilder metricsBuilderEventSubtype = constructMetricsBuilder(adManagerBasedAdClip).eventSubtype("AdClipBegin");
        PlaybackEventReporter primaryEventReporter = getContext().getPrimaryEventReporter();
        String str = String.format(Locale.US, "AdClipId: %s, AdClipDurationInMS: %d", adManagerBasedAdClip.getAdId(), Long.valueOf(adManagerBasedAdClip.getDuration().getTotalMilliseconds()));
        primaryEventReporter.reportMetric(metricsBuilderEventSubtype);
        primaryEventReporter.reportMetric("AdEvent", "AdClipInformation", null, str, null);
        DLog.logf("AdClipInformation: %s", str);
    }

    private void scheduleEndTriggerForSSAI(@Nonnull AdManagerBasedAdClip adManagerBasedAdClip) {
        Preconditions.checkNotNull(adManagerBasedAdClip, "nextClip");
        this.mTimelineMonitor.scheduleTask(getContext().getPrimaryPlayer(), new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.state.ServerInsertedAdClipState.2
            @Override // java.lang.Runnable
            public void run() {
                ServerInsertedAdClipState.this.doTrigger(new SimpleTrigger(AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED));
            }
        }, TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition() + adManagerBasedAdClip.getDuration().getTotalMilliseconds()), false, true));
    }

    private void setUpListenersForSSAI(AdManagerBasedAdClip adManagerBasedAdClip) {
        Preconditions.checkNotNull(adManagerBasedAdClip, "nextClip");
        this.mPlaybackEventTransport.registerEventBusHandler(this);
        if (this.mVolumeManager.getCurrentVolume() == 0) {
            adManagerBasedAdClip.sendMuteEvent(getContext().getOfferType());
        }
        this.mVolumeManager.startListening(this.mVolumeChangeListener);
    }

    private void resetListeners() {
        this.mVolumeManager.stopListening();
        this.mPlaybackEventTransport.unregisterEventBusHandler(this);
    }
}
