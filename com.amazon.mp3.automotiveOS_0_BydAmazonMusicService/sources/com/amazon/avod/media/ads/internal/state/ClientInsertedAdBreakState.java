package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.event.playback.AdPlaybackPlayingEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackStartedEvent;
import com.amazon.avod.util.CastUtils;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ClientInsertedAdBreakState extends AdBreakState {
    public ClientInsertedAdBreakState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor) {
        this(adPlaybackStateMachineContext, playbackEventTransport, AdsConfig.getInstance(), playbackMediaEventReporters, timelineMonitor, AloysiusConfig.getInstance().shouldUnifyMediaEventReporters());
    }

    @VisibleForTesting
    ClientInsertedAdBreakState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdsConfig adsConfig, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor, boolean z) {
        super(adPlaybackStateMachineContext, playbackEventTransport, adsConfig, playbackMediaEventReporters, timelineMonitor, z);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        AdBreakTrigger adBreakTrigger = (AdBreakTrigger) CastUtils.castTo(trigger, AdBreakTrigger.class);
        Preconditions.checkArgument(adBreakTrigger != null, "only AdBreakTrigger triggers are handled.");
        getContext().getAdBreakBufferContext().reset();
        getContext().getPrimaryPlayer().getPlaybackExperienceController().setVideoPlayerInBackground(true);
        if (!this.mShouldUnifyMediaEventReporters) {
            getContext().getStateMachine().setCyclicReportingPaused(false);
        }
        AdBreak adBreak = adBreakTrigger.getBreak();
        if (!adBreak.getClips().isEmpty()) {
            getContext().getPrimaryPlayer().pause();
            getContext().setCurrentAdBreak(adBreak);
            adBreak.sendAdBreakStart();
            getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdBreakBegin", null, constructAdString(adBreak), null);
            doTrigger(AdEnabledPlayerTriggers.PLAY_AD);
            AdPlaybackStartedEvent adPlaybackStartedEvent = new AdPlaybackStartedEvent();
            this.mPlaybackEventTransport.postEvent(adPlaybackStartedEvent);
            this.mAloysiusPlaybackReporter.handleAdPlaybackStartedEvent(adPlaybackStartedEvent);
            if (this.mAdsConfig.shouldSuppressAdClipStartStopReporting()) {
                AdPlaybackPlayingEvent adPlaybackPlayingEvent = new AdPlaybackPlayingEvent();
                this.mPlaybackEventTransport.postEvent(adPlaybackPlayingEvent);
                this.mAloysiusPlaybackReporter.handlePlaybackPlayingEvent(adPlaybackPlayingEvent);
                return;
            }
            return;
        }
        doTrigger(AdEnabledPlayerTriggers.NO_MORE_ADS);
    }
}
