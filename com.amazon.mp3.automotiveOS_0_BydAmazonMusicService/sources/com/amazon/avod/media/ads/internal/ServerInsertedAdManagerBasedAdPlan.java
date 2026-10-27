package com.amazon.avod.media.ads.internal;

import com.amazon.avod.ads.api.AdManifest;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.playback.PlaybackEventReporter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedAdManagerBasedAdPlan extends AdManagerBasedAdPlan {
    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan, com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public /* bridge */ /* synthetic */ List getBreaks() {
        return super.getBreaks();
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan, com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public /* bridge */ /* synthetic */ TimeSpan getDuration() {
        return super.getDuration();
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan, com.amazon.avod.media.ads.AdPlan
    public /* bridge */ /* synthetic */ boolean hasPlayableAds() {
        return super.hasPlayableAds();
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan
    public /* bridge */ /* synthetic */ void initialize(AdManifest adManifest, TimeSpan timeSpan, PlaybackEventReporter playbackEventReporter) {
        super.initialize(adManifest, timeSpan, playbackEventReporter);
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan, com.amazon.avod.media.ads.AdPlan
    public /* bridge */ /* synthetic */ boolean isDraper() {
        return super.isDraper();
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan, com.amazon.avod.media.ads.AdPlan
    public /* bridge */ /* synthetic */ boolean showCountdownTimer() {
        return super.showCountdownTimer();
    }

    public ServerInsertedAdManagerBasedAdPlan(AdManagerBasedFactory adManagerBasedFactory, ExecutorService executorService, String str, boolean z, @Nonnull QOSCommunicationService qOSCommunicationService) {
        super(adManagerBasedFactory, executorService, str, z, qOSCommunicationService);
    }

    @Override // com.amazon.avod.media.ads.internal.AdManagerBasedAdPlan
    protected TimeSpan getAbsoluteVideoDuration(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        return TimeSpan.add(timeSpan, timeSpan2);
    }
}
