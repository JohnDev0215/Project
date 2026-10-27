package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusContentReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.event.playback.AdPlaybackCompletedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackStoppedEvent;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdBreakState extends AdEnabledPlaybackState {
    private static final Function<AdClip, String> mToAdIdIterable = new Function<AdClip, String>() { // from class: com.amazon.avod.media.ads.internal.state.AdBreakState.1
        @Override // com.google.common.base.Function
        public String apply(AdClip adClip) {
            if (adClip == null) {
                throw new IllegalStateException("adClipId is NULL");
            }
            return adClip.getAdId();
        }
    };
    protected final AdsConfig mAdsConfig;
    protected final AloysiusContentReporter mAloysiusContentReporter;
    protected final AloysiusPlaybackReporter mAloysiusPlaybackReporter;
    protected final PlaybackEventTransport mPlaybackEventTransport;
    protected final boolean mShouldUnifyMediaEventReporters;
    protected final TimelineMonitor mTimelineMonitor;

    @VisibleForTesting
    AdBreakState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdsConfig adsConfig, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor, boolean z) {
        super(AdEnabledPlayerStateType.AD_BREAK, adPlaybackStateMachineContext);
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mAloysiusPlaybackReporter = ((PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters")).getPlaybackReporter();
        this.mAloysiusContentReporter = ((PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters")).getContentReporter();
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mShouldUnifyMediaEventReporters = z;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (getContext().getCurrentAdBreak() != null) {
            getContext().getCurrentAdBreak().sendAdBreakEnd();
            Iterator<AdClip> it = getContext().getCurrentAdBreak().getClips().iterator();
            while (it.hasNext()) {
                ((AdManagerBasedAdClip) CastUtils.castTo(it.next(), AdManagerBasedAdClip.class)).setPlayed(true);
            }
            getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdBreakEnd", null, constructAdString(getContext().getCurrentAdBreak()), null);
            getContext().setCurrentAdBreak(null);
        }
        this.mPlaybackEventTransport.postEvent(new AdPlaybackStoppedEvent());
        if (this.mAdsConfig.shouldSuppressAdClipStartStopReporting()) {
            AdPlaybackCompletedEvent adPlaybackCompletedEvent = new AdPlaybackCompletedEvent();
            this.mPlaybackEventTransport.postEvent(adPlaybackCompletedEvent);
            this.mAloysiusPlaybackReporter.handlePlaybackCompletedEvent(adPlaybackCompletedEvent);
        }
        getContext().getPrimaryPlayer().getPlaybackExperienceController().setVideoPlayerInBackground(false);
        if (this.mShouldUnifyMediaEventReporters) {
            return;
        }
        getContext().getStateMachine().setCyclicReportingPaused(true);
    }

    @Nullable
    @VisibleForTesting
    String constructAdString(@Nullable AdBreak adBreak) {
        if (adBreak == null || adBreak.getId() == null) {
            return null;
        }
        String str = String.format(Locale.US, "AdBreakId: %s, AdBreakDurationInMS: %d, AdClipIds: %s", adBreak.getId(), Long.valueOf(adBreak.getDuration().getTotalMilliseconds()), Joiner.on("|").join(Lists.transform(adBreak.getClips(), mToAdIdIterable)));
        DLog.logf("AdBreakInformation: %s", str);
        return str;
    }
}
