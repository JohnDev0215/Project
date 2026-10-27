package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PreloadPrerollBreaks extends SimpleTask {
    private final AdsConfig mAdsConfig;
    private final PlaybackSessionContext mPlaybackSessionContext;

    public PreloadPrerollBreaks(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionContext playbackSessionContext) {
        this(eventBus, exceptionCallback, playbackSessionContext, AdsConfig.getInstance());
    }

    PreloadPrerollBreaks(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull AdsConfig adsConfig) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        TimeSpan startTime = ((VideoSpecification) Preconditions.checkNotNull(this.mPlaybackSessionContext.getVideoSpec(), "videoSpec")).getStartTime();
        if (this.mAdsConfig.getPreInitAdPreloadingEnabled() && startTime.isZero()) {
            AdPlan adPlan = this.mPlaybackSessionContext.getAdPlan();
            ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
            String consumptionId = contentSession != null ? contentSession.getConsumptionId() : null;
            for (AdBreak adBreak : adPlan.getBreaks()) {
                if (adBreak.getStartTime().isZero()) {
                    adBreak.preload(startTime, shouldStoreInPersistentCache(adBreak.getAdPositionType(), adPlan.isDraper(), this.mAdsConfig.isPersistentCacheForDraperPreRollsEnabled(), this.mAdsConfig.isPersistentCacheForAvodPreRollsEnabled()), consumptionId);
                    return;
                }
            }
        }
    }

    private boolean shouldStoreInPersistentCache(@Nonnull AdPositionType adPositionType, boolean z, boolean z2, boolean z3) {
        return adPositionType == AdPositionType.PRE_ROLL && (!z ? !z3 : !z2);
    }
}
