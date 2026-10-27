package com.amazon.avod.media.ads.internal;

import UpsellInterface.v1_0.ContentTrait;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.ads.api.AdBreakInfo;
import com.amazon.avod.ads.api.AdInfo;
import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.ads.api.AdManifest;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class AdManagerBasedAdPlan implements AdPlan {
    private static final TimeSpan OFFSET_MARGIN = TimeSpan.fromMilliseconds(2000);
    private final AdErrorReporter mAdErrorSender;
    private final AdManagerBasedFactory mAdManagerBasedFactory;
    private TimeSpan mAdPlanDuration;
    private final List<AdBreak> mBreaks;
    private final String mOfferType;

    @Nonnull
    private final QOSCommunicationService mQOSCommunicationService;
    private final boolean mShowCountdownTimer;

    protected TimeSpan getAbsoluteVideoDuration(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        return timeSpan;
    }

    AdManagerBasedAdPlan(AdManagerBasedFactory adManagerBasedFactory, ExecutorService executorService, String str, boolean z, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this(adManagerBasedFactory, new AdErrorReporter(executorService), str, z, qOSCommunicationService);
    }

    @VisibleForTesting
    AdManagerBasedAdPlan(@Nonnull AdManagerBasedFactory adManagerBasedFactory, @Nonnull AdErrorReporter adErrorReporter, @Nullable String str, boolean z, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this.mBreaks = Lists.newLinkedList();
        this.mAdPlanDuration = TimeSpan.ZERO;
        this.mAdManagerBasedFactory = (AdManagerBasedFactory) Preconditions.checkNotNull(adManagerBasedFactory);
        this.mAdErrorSender = (AdErrorReporter) Preconditions.checkNotNull(adErrorReporter);
        this.mOfferType = str;
        this.mShowCountdownTimer = z;
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "qosCommunicationService");
    }

    public void initialize(AdManifest adManifest, TimeSpan timeSpan, PlaybackEventReporter playbackEventReporter) {
        for (AdBreakInfo adBreakInfo : sortAdBreaks(adManifest.getAdBreaks(), timeSpan)) {
            try {
                AdManagerBasedAdBreak adManagerBasedAdBreakNewAdBreak = this.mAdManagerBasedFactory.newAdBreak();
                adManagerBasedAdBreakNewAdBreak.initialize(adBreakInfo, timeSpan, this.mAdPlanDuration);
                TimeSpan absoluteVideoDuration = getAbsoluteVideoDuration(timeSpan, this.mAdPlanDuration);
                if (isAdBreakWithinPrimaryContent(adBreakInfo, absoluteVideoDuration)) {
                    this.mBreaks.add(adManagerBasedAdBreakNewAdBreak);
                    this.mAdPlanDuration = TimeSpan.add(this.mAdPlanDuration, adManagerBasedAdBreakNewAdBreak.getDuration());
                } else {
                    reportAdBreakBeyondPrimaryContent(adBreakInfo, absoluteVideoDuration);
                }
            } catch (MediaException e) {
                DLog.warnf("An ad break (%s) failed to initialize. Exception: %s", adBreakInfo.getId(), e);
                playbackEventReporter.reportError("AdBreakInitializationError", e.getMessage(), String.format(Locale.US, "Ad Break Id: %s", adBreakInfo.getId()));
            }
        }
        Collections.sort(this.mBreaks, AdBreakTimeBasedComparator.INSTANCE);
    }

    private List<AdBreakInfo> sortAdBreaks(@Nonnull List<AdBreakInfo> list, @Nullable final TimeSpan timeSpan) {
        Collections.sort(list, new Comparator<AdBreakInfo>() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan.1
            @Override // java.util.Comparator
            public int compare(AdBreakInfo adBreakInfo, AdBreakInfo adBreakInfo2) {
                long totalMilliseconds;
                long totalMilliseconds2;
                if (timeSpan == null) {
                    totalMilliseconds = adBreakInfo.getOffset().getDuration().getTotalMilliseconds();
                    totalMilliseconds2 = adBreakInfo2.getOffset().getDuration().getTotalMilliseconds();
                } else {
                    totalMilliseconds = adBreakInfo.getOffset().getDuration(timeSpan.getTotalMilliseconds()).getTotalMilliseconds();
                    totalMilliseconds2 = adBreakInfo2.getOffset().getDuration(timeSpan.getTotalMilliseconds()).getTotalMilliseconds();
                }
                return Long.compare(totalMilliseconds, totalMilliseconds2);
            }
        });
        return list;
    }

    private void reportAdBreakBeyondPrimaryContent(AdBreakInfo adBreakInfo, TimeSpan timeSpan) {
        String str = String.format(Locale.US, "adBreak (%s) was not added to addPlan because it extended past primaryContent length %s, AdBreakPosition: %s.", adBreakInfo.getId(), timeSpan, adBreakInfo.getOffset().getDuration(timeSpan.getTotalMilliseconds()));
        DLog.logf(str);
        this.mAdErrorSender.sendError(adBreakInfo.getAdPod(), AdInfoErrorCode.PLAYER_EXPECTING_DIFFERENT_DURATION);
        this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.UNKNOWN_ERROR, str)));
        Iterator<? extends AdInfo> it = adBreakInfo.getAdBuffet().iterator();
        while (it.hasNext()) {
            this.mAdErrorSender.sendError(it.next(), AdInfoErrorCode.PLAYER_EXPECTING_DIFFERENT_DURATION);
        }
    }

    private boolean isAdBreakWithinPrimaryContent(AdBreakInfo adBreakInfo, TimeSpan timeSpan) {
        return TimeSpan.fromMilliseconds(adBreakInfo.getOffset().getDuration(timeSpan.getTotalMilliseconds()).getTotalMilliseconds()).lessThanEquals(TimeSpan.add(timeSpan, OFFSET_MARGIN));
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public List<AdBreak> getBreaks() {
        return Collections.unmodifiableList(this.mBreaks);
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean isDraper() {
        return !ContentTrait.AD_SUPPORTED.equals(this.mOfferType);
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean hasPlayableAds() {
        Iterator<AdBreak> it = this.mBreaks.iterator();
        while (it.hasNext()) {
            if (!it.next().getClips().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean showCountdownTimer() {
        return this.mShowCountdownTimer;
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public TimeSpan getDuration() {
        return this.mAdPlanDuration;
    }
}
