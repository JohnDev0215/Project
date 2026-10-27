package com.amazon.avod.playback.sye;

import com.amazon.atvplaybackresource.AuditPing;
import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdReportingUtils;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.playback.event.playback.AdTrackingAuditPingEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.sye.events.SyePlaybackStartEvent;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.eventbus.Subscribe;
import java.net.URI;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SyeAuditPingTracker {
    private final AdHttpClient mAdHttpClient;
    private final AdvertisingIdCollector.AdvertisingInfo mAdInfo;
    private final AdReportingUtils mAdReportingUtils;
    private final AloysiusReporterHolder mAloysiusReporterHolder;
    private final String mAuditPingAdAgency;
    private final int mAuditPingStartCode;
    private final int mAuditPingStopCode;
    private final int mAuditPingUooStatusOptInCode;
    private final int mAuditPingUooStatusOptOutCode;
    private final ImmutableList<AuditPing> mAuditPingUrls;
    private final String mConsumptionId;
    private final boolean mIsAuditPingEnabled;
    private final ExecutorService mPingExecutor;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final String mSessionId;
    private final String mStreamId;

    SyeAuditPingTracker(@Nonnull ImmutableList<AuditPing> immutableList, @Nonnull String str, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdvertisingIdCollector advertisingIdCollector) {
        this(immutableList, str, aloysiusReporterHolder, playbackEventTransport, advertisingIdCollector, UUID.randomUUID().toString(), UUID.randomUUID().toString(), Executors.newSingleThreadExecutor(), AdReportingUtils.getInstance(), PlaybackConfig.INSTANCE, AdsConfig.getInstance());
    }

    SyeAuditPingTracker(@Nonnull ImmutableList<AuditPing> immutableList, @Nonnull String str, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull String str2, @Nonnull String str3, @Nonnull ExecutorService executorService, @Nonnull AdReportingUtils adReportingUtils, @Nonnull PlaybackConfig playbackConfig, @Nonnull AdsConfig adsConfig) {
        this.mAuditPingUrls = (ImmutableList) Preconditions.checkNotNull(immutableList, "auditPingUrls");
        this.mConsumptionId = (String) Preconditions.checkNotNull(str, "consumptionId");
        this.mSessionId = (String) Preconditions.checkNotNull(str2, Splash.PARAMS_SESSION_ID);
        this.mStreamId = (String) Preconditions.checkNotNull(str3, "streamId");
        this.mAloysiusReporterHolder = (AloysiusReporterHolder) Preconditions.checkNotNull(aloysiusReporterHolder, "aloysiusReporterHolder");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mAdReportingUtils = (AdReportingUtils) Preconditions.checkNotNull(adReportingUtils, "adReportingUtils");
        Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mIsAuditPingEnabled = playbackConfig.isAuditPingEnabled();
        this.mAuditPingAdAgency = playbackConfig.getSupportedAdAgencyForAuditPing();
        this.mAuditPingStartCode = playbackConfig.getAuditPingStartCode();
        this.mAuditPingStopCode = playbackConfig.getAuditPingStopCode();
        this.mAuditPingUooStatusOptOutCode = playbackConfig.getAuditPingUooStatusOptOutCode();
        this.mAuditPingUooStatusOptInCode = playbackConfig.getAuditPingUooStatusOptInCode();
        Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mAdHttpClient = adReportingUtils.createAdHttpClient(adsConfig);
        Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mAdInfo = advertisingIdCollector.get(adsConfig.getGPAIDFetchTimeOut().getTotalMilliseconds(), false);
        this.mPingExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "pingExecutor");
    }

    void start() {
        this.mPlaybackEventTransport.registerEventBusHandler(this);
    }

    void stop() {
        this.mPlaybackEventTransport.unregisterEventBusHandler(this);
    }

    @Subscribe
    public void handlePlaybackStartEvent(@Nonnull SyePlaybackStartEvent syePlaybackStartEvent) {
        DLog.devf("SyeAuditPingTracker: playback start event received");
        String auditPingUrl = getAuditPingUrl(this.mAuditPingUrls, this.mAuditPingAdAgency);
        if (checkAuditPingUrl(auditPingUrl)) {
            sendAuditPing(auditPingUrl, this.mAuditPingStartCode);
            this.mAloysiusReporterHolder.getAdIdentifierReporter().onAdTrackingAuditPingEvent(new AdTrackingAuditPingEvent(syePlaybackStartEvent.getEventTimeStamp(), auditPingUrl, this.mConsumptionId, this.mAuditPingAdAgency, this.mSessionId, this.mStreamId));
        }
    }

    @Subscribe
    public void handlePlaybackStopEvent(@Nonnull PlaybackStopEvent playbackStopEvent) {
        DLog.devf("SyeAuditPingTracker: playback stop event received");
        String auditPingUrl = getAuditPingUrl(this.mAuditPingUrls, this.mAuditPingAdAgency);
        if (checkAuditPingUrl(auditPingUrl)) {
            sendAuditPing(auditPingUrl, this.mAuditPingStopCode);
        }
    }

    private boolean checkAuditPingUrl(String str) {
        if (!this.mIsAuditPingEnabled) {
            return false;
        }
        if (Strings.isNullOrEmpty(str)) {
            DLog.warnf("Audit ping url not available");
            return false;
        }
        DLog.devf("Audit ping available");
        return true;
    }

    private void sendAuditPing(String str, int i) {
        String strConstructNielsenAuditPingUrl = this.mAdReportingUtils.constructNielsenAuditPingUrl(this.mAdInfo, str, this.mSessionId, this.mStreamId, TimeSpan.fromMilliseconds(System.currentTimeMillis()).getTotalSeconds(), i, this.mAuditPingUooStatusOptInCode, this.mAuditPingUooStatusOptOutCode);
        DLog.logf("SyeAuditPingTracker: audit ping at %s", strConstructNielsenAuditPingUrl);
        if (Strings.isNullOrEmpty(strConstructNielsenAuditPingUrl)) {
            return;
        }
        this.mAdReportingUtils.pingBeacon(new Beacon(new VastTracking(VastTrackingEventType.auditPingEvent, null, URI.create(strConstructNielsenAuditPingUrl)), this.mAdHttpClient), this.mPingExecutor);
    }

    @Nullable
    private String getAuditPingUrl(ImmutableList<AuditPing> immutableList, String str) {
        UnmodifiableIterator<AuditPing> it = immutableList.iterator();
        while (it.hasNext()) {
            AuditPing next = it.next();
            if (str.equalsIgnoreCase(next.adReportingAgency.orNull())) {
                return next.url.orNull();
            }
        }
        return null;
    }
}
