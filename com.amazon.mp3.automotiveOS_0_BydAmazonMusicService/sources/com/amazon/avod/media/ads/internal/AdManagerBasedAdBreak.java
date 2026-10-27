package com.amazon.avod.media.ads.internal;

import com.amazon.avod.ads.api.AdBreakErrorCode;
import com.amazon.avod.ads.api.AdBreakInfo;
import com.amazon.avod.ads.api.AdInfo;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.ads.api.AdParsingException;
import com.amazon.avod.ads.api.AdResolutionException;
import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.ads.api.AdTypeException;
import com.amazon.avod.ads.api.Creative;
import com.amazon.avod.ads.api.CreativeLinear;
import com.amazon.avod.ads.api.CreativeType;
import com.amazon.avod.ads.api.Duration;
import com.amazon.avod.ads.parser.vmap.VmapTrackingEventType;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.ServerInsertedStreamPmetMetrics;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdManagerBasedAdBreak implements AdBreak {
    private TimeSpan mAbsolutePosition;
    private AdBreakInfo mAdBreakInfo;
    private boolean mAdBreakScheduled;
    private boolean mAdBreakSeekOverScheduled;
    private final AdErrorReporter mAdErrorSender;
    private final AdManagerBasedFactory mAdManagerBasedFactory;
    private AdPositionType mAdPositionType;
    private TimeSpan mAdTriggeredTimestamp;
    private final List<AdClip> mClips;
    private final AdsConfig mConfig;
    private TimeSpan mDuration;
    private final ExecutorService mExecutor;
    private final AdClipMediaFileChooser mMediaFileChooser;

    @Nonnull
    private QOSCommunicationService mQOSCommunicationService;
    private TimeSpan mRelativePosition;
    private final long mSeekOverAdBreakEndOffsetInMs;
    private final long mSeekOverAdBreakStartOffsetInMs;

    public AdManagerBasedAdBreak(AdManagerBasedFactory adManagerBasedFactory, ExecutorService executorService, AdClipMediaFileChooser adClipMediaFileChooser, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this(adManagerBasedFactory, AdsConfig.getInstance(), executorService, new AdErrorReporter(executorService), adClipMediaFileChooser, qOSCommunicationService, AdsConfig.getInstance().getSeekOverAdBreakStartOffsetInMs(), AdsConfig.getInstance().getSeekOverAdBreakEndOffsetInMs());
    }

    AdManagerBasedAdBreak(AdManagerBasedFactory adManagerBasedFactory, AdsConfig adsConfig, ExecutorService executorService, AdErrorReporter adErrorReporter, AdClipMediaFileChooser adClipMediaFileChooser, @Nonnull QOSCommunicationService qOSCommunicationService, long j, long j2) {
        this.mClips = Lists.newLinkedList();
        this.mDuration = TimeSpan.ZERO;
        this.mAdManagerBasedFactory = (AdManagerBasedFactory) Preconditions.checkNotNull(adManagerBasedFactory);
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService);
        this.mAdErrorSender = (AdErrorReporter) Preconditions.checkNotNull(adErrorReporter);
        this.mConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig);
        this.mMediaFileChooser = (AdClipMediaFileChooser) Preconditions.checkNotNull(adClipMediaFileChooser);
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "QOSCommunicationService");
        this.mSeekOverAdBreakStartOffsetInMs = j;
        this.mSeekOverAdBreakEndOffsetInMs = j2;
    }

    public void initialize(AdBreakInfo adBreakInfo, TimeSpan timeSpan, TimeSpan timeSpan2) throws MediaException {
        AdEventTransport eventTransport;
        RetriablePlaybackErrorEvent retriablePlaybackErrorEvent;
        this.mAdBreakInfo = (AdBreakInfo) Preconditions.checkNotNull(adBreakInfo);
        Duration duration = adBreakInfo.getOffset().getDuration(timeSpan.getTotalMilliseconds());
        if (this.mConfig.isSSAIEnabled()) {
            TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(duration.getTotalMilliseconds());
            this.mAbsolutePosition = timeSpanFromMilliseconds;
            this.mRelativePosition = TimeSpan.difference(timeSpanFromMilliseconds, timeSpan2);
        } else {
            TimeSpan timeSpanFromMilliseconds2 = TimeSpan.fromMilliseconds(duration.getTotalMilliseconds());
            this.mRelativePosition = timeSpanFromMilliseconds2;
            this.mAbsolutePosition = timeSpanFromMilliseconds2;
        }
        this.mAdPositionType = calculateAdPositionType(timeSpan);
        try {
            this.mAdBreakInfo.retrieveAdInfo();
            for (AdInfo adInfo : getOrderedAdInfos(this.mAdBreakInfo)) {
                try {
                    try {
                        try {
                            if (adInfo.hasUnresolvedEntities()) {
                                adInfo.resolveExternalEntities();
                            }
                            initializeClips(adInfo);
                            break;
                        } catch (AdTypeException e) {
                            e = e;
                            this.mAdErrorSender.sendError(adInfo, AdInfoErrorCode.AD_TYPE_UNSUPPORTED);
                            eventTransport = this.mQOSCommunicationService.getEventTransport();
                            retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new AdException(e));
                            eventTransport.postEvent(retriablePlaybackErrorEvent);
                            DLog.exceptionf(e);
                            this.mClips.clear();
                        } catch (com.amazon.avod.ads.api.AdException e2) {
                            e = e2;
                            this.mAdErrorSender.sendError(adInfo, AdInfoErrorCode.UNDEFINED_ERROR);
                            eventTransport = this.mQOSCommunicationService.getEventTransport();
                            retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new AdException(e));
                            eventTransport.postEvent(retriablePlaybackErrorEvent);
                            DLog.exceptionf(e);
                            this.mClips.clear();
                        }
                    } catch (AdNetworkException e3) {
                        e = e3;
                        this.mAdErrorSender.sendError(adInfo, AdInfoErrorCode.WRAPPER_HTTP_TIMEOUT);
                        eventTransport = this.mQOSCommunicationService.getEventTransport();
                        retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new AdException(e));
                        eventTransport.postEvent(retriablePlaybackErrorEvent);
                        DLog.exceptionf(e);
                        this.mClips.clear();
                    } catch (AdParsingException e4) {
                        e = e4;
                        this.mAdErrorSender.sendError(adInfo, AdInfoErrorCode.GENERAL_WRAPPER_ERROR);
                        eventTransport = this.mQOSCommunicationService.getEventTransport();
                        retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new AdException(e));
                        eventTransport.postEvent(retriablePlaybackErrorEvent);
                        DLog.exceptionf(e);
                        this.mClips.clear();
                    }
                } catch (AdResolutionException e5) {
                    e = e5;
                    this.mAdErrorSender.sendError(adInfo, AdInfoErrorCode.NO_ADS_AFTER_FOLLOWING_WRAPPER);
                    eventTransport = this.mQOSCommunicationService.getEventTransport();
                    retriablePlaybackErrorEvent = new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new AdException(e));
                    eventTransport.postEvent(retriablePlaybackErrorEvent);
                    DLog.exceptionf(e);
                    this.mClips.clear();
                }
            }
            Iterator<AdClip> it = this.mClips.iterator();
            while (it.hasNext()) {
                this.mDuration = TimeSpan.add(this.mDuration, it.next().getDuration());
            }
        } catch (AdNetworkException e6) {
            sendError(AdBreakErrorCode.DOCUMENT_RETRIVAL_ERROR, null);
            AdException adException = new AdException(e6);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, adException));
            throw adException;
        } catch (AdParsingException e7) {
            sendError(AdBreakErrorCode.DOCUMENT_EXTRACTION_ERROR, null);
            AdException adException2 = new AdException(e7);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, adException2));
            throw adException2;
        }
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public AdPositionType getAdPositionType() {
        return this.mAdPositionType;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public TimeSpan getDuration() {
        return this.mDuration;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public TimeSpan getStartTime() {
        return this.mAbsolutePosition;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    @Nonnull
    public TimeSpan getRelativeStartTime() {
        return this.mRelativePosition;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    @Nonnull
    public TimeSpan getAbsoluteStartTime() {
        return this.mAbsolutePosition;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public String getId() {
        AdBreakInfo adBreakInfo = this.mAdBreakInfo;
        if (adBreakInfo != null) {
            return adBreakInfo.getId();
        }
        return null;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public List<AdClip> getClips() {
        return Collections.unmodifiableList(this.mClips);
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public boolean isPlayed() {
        Iterator<AdClip> it = this.mClips.iterator();
        while (it.hasNext()) {
            if (!it.next().isPlayed()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public boolean isAdBreakSeekOverScheduled() {
        return this.mAdBreakSeekOverScheduled;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void scheduleAdBreakSeekOver(final TimeSpan timeSpan, @Nonnull final VideoPlayer videoPlayer, @Nonnull TimelineMonitor timelineMonitor, @Nonnull final PlaybackEventReporter playbackEventReporter, @Nonnull final PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mAdBreakSeekOverScheduled = true;
        final TimeSpan timeSpanAdd = TimeSpan.add(timeSpan, getDuration());
        DLog.logf("ServerInsertedPlaybackSession: Scheduling AdBreak seekover. absoluteAdBreakStart: %s, absoluteAdBreakEnd: %s", timeSpan, timeSpanAdd);
        final TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(Math.max(0L, timeSpan.getTotalMilliseconds() - this.mSeekOverAdBreakStartOffsetInMs));
        final TimeSpan timeSpanFromMilliseconds2 = TimeSpan.fromMilliseconds(timeSpanAdd.getTotalMilliseconds() + this.mSeekOverAdBreakEndOffsetInMs);
        ((TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor")).scheduleTask(videoPlayer, new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdBreak$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AdManagerBasedAdBreak.lambda$scheduleAdBreakSeekOver$0(videoPlayer, timeSpanFromMilliseconds2, timeSpan, timeSpanAdd, timeSpanFromMilliseconds, playbackEventReporter, playbackPmetMetricReporter);
            }
        }, timeSpanFromMilliseconds, true, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scheduleAdBreakSeekOver$0(VideoPlayer videoPlayer, TimeSpan timeSpan, TimeSpan timeSpan2, TimeSpan timeSpan3, TimeSpan timeSpan4, PlaybackEventReporter playbackEventReporter, PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        ((VideoPlayer) Preconditions.checkNotNull(videoPlayer, "videoPlayer")).seekToManifestPosition(timeSpan.getTotalMilliseconds(), SeekAction.SeekCause.AD_SEEK_OVER);
        ((PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter")).reportMetric("AdEvent", "AdBreakSeekOver", null, String.format(Locale.US, "ServerInsertedPlaybackSession: Triggering adBreak seekover. absoluteAdBreakStart: %s,  absoluteAdBreakEnd: %s,  adjustedAbsoluteAdBreakStart: %s,  adjustedAbsoluteAdBreakEnd: %s", timeSpan2, timeSpan3, timeSpan4, timeSpan), null);
        playbackPmetMetricReporter.reportServerInsertedStreamMetrics(ServerInsertedStreamPmetMetrics.AD_BREAK_SEEK_OVER);
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void preload(TimeSpan timeSpan, boolean z, String str) {
        TimeSpan timeSpanAdd = TimeSpan.ZERO;
        if (getStartTime().compareTo(timeSpan) > 0) {
            timeSpanAdd = TimeSpan.difference(getStartTime(), timeSpan);
        }
        for (AdClip adClip : getClips()) {
            adClip.preload(timeSpanAdd, z, str);
            timeSpanAdd = TimeSpan.add(timeSpanAdd, adClip.getDuration());
        }
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void sendAdBreakStart() {
        sendEvent(VmapTrackingEventType.breakStart);
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void sendAdBreakEnd() {
        sendEvent(VmapTrackingEventType.breakEnd);
    }

    public void sendError(AdBreakErrorCode adBreakErrorCode, String str) {
        this.mAdErrorSender.sendVmapError(this.mAdBreakInfo, adBreakErrorCode);
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    @Nullable
    public String getAdSystem() {
        if (this.mClips.isEmpty()) {
            return null;
        }
        return this.mClips.get(0).getAdSystem();
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public AdClip findNextUnplayedClip() {
        for (AdClip adClip : this.mClips) {
            if (!adClip.isPlayed()) {
                return adClip;
            }
        }
        return null;
    }

    private void sendEvent(final VmapTrackingEventType vmapTrackingEventType) {
        DLog.logf("Sending VMAP event(%s) for AdBreakId(%s)", vmapTrackingEventType, this.mAdBreakInfo.getId());
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdBreak.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AdManagerBasedAdBreak.this.mAdBreakInfo.sendEvent(vmapTrackingEventType);
                } catch (AdNetworkException e) {
                    DLog.exceptionf(e, "Failure to send VMAP event of type: %s", vmapTrackingEventType);
                }
            }
        });
    }

    private void initializeClips(AdInfo adInfo) throws AdTypeException {
        this.mClips.clear();
        TimeSpan startTime = getStartTime();
        for (AdInfo adInfo2 : adInfo.getDisplayableAds()) {
            AdSkipInfo parentSkipInfo = adInfo2.getParentSkipInfo();
            for (Creative creative : adInfo2.getDisplayableCreatives()) {
                if (creative.getCreativeType() == CreativeType.linear) {
                    AdManagerBasedAdClip adManagerBasedAdClipNewAdClip = this.mAdManagerBasedFactory.newAdClip();
                    CreativeLinear creativeLinear = (CreativeLinear) CastUtils.castTo(creative, CreativeLinear.class);
                    adManagerBasedAdClipNewAdClip.initialize(creativeLinear, parentSkipInfo, startTime);
                    startTime = TimeSpan.add(startTime, adManagerBasedAdClipNewAdClip.getDuration());
                    if (!this.mMediaFileChooser.hasPlayableFile(creativeLinear.getMediaFiles())) {
                        adManagerBasedAdClipNewAdClip.sendError(AdInfoErrorCode.MEDIA_FILE_NOT_SUPPORTED);
                    } else if (!adManagerBasedAdClipNewAdClip.infoUrlIsSafe()) {
                        adManagerBasedAdClipNewAdClip.sendError(AdInfoErrorCode.GENERAL_LINEAR_ERROR);
                    } else {
                        this.mClips.add(adManagerBasedAdClipNewAdClip);
                    }
                } else {
                    if (!this.mConfig.getIgnoreUnsupportedCreativeTypes()) {
                        throw new AdTypeException(String.format(Locale.US, "Received a creative that is not a linear: type: %s, adId: %s", creative.getCreativeType(), creative.getAdInfo().getAdId()));
                    }
                    DLog.warnf("Received an unsupported creative type %s", creative.getCreativeType());
                }
            }
        }
        if (this.mClips.isEmpty()) {
            DLog.logf("This ad break has no valid ads. AdId: %s", adInfo.getAdId());
        }
    }

    private AdPositionType calculateAdPositionType(TimeSpan timeSpan) {
        if (this.mRelativePosition.isZero()) {
            return AdPositionType.PRE_ROLL;
        }
        if (this.mRelativePosition.greaterThan(TimeSpan.ZERO) && this.mRelativePosition.lessThan(timeSpan)) {
            return AdPositionType.MID_ROLL;
        }
        return AdPositionType.POST_ROLL;
    }

    @Nonnull
    private List<AdInfo> getOrderedAdInfos(AdBreakInfo adBreakInfo) {
        ArrayList arrayListNewArrayList = Lists.newArrayList(adBreakInfo.getAdBuffet());
        AdInfo adPod = adBreakInfo.getAdPod();
        if (adPod != null) {
            arrayListNewArrayList.add(adPod);
        }
        return arrayListNewArrayList;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void setAdTriggeredTimeStamp(@Nonnull TimeSpan timeSpan) {
        this.mAdTriggeredTimestamp = (TimeSpan) Preconditions.checkNotNull(timeSpan, "adTriggeredAt");
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public void adBreakScheduled() {
        this.mAdBreakScheduled = true;
    }

    @Override // com.amazon.avod.media.ads.AdBreak
    public boolean isAdBreakScheduled() {
        return this.mAdBreakScheduled;
    }

    public String toString() {
        return "AdBreak { Id: " + getId() + ", StartTime: " + getStartTime() + ", Duration: " + getDuration() + ", PositionType: " + getAdPositionType();
    }
}
