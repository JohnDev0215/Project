package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdClipVideoPlayerSetEvent;
import com.amazon.avod.media.ads.AdClipVideoPlayerUnsetEvent;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.StandardPlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdClipState extends AdEnabledPlaybackState {

    @Nonnull
    private final AdEventTransport mAdEventTransport;
    private final DiagnosticsOverlayToggler mDiagnosticsToggler;
    private final PlaybackMediaEventReporters mPlaybackMediaEventReporters;

    public AdClipState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters) {
        super(AdEnabledPlayerStateType.AD_CLIP, adPlaybackStateMachineContext);
        this.mDiagnosticsToggler = diagnosticsOverlayToggler;
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mPlaybackMediaEventReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        boolean zCreatePresentation;
        AdManagerBasedAdClip adManagerBasedAdClip = (AdManagerBasedAdClip) CastUtils.castTo(getContext().getCurrentAdBreak().findNextUnplayedClip(), AdManagerBasedAdClip.class);
        if (adManagerBasedAdClip == null) {
            doTrigger(new SimpleTrigger(AdEnabledPlayerTriggerType.NO_MORE_ADS));
            return;
        }
        try {
            zCreatePresentation = adManagerBasedAdClip.createPresentation(getContext().getPrimaryEventReporter().getUserWatchSessionId(), getContext().getPlaybackSessionContext().getContentSession().getConsumptionId(), this.mPlaybackMediaEventReporters);
        } catch (Exception e) {
            DLog.exceptionf(e, "Error creating presentation for clip.", new Object[0]);
            zCreatePresentation = false;
        }
        if (zCreatePresentation) {
            getContext().setCurrentAdClip(adManagerBasedAdClip);
            MetricsBuilder metricsBuilderEventSubtype = constructMetricsBuilder(adManagerBasedAdClip).eventSubtype("AdClipBegin");
            PlaybackEventReporter primaryEventReporter = getContext().getPrimaryEventReporter();
            String str = String.format(Locale.US, "AdClipId: %s, AdClipDurationInMS: %d", adManagerBasedAdClip.getAdId(), Long.valueOf(adManagerBasedAdClip.getDuration().getTotalMilliseconds()));
            primaryEventReporter.reportMetric(metricsBuilderEventSubtype);
            primaryEventReporter.reportMetric("AdEvent", "AdClipInformation", null, str, null);
            DLog.logf("AdClipInformation: %s", str);
            this.mDiagnosticsToggler.toggle(getContext().getPrimaryDiagnosticsController(), adManagerBasedAdClip.getPresentation().getDiagnosticsController());
            this.mAdEventTransport.postEvent(new AdClipVideoPlayerSetEvent(adManagerBasedAdClip.getPlayer()));
            doTrigger(new PrepareClipTrigger(adManagerBasedAdClip));
            return;
        }
        doTrigger(AdEnabledPlayerTriggers.NO_MORE_ADS);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        AdManagerBasedAdClip currentAdClip = getContext().getCurrentAdClip();
        if (currentAdClip != null) {
            this.mDiagnosticsToggler.toggle(currentAdClip.getPresentation().getDiagnosticsController(), getContext().getPrimaryDiagnosticsController());
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
            this.mPlaybackMediaEventReporters.getDiagnosticsReporter().handleDiagnosticsEvent(new AloysiusDiagnosticEvent("AdClipEnd", String.format(Locale.US, "AdId: %s", currentAdClip.getAdId()), AloysiusDiagnosticsState.Discrete));
            currentAdClip.releasePresentation();
            currentAdClip.setPlayed(true);
            getContext().setCurrentAdClip(null);
        }
    }

    @VisibleForTesting
    private MetricsBuilder constructMetricsBuilder(AdManagerBasedAdClip adManagerBasedAdClip) {
        return new MetricsBuilder().adId(adManagerBasedAdClip.getAdId()).eventType("AdEvent");
    }
}
