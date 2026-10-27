package com.amazon.avod.playback.session.workflow.tasks;

import android.util.Base64;
import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerConfig;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.urlvending.CuepointPlaylistTransformer;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackTimer;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.service.cache.PlayerResourcesCache;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheRequest;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.pmet.PMETEventReporter;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareAdPlan extends AsyncResponseBasedTask {
    private final AdEnabledPlaybackTimer mAdEnabledPlaybackTimer;
    private final String mAdManifestS3Identifier;
    private final AdsConfig mAdsConfig;
    private final DeviceIdentity mDeviceIdentity;
    private final IdentityShim mIdentity;
    private boolean mIsTaskComplete;
    private final ManifestCapturerInterface mManifestCapturer;
    private final PMETEventReporter mPMETEventReporter;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private final PlayerResourcesCache mPlayerResourcesCache;
    private final boolean mShouldCaptureSSAIManifests;
    private final boolean mShouldEnableAdManifestCapturer;
    private final Object mTaskCompleteLock;
    private final Ticker mTicker;

    public PrepareAdPlan(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSession playbackSession, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        PlaybackSessionResources resources = playbackSession.getResources();
        AdEnabledPlaybackTimer adEnabledPlaybackTimer = new AdEnabledPlaybackTimer(AdsConfig.getInstance().getAdClipPrepareTimeout());
        Ticker tickerAndroidTicker = Tickers.androidTicker();
        PMETEventReporter pMETEventReporter = new PMETEventReporter();
        PlayerResourcesCache playerResourcesCache = PlayerResourcesCache.getInstance();
        IdentityShim identityShim = MediaSystemSharedDependencies.getInstance().getIdentityShim();
        AdsConfig adsConfig = AdsConfig.getInstance();
        DeviceIdentity deviceIdentity = MediaSystemSharedDependencies.getInstance().getDeviceIdentity();
        ManifestCapturerConfig manifestCapturerConfig = ManifestCapturerConfig.INSTANCE;
        this(eventBus, exceptionCallback, resources, playbackSessionContext, adEnabledPlaybackTimer, tickerAndroidTicker, pMETEventReporter, playerResourcesCache, identityShim, adsConfig, deviceIdentity, manifestCapturerConfig.shouldEnableAdManifestCapturer(), manifestCapturerConfig.shouldCaptureSSAIManifests(), "AdManifest", manifestCapturerInterface);
    }

    PrepareAdPlan(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull AdEnabledPlaybackTimer adEnabledPlaybackTimer, @Nonnull Ticker ticker, @Nonnull PMETEventReporter pMETEventReporter, @Nonnull PlayerResourcesCache playerResourcesCache, @Nonnull IdentityShim identityShim, @Nonnull AdsConfig adsConfig, @Nonnull DeviceIdentity deviceIdentity, boolean z, boolean z2, @Nonnull String str, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        super(eventBus, exceptionCallback);
        this.mTaskCompleteLock = new Object();
        this.mIsTaskComplete = false;
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mAdEnabledPlaybackTimer = (AdEnabledPlaybackTimer) Preconditions.checkNotNull(adEnabledPlaybackTimer, "adEnabledPlaybackTimer");
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
        this.mPMETEventReporter = (PMETEventReporter) Preconditions.checkNotNull(pMETEventReporter, "pmetEventReporter");
        this.mPlayerResourcesCache = (PlayerResourcesCache) Preconditions.checkNotNull(playerResourcesCache, "playerResourcesCache");
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identity");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mShouldEnableAdManifestCapturer = z;
        this.mShouldCaptureSSAIManifests = z2;
        this.mAdManifestS3Identifier = (String) Preconditions.checkNotNull(str, "adManifestS3Identifier");
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        AdEnabledPlaybackManager adEnabledPlaybackManager = this.mPlaybackSessionContext.getAdEnabledPlaybackManager();
        if (adEnabledPlaybackManager == null) {
            notifyTaskCompletedIfNecessary();
            return;
        }
        DLog.logf("PlayerLifecycleWorkflow - Preparing ad plan...");
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "%s:AdPlanRetrieval", getClass().getSimpleName());
        this.mPlaybackSessionResources.getAdUriProxy().sync();
        if (this.mAdsConfig.shouldEnableAdPlanTimeOutInDAGSSAI() || !this.mAdsConfig.isSSAIEnabled()) {
            this.mAdEnabledPlaybackTimer.start(new TimerTask() { // from class: com.amazon.avod.playback.session.workflow.tasks.PrepareAdPlan.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    PrepareAdPlan.this.reportTimeoutError();
                }
            });
        }
        VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
        VideoOptions videoOptions = this.mPlaybackSessionContext.getVideoOptions();
        PlaybackEventReporter playbackReporter = this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter();
        String offerType = videoOptions.getOfferType();
        boolean z = this.mAdsConfig.shouldSuppressCuepointsForChildRole() && this.mIdentity.isChild();
        boolean z2 = this.mAdsConfig.shouldSuppressAdsForDownloads() && this.mPlaybackSessionContext.getDownload() != null;
        AdPlan adPlan = EmptyAdPlan.INSTANCE;
        if (videoSpec != null && !videoSpec.isLiveStream() && !videoSpec.isTrailer() && !z && !z2 && videoSpec.shouldShowAds()) {
            adPlan = getAdPlan(videoSpec, videoOptions, offerType, playbackReporter, adEnabledPlaybackManager);
        }
        adEnabledPlaybackManager.updateAdPlan(adPlan);
        notifyTaskCompletedIfNecessary();
        Profiler.endTrace(traceKeyBeginTrace);
    }

    private AdPlan getAdPlan(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager) {
        EmptyAdPlan emptyAdPlan = EmptyAdPlan.INSTANCE;
        try {
            PlaybackResources validatedResources = this.mPlayerResourcesCache.getValidatedResources(new PlayerResourcesCacheRequest(videoSpecification, ConsumptionType.Streaming, videoOptions.getSessionContext()));
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(this.mTicker);
            CuepointPlaylist cuepointPlaylist = getCuepointPlaylist(validatedResources);
            if (this.mAdsConfig.isSSAIEnabledForDownloads() && this.mPlaybackSessionContext.getDeliveryType() == DeliveryType.DOWNLOAD) {
                return createAdPlan(videoSpecification, videoOptions, str, playbackEventReporter, validatedResources, cuepointPlaylist, adEnabledPlaybackManager, stopwatchCreateStarted, "ServerInsertedDownload");
            }
            if (shouldCaptureAdManifest() && cuepointPlaylist != null) {
                captureAndUploadAdManifest(cuepointPlaylist);
            }
            return createAdPlan(videoSpecification, videoOptions, str, playbackEventReporter, validatedResources, cuepointPlaylist, adEnabledPlaybackManager, stopwatchCreateStarted, "ServerInsertedStreaming");
        } catch (MediaException | IllegalStateException e) {
            e = e;
            String str2 = String.format(Locale.US, "PlayerLifecycleWorkflow - Error acquiring ad plan: %s", e.getMessage());
            DLog.errorf(str2);
            playbackEventReporter.reportError("AdPlanError", str2, null);
            if (e.getCause() != null) {
                e = e.getCause();
            }
            this.mPMETEventReporter.reportCounterMetric(new SimpleCounterMetric("AdPlanRetrievalFailed", (ImmutableList<String>) ImmutableList.of("Counter", e.getClass().getSimpleName())));
            return emptyAdPlan;
        }
    }

    private AdPlan createAdPlan(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable PlaybackResources playbackResources, @Nullable CuepointPlaylist cuepointPlaylist, @Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager, @Nonnull Stopwatch stopwatch, @Nonnull String str2) throws MediaException {
        AdPlan adPlan = this.mPlaybackSessionResources.getAdPlanFactory().newAdPlan(videoSpecification, videoOptions.getSessionContext(), str, playbackEventReporter, getConsumptionId(), playbackResources, cuepointPlaylist, this.mPlaybackSessionContext.getRendererSchemeType()).getAdPlan();
        DLog.logf("PlayerLifecycleWorkflow - Successfully acquired ad plan.");
        playbackEventReporter.reportMetric("AdEvent", "AdPlanDownloaded", new TimeSpan(stopwatch.stop()), str, null);
        playbackEventReporter.reportMetric("AdEvent", str2, null, "AdPlanCreated successfully", null);
        adEnabledPlaybackManager.getContext().updatePlan(adPlan);
        return adPlan;
    }

    @Nullable
    private CuepointPlaylist getCuepointPlaylist(@Nonnull PlaybackResources playbackResources) {
        CacheRecord cacheRecord = this.mPlaybackSessionContext.getCacheRecord();
        Optional<CuepointPlaylist> cuepointPlaylist = playbackResources.getCuepointPlaylist();
        if (cacheRecord != null && cacheRecord.getAudioVideoUrls() != null) {
            return CuepointPlaylistTransformer.transform(cacheRecord.getAudioVideoUrls().getCuepointPlaylistInfo());
        }
        if (cuepointPlaylist.isPresent()) {
            return cuepointPlaylist.get();
        }
        return null;
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        notifyTaskCompletedIfNecessary();
    }

    void reportTimeoutError() {
        synchronized (this.mTaskCompleteLock) {
            if (this.mIsTaskComplete) {
                return;
            }
            String str = String.format(Locale.US, "Timed out download ad plan after %s", this.mAdEnabledPlaybackTimer.getTimeout());
            DLog.errorf(str);
            this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter().reportError("AdPlanError", str, null);
            notifyTaskCompletedIfNecessary();
        }
    }

    private void notifyTaskCompletedIfNecessary() {
        synchronized (this.mTaskCompleteLock) {
            if (this.mIsTaskComplete) {
                return;
            }
            this.mAdEnabledPlaybackTimer.stop();
            this.mIsTaskComplete = true;
            notifyTaskSuccess();
        }
    }

    @Nullable
    protected String getConsumptionId() {
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        if (contentSession != null) {
            return contentSession.getConsumptionId();
        }
        return null;
    }

    private void captureAndUploadAdManifest(@Nonnull CuepointPlaylist cuepointPlaylist) {
        AdPlaybackStateMachineContext context = this.mPlaybackSessionContext.getAdEnabledPlaybackManager().getContext();
        this.mManifestCapturer.capture(new Date().getTime(), ByteBuffer.wrap(Base64.decode(cuepointPlaylist.encodedManifest.getBytes(), 0)), getConsumptionId(), this.mDeviceIdentity.getDeviceId(), this.mAdManifestS3Identifier, false, null);
        this.mManifestCapturer.uploadAdManifest();
        context.getPrimaryEventReporter().reportMetric("AdEvent", "AdManifestUpload", null, "ServerInsertedPlaybackSession: uploading adManifest to s3", null);
    }

    private boolean shouldCaptureAdManifest() {
        return this.mShouldEnableAdManifestCapturer && this.mShouldCaptureSSAIManifests;
    }
}
