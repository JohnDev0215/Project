package com.amazon.avod.playback.smoothstream;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.TimelineManager;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.ServerInsertedStreamPmetMetrics;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedManifestTimelineManager implements TimelineManager {
    private AdPlan mAdPlan;
    private TimeSpan mAdPlanDuration;
    private final InitializationLatch mInitializationLatch;
    private AtomicBoolean mInitializationLatchStarted;
    private TimeSpan mManifestDuration;
    private TimeSpan mPlaybackDuration;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private AdPlaybackStateMachineContext mPlaybackStateMachineContext;
    private final VideoPresentationEventReporter mPresentationEventReporter;
    private List<ServerInsertedTimelineVector> mServerInsertedTimelineVectors;
    private TimeSpan mSessionDuration;
    private final Long mTimeoutDuration;

    public ServerInsertedManifestTimelineManager(@Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nullable TimeSpan timeSpan) {
        this(playbackSessionContext, videoPresentationEventReporter, EmptyAdPlan.INSTANCE, PlaybackPmetMetricReporter.getInstance(), timeSpan, AdsConfig.getInstance().getTimelineManagerInitializationTimeoutInMilliseconds());
    }

    @VisibleForTesting
    ServerInsertedManifestTimelineManager(@Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull AdPlan adPlan, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nullable TimeSpan timeSpan, @Nonnegative Long l) {
        this.mAdPlan = EmptyAdPlan.INSTANCE;
        TimeSpan timeSpan2 = TimeSpan.ZERO;
        this.mAdPlanDuration = timeSpan2;
        this.mSessionDuration = timeSpan2;
        this.mManifestDuration = timeSpan2;
        this.mServerInsertedTimelineVectors = new ArrayList();
        this.mInitializationLatch = new InitializationLatch(this);
        this.mInitializationLatchStarted = new AtomicBoolean(false);
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mPresentationEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "VideoPresentationEventReporter");
        this.mAdPlan = (AdPlan) Preconditions.checkNotNull(adPlan, "adPlan");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "pmetMetricReporter");
        this.mPlaybackDuration = timeSpan;
        this.mTimeoutDuration = (Long) Preconditions.checkNotNull(l, "timeline manager timeout duration");
    }

    @Override // com.amazon.avod.playback.TimelineManager
    @Nonnull
    public TimeSpan getPlaybackDuration() {
        return this.mSessionDuration;
    }

    @Nonnull
    public TimeSpan getPlaybackAbsolutePositionFromRelativePositionForMain(@Nonnegative long j) {
        Preconditions.checkArgument(this.mInitializationLatch.isInitialized(), "notifyRetrievalComplete() needs to be called before you use this method because the ad plan has not been retrieved.");
        if (!this.mAdPlanDuration.isZero()) {
            try {
                return new TimeSpan(ServerInsertedTimelineManagerUtil.getPlaybackAbsolutePositionForRelativePosition(this.mServerInsertedTimelineVectors, j, ServerInsertedTimelineVectorType.MAIN));
            } catch (IllegalArgumentException e) {
                reportMetric("ServerInsertedTimelineError", String.format("ServerInsertedPlaybackSession: Encountered issue while converting absolute position to relative position for main vector.  %s", e.getMessage()));
            }
        }
        return new TimeSpan(j);
    }

    @Override // com.amazon.avod.playback.TimelineManager
    @Nonnull
    public TimeSpan getNormalisedRelativePosition(long j) {
        Preconditions.checkArgument(this.mInitializationLatch.isInitialized(), "notifyRetrievalComplete() needs to be called before you use this method because the ad plan has not been retrieved.");
        if (!this.mAdPlanDuration.isZero()) {
            try {
                return new TimeSpan(ServerInsertedTimelineManagerUtil.getNormalisedRelativePosition(this.mServerInsertedTimelineVectors, j));
            } catch (IllegalArgumentException e) {
                reportMetric("ServerInsertedTimelineError", String.format(" Encountered issue while converting relative playback position to absolute playback.  %s", e.getMessage()));
            }
        }
        return new TimeSpan(j);
    }

    public boolean areInSameChapter(long j, long j2) {
        Preconditions.checkArgument(this.mInitializationLatch.isInitialized(), "notifyRetrievalComplete() needs to be called before you use this method because the ad plan has not been retrieved.");
        if (this.mAdPlanDuration.isZero()) {
            return false;
        }
        return ServerInsertedTimelineManagerUtil.areInSameChapter(this.mServerInsertedTimelineVectors, getPlaybackAbsolutePositionFromRelativePositionForMain(j).getTotalNanoSeconds(), getPlaybackAbsolutePositionFromRelativePositionForMain(j2).getTotalNanoSeconds());
    }

    @VisibleForTesting
    void createTimelineVectorsFromAdPlan() {
        if (this.mAdPlan.getBreaks().size() == 0) {
            reportMetric("AdEmptyAdPlan", "Encountered adPlan with zero adBreaks.");
            reportPmetMetric(ServerInsertedStreamPmetMetrics.EMPTY_AD_PLAN);
            return;
        }
        this.mServerInsertedTimelineVectors = ServerInsertedTimelineManagerUtil.createTimelineVectors(this.mAdPlan, this.mManifestDuration.getTotalNanoSeconds());
        StringBuilder sb = new StringBuilder();
        for (ServerInsertedTimelineVector serverInsertedTimelineVector : this.mServerInsertedTimelineVectors) {
            DLog.logf("ServerInsertedPlaybackSession: Item: %s", serverInsertedTimelineVector.toString());
            sb.append(String.format("%s, %n", serverInsertedTimelineVector.toString()));
        }
        reportMetric("ServerInsertedTimelineCreated", sb.toString());
    }

    @Override // com.amazon.avod.playback.TimelineManager
    public void notifyAdPlanRetrievalComplete(@Nonnull AdPlan adPlan) {
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        if (contentSession == null || contentSession.getContext() == null) {
            DLog.logf("ServerInsertedPlaybackSession: Early return,  encountered empty(null) contentSession.");
        } else {
            init(adPlan);
            this.mPlaybackStateMachineContext.updateTimeline();
        }
    }

    @Override // com.amazon.avod.playback.TimelineManager
    public void notifyAdPlanRetrievalComplete(@Nullable AdPlan adPlan, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        if (!this.mInitializationLatchStarted.getAndSet(true) && !this.mInitializationLatch.isInitialized()) {
            this.mPlaybackStateMachineContext = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "playbackStateMachineContext");
            this.mInitializationLatch.start(this.mTimeoutDuration.longValue(), TimeUnit.MILLISECONDS);
        }
        if (adPlan != null && adPlan != EmptyAdPlan.INSTANCE) {
            init(adPlan);
        } else {
            reportMetric("AdEmptyAdPlan", "ServerInsertedPlaybackSession: Encountered empty(null) adplan. aborting Timeline creation.");
            reportPmetMetric(ServerInsertedStreamPmetMetrics.EMPTY_AD_PLAN);
        }
        if (!this.mInitializationLatch.isInitialized()) {
            this.mInitializationLatch.complete();
        }
        setPlaybackStartTime(this.mPlaybackStateMachineContext);
    }

    private void init(@Nonnull AdPlan adPlan) {
        Iterator<AdBreak> it = adPlan.getBreaks().iterator();
        while (it.hasNext()) {
            DLog.devf("ServerInsertedPlaybackSession: ad break id: %s", it.next().getId());
        }
        this.mAdPlan = adPlan;
        this.mAdPlanDuration = adPlan.getDuration();
        TimeSpan manifestDuration = getManifestDuration(adPlan);
        this.mManifestDuration = manifestDuration;
        this.mSessionDuration = TimeSpan.difference(manifestDuration, this.mAdPlanDuration);
        createTimelineVectorsFromAdPlan();
        reportMetric("ServerInsertedTimelineInformation", String.format("ManifestDuration: %s, SessionDuration: %s, AdPlanDuration: %s", this.mManifestDuration, this.mSessionDuration, this.mAdPlanDuration));
    }

    private TimeSpan getManifestDuration(@Nonnull AdPlan adPlan) {
        TimeSpan timeSpanAdd;
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        TimeSpan timeSpan = this.mPlaybackDuration;
        if (timeSpan == null || timeSpan.isZero()) {
            timeSpanAdd = TimeSpan.MAX_VALUE;
        } else {
            timeSpanAdd = TimeSpan.add(this.mPlaybackDuration, adPlan.getDuration());
        }
        if (contentSession == null) {
            return timeSpanAdd;
        }
        try {
            return contentSession.getContext() != null ? contentSession.getContext().getDuration() : timeSpanAdd;
        } catch (IllegalStateException e) {
            DLog.logf("ServerInsertedPlaybackSession: Ran into a ISE while reading manifest duration from content session: %s", e.getMessage());
            return timeSpanAdd;
        }
    }

    private void setPlaybackStartTime(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        Preconditions.checkArgument(this.mInitializationLatch.isInitialized(), "notifyRetrievalComplete() needs to be called before you use this method because the ad plan has not been retrieved.");
        TimeSpan startTime = this.mPlaybackSessionContext.getVideoSpec().getStartTime();
        if (startTime.equals(TimeSpan.ZERO)) {
            return;
        }
        TimeSpan playbackAbsolutePositionFromRelativePositionForMain = getPlaybackAbsolutePositionFromRelativePositionForMain(startTime.getTotalNanoSeconds());
        DLog.logf("ServerInsertedPlaybackSession: VideoSpec start time: %s, VideoSpec absolute start time: %s", startTime, playbackAbsolutePositionFromRelativePositionForMain);
        updateVideoSpecificationStartTime(playbackAbsolutePositionFromRelativePositionForMain, adPlaybackStateMachineContext);
    }

    private void updateVideoSpecificationStartTime(@Nonnull TimeSpan timeSpan, AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        Preconditions.checkArgument(this.mInitializationLatch.isInitialized(), "notifyRetrievalComplete() needs to be called before you use this method because the ad plan has not been retrieved.");
        Preconditions.checkNotNull(timeSpan, "playbackStartTime");
        VideoSpecification videoSpecificationBuild = VideoSpecification.newBuilder(this.mPlaybackSessionContext.getVideoSpec()).setStartTime(timeSpan).build();
        this.mPlaybackSessionContext.setVideoSpec(videoSpecificationBuild);
        adPlaybackStateMachineContext.setVideoSpecification(videoSpecificationBuild);
    }

    private void reportMetric(@Nonnull String str, @Nonnull String str2) {
        if (this.mPresentationEventReporter.getPlaybackReporter() != null) {
            this.mPresentationEventReporter.getPlaybackReporter().reportMetric("AdEvent", str, null, str2, null);
            DLog.logf("ServerInsertedPlaybackSession: %s", str2);
        }
    }

    private void reportPmetMetric(@Nonnull ServerInsertedStreamPmetMetrics serverInsertedStreamPmetMetrics) {
        this.mPlaybackPmetMetricReporter.reportServerInsertedStreamMetrics(serverInsertedStreamPmetMetrics);
    }
}
