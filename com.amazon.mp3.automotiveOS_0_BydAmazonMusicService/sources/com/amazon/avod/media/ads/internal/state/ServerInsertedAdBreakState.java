package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.PlaybackContentEvent;
import com.amazon.avod.playback.event.playback.AdChapteringEvent;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedAdBreakState extends AdBreakState {
    private boolean mIsChapteredBreak;
    private long mPrimaryResumePointInMillisAfterChapter;
    private final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;

    public ServerInsertedAdBreakState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        this(adPlaybackStateMachineContext, playbackEventTransport, AdsConfig.getInstance(), playbackMediaEventReporters, timelineMonitor, AloysiusConfig.getInstance().shouldUnifyMediaEventReporters(), serverInsertedManifestTimelineManager);
    }

    @VisibleForTesting
    ServerInsertedAdBreakState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdsConfig adsConfig, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull TimelineMonitor timelineMonitor, boolean z, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        super(adPlaybackStateMachineContext, playbackEventTransport, adsConfig, playbackMediaEventReporters, timelineMonitor, z);
        this.mServerInsertedManifestTimelineManager = serverInsertedManifestTimelineManager;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        AdBreakTrigger adBreakTrigger = (AdBreakTrigger) CastUtils.castTo(trigger, AdBreakTrigger.class);
        PlaybackEventReporter primaryEventReporter = getContext().getPrimaryEventReporter();
        VideoPlayer primaryPlayer = getContext().getPrimaryPlayer();
        Preconditions.checkArgument(adBreakTrigger != null, "only AdBreakTrigger triggers are handled.");
        getContext().getAdBreakBufferContext().reset();
        String contentType = null;
        for (AdClip adClip : adBreakTrigger.getBreak().getClips()) {
            adClip.scheduleTimelineEventsWithPrimaryPlayer(this.mTimelineMonitor, primaryPlayer, primaryEventReporter, getContext().getOfferType());
            contentType = adClip.getContentType();
        }
        this.mAloysiusContentReporter.handlePlaybackContentEvent(new PlaybackContentEvent(adBreakTrigger.getBreak().getId(), getContext().getPrimaryPlayer(), contentType));
        getContext().setCurrentAdBreak(adBreakTrigger.getBreak());
        adBreakTrigger.getBreak().sendAdBreakStart();
        primaryEventReporter.reportMetric("AdEvent", "AdBreakBegin", null, constructAdString(adBreakTrigger.getBreak()), null);
        if (adBreakTrigger.getSeekTarget() != null) {
            String id = adBreakTrigger.getBreak().getId();
            long totalMilliseconds = adBreakTrigger.getBreak().getStartTime().getTotalMilliseconds();
            long totalMilliseconds2 = adBreakTrigger.getSeekStartPosition().getTotalMilliseconds();
            this.mPrimaryResumePointInMillisAfterChapter = adBreakTrigger.getSeekTarget().getTotalMilliseconds();
            enableChapter();
            DLog.logf("ServerInsertedPlaybackSession: SIABS.entry seeking to absolutePosition: %s", TimeSpan.fromMilliseconds(totalMilliseconds));
            primaryPlayer.seekTo(totalMilliseconds);
            sendChapteringReporting(primaryEventReporter, id, totalMilliseconds, totalMilliseconds2);
        }
        doTrigger(new SimpleTrigger(AdEnabledPlayerTriggerType.NEXT_AD_CLIP_SERVER_INSERTED));
    }

    private void sendChapteringReporting(PlaybackEventReporter playbackEventReporter, String str, long j, long j2) {
        String strConstructChapteringJsonString = constructChapteringJsonString(str, j, this.mPrimaryResumePointInMillisAfterChapter, j2);
        playbackEventReporter.reportMetric("AdEvent", "AdChapteringOccurred", null, strConstructChapteringJsonString, null);
        sendAloysiusChapteringReporting(strConstructChapteringJsonString);
    }

    private void sendAloysiusChapteringReporting(String str) {
        this.mPlaybackEventTransport.postEvent(new AdChapteringEvent(str));
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdBreakState, com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        AdBreak currentAdBreak = getContext().getCurrentAdBreak();
        if (currentAdBreak != null) {
            currentAdBreak.sendAdBreakEnd();
            Iterator<AdClip> it = currentAdBreak.getClips().iterator();
            while (it.hasNext()) {
                ((AdManagerBasedAdClip) CastUtils.castTo(it.next(), AdManagerBasedAdClip.class)).setPlayed(true);
            }
            getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdBreakEnd", null, constructAdString(currentAdBreak), null);
            getContext().setCurrentAdBreak(null);
            if (!currentAdBreak.isAdBreakSeekOverScheduled()) {
                currentAdBreak.scheduleAdBreakSeekOver(currentAdBreak.getStartTime(), getContext().getPrimaryPlayer(), this.mTimelineMonitor, getContext().getPrimaryEventReporter(), PlaybackPmetMetricReporter.getInstance());
            }
        }
        if (isChaptered()) {
            resetChapter();
            DLog.logf("ServerInsertedPlaybackSession: SIABS.exit seekTo: %s", TimeSpan.fromMilliseconds(this.mPrimaryResumePointInMillisAfterChapter));
            getContext().getPrimaryPlayer().seekToManifestPosition(this.mPrimaryResumePointInMillisAfterChapter, SeekAction.SeekCause.AD_CHAPTERING);
        }
    }

    private void resetChapter() {
        this.mIsChapteredBreak = false;
    }

    private void enableChapter() {
        this.mIsChapteredBreak = true;
    }

    private boolean isChaptered() {
        return this.mIsChapteredBreak;
    }

    @Nullable
    private String constructChapteringJsonString(String str, long j, long j2, long j3) {
        if (str == null) {
            return null;
        }
        String str2 = String.format(Locale.US, "AdBreakId: %s, AdBreakStartInMillseconds: %d, SeekTargetInMilliseconds: %d, SeekStartPositionInMilliseconds: %d", str, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3));
        DLog.logf("ChapteringInformation: %s", str2);
        return str2;
    }
}
