package com.amazon.avod.media.ads.internal.state;

import android.util.Base64;
import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerConfig;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdPlanResult;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.TimelineManager;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.event.AdPlanFetchedEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.pmet.PMETEventReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class AsyncAdPlanRetriever implements VideoPresentationEventListener {
    private final Runnable mAcquireRunnable;

    @Nonnull
    private final AdEventTransport mAdEventTransport;
    private AdPlan mAdPlan;

    @Nonnull
    private final AdsConfig mAdsConfig;
    private final AdPlaybackStateMachineContext mContext;
    private CuepointPlaylist mCuepointPlaylist;
    private final DeviceIdentity mDeviceIdentity;
    private final ExecutorService mExecutor;

    @Nonnull
    private final IdentityShim mIdentity;
    private final AsyncAdPlanRetrieverListener mListener;

    @Nonnull
    private final ManifestCapturerConfig mManifestCapturerConfig;
    private final String mOfferType;
    private final PMETEventReporter mPMETEventReporter;
    private final AdPlanFactory mPlanFactory;
    private PlaybackEventTransport mPlaybackEventTransport;
    private final Ticker mTicker;
    private final TimelineManager mTimelineManager;
    private final TimerTask mTimeoutTask;
    private final AdEnabledPlaybackTimer mTimer;

    public interface AsyncAdPlanRetrieverListener {
        void notifyRetrievalComplete(@Nonnull AdPlan adPlan);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    public AsyncAdPlanRetriever(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdPlanFactory adPlanFactory, @Nonnull ExecutorService executorService, @Nonnull AdEnabledPlaybackTimer adEnabledPlaybackTimer, @Nonnull String str, @Nonnull AsyncAdPlanRetrieverListener asyncAdPlanRetrieverListener, @Nonnull AdEventTransport adEventTransport, @Nullable TimelineManager timelineManager, @Nonnull PlaybackEventTransport playbackEventTransport) {
        this(adPlaybackStateMachineContext, adPlanFactory, executorService, adEnabledPlaybackTimer, str, asyncAdPlanRetrieverListener, Tickers.androidTicker(), new PMETEventReporter(), adEventTransport, MediaSystemSharedDependencies.getInstance().getIdentityShim(), AdsConfig.getInstance(), timelineManager, playbackEventTransport, MediaSystemSharedDependencies.getInstance().getDeviceIdentity(), ManifestCapturerConfig.INSTANCE);
    }

    AsyncAdPlanRetriever(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdPlanFactory adPlanFactory, @Nonnull ExecutorService executorService, @Nonnull AdEnabledPlaybackTimer adEnabledPlaybackTimer, @Nonnull String str, @Nonnull AsyncAdPlanRetrieverListener asyncAdPlanRetrieverListener, @Nonnull Ticker ticker, @Nonnull PMETEventReporter pMETEventReporter, @Nonnull AdEventTransport adEventTransport, @Nonnull IdentityShim identityShim, @Nonnull AdsConfig adsConfig, @Nullable TimelineManager timelineManager, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull DeviceIdentity deviceIdentity, @Nonnull ManifestCapturerConfig manifestCapturerConfig) {
        this.mAdPlan = EmptyAdPlan.INSTANCE;
        this.mAcquireRunnable = new Runnable() { // from class: com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.acquireManifest();
            }
        };
        this.mTimeoutTask = new TimerTask() { // from class: com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AsyncAdPlanRetriever.this.reportTimeoutError();
            }
        };
        this.mContext = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "context");
        this.mPlanFactory = (AdPlanFactory) Preconditions.checkNotNull(adPlanFactory, "planFactory");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mTimer = (AdEnabledPlaybackTimer) Preconditions.checkNotNull(adEnabledPlaybackTimer, "timer");
        this.mOfferType = (String) Preconditions.checkNotNull(str, "offerType");
        this.mListener = (AsyncAdPlanRetrieverListener) Preconditions.checkNotNull(asyncAdPlanRetrieverListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
        this.mPMETEventReporter = (PMETEventReporter) Preconditions.checkNotNull(pMETEventReporter, "pmetEventReporter");
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identity");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mTimelineManager = timelineManager;
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport);
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mManifestCapturerConfig = (ManifestCapturerConfig) Preconditions.checkNotNull(manifestCapturerConfig, "manifestCapturerConfig");
    }

    public void prepareAdPlan() {
        DLog.logf("Preparing ad plan...");
        this.mContext.getUriProxy().sync();
        this.mTimer.start(this.mTimeoutTask);
        if (this.mAdsConfig.isSSAIEnabled()) {
            return;
        }
        this.mExecutor.execute(this.mAcquireRunnable);
    }

    void acquireManifest() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "%s:AdPlanRetrieval", AsyncAdPlanRetriever.class.getSimpleName());
        VideoSpecification videoSpecification = this.mContext.getVideoSpecification();
        VideoOptions videoOptions = this.mContext.getPlaybackSessionContext().getVideoOptions();
        PlaybackEventReporter primaryEventReporter = this.mContext.getPrimaryEventReporter();
        if (videoSpecification.isLiveStream() || videoSpecification.isTrailer() || ((this.mAdsConfig.shouldSuppressCuepointsForChildRole() && this.mIdentity.isChild()) || !videoSpecification.shouldShowAds())) {
            this.mListener.notifyRetrievalComplete(EmptyAdPlan.INSTANCE);
            TimelineManager timelineManager = this.mTimelineManager;
            if (timelineManager != null) {
                timelineManager.notifyAdPlanRetrievalComplete(this.mAdPlan, this.mContext);
            }
            this.mTimer.stop();
            Profiler.endTrace(traceKeyBeginTrace);
            return;
        }
        try {
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(this.mTicker);
            AdPlanResult adPlanResultNewAdPlan = this.mPlanFactory.newAdPlan(videoSpecification, videoOptions.getSessionContext(), this.mOfferType, primaryEventReporter, getConsumptionId(), null, this.mCuepointPlaylist, this.mContext.getPlaybackSessionContext().getRendererSchemeType());
            this.mAdPlan = adPlanResultNewAdPlan.getAdPlan();
            if (adPlanResultNewAdPlan.getCuepointPlaylistOptional().isPresent()) {
                this.mCuepointPlaylist = adPlanResultNewAdPlan.getCuepointPlaylistOptional().get();
            }
            DLog.logf("Successfully acquired ad plan.");
            this.mPlaybackEventTransport.postEvent(new AdPlanFetchedEvent(this.mAdPlan));
            StringBuilder sb = new StringBuilder(String.format("OfferType: %s Adplan Duration: %s", this.mOfferType, this.mAdPlan.getDuration()));
            for (AdBreak adBreak : this.mAdPlan.getBreaks()) {
                sb.append(adBreak);
                Iterator<AdClip> it = adBreak.getClips().iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                }
            }
            primaryEventReporter.reportMetric("AdEvent", "AdPlanDownloaded", new TimeSpan(stopwatchCreateStarted.stop()), sb.toString(), null);
        } catch (MediaException e) {
            String str = String.format(Locale.US, "Error acquiring ad plan: %s", e.getMessage());
            DLog.errorf(str);
            primaryEventReporter.reportError("AdPlanError", str, null);
            this.mPMETEventReporter.reportCounterMetric(new SimpleCounterMetric("AdPlanRetrievalFailed", (ImmutableList<String>) ImmutableList.of("Counter", (e.getCause() == null ? e : e.getCause()).getClass().getSimpleName())));
            this.mAdEventTransport.postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, e));
        }
        this.mListener.notifyRetrievalComplete(this.mAdPlan);
        TimelineManager timelineManager2 = this.mTimelineManager;
        if (timelineManager2 != null) {
            timelineManager2.notifyAdPlanRetrievalComplete(this.mAdPlan, this.mContext);
        }
        this.mTimer.stop();
        Profiler.endTrace(traceKeyBeginTrace);
    }

    void reportTimeoutError() {
        String str = String.format(Locale.US, "Timed out download ad plan after %s", this.mTimer.getTimeout());
        DLog.errorf(str);
        this.mContext.getPrimaryEventReporter().reportError("AdPlanError", str, null);
        AdPlan adPlan = this.mAdPlan;
        if (adPlan == null || adPlan.getBreaks().isEmpty()) {
            this.mListener.notifyRetrievalComplete(EmptyAdPlan.INSTANCE);
        }
        this.mAdEventTransport.postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.NETWORK_ERROR, "Timeout downloading Ad Plan")));
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        TimelineManager timelineManager = this.mTimelineManager;
        if (timelineManager != null) {
            timelineManager.notifyAdPlanRetrievalComplete(this.mAdPlan);
        }
    }

    public void setUpAdPlan(@Nullable CuepointPlaylist cuepointPlaylist) {
        this.mCuepointPlaylist = cuepointPlaylist;
        acquireManifest();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
        if (shouldCaptureAdManifest()) {
            captureAndUploadAdManifest();
        }
    }

    private void captureAndUploadAdManifest() {
        String str = this.mAdsConfig.isSSAIEnabled() ? "ServerInsertedPlaybackSession" : "ClientInsertedPlaybackSession";
        ManifestCapturerInterface manifestCapturer = getManifestCapturer();
        if (manifestCapturer == null || this.mCuepointPlaylist == null) {
            DLog.logf(str + ": unable to capture ad manifest");
            return;
        }
        manifestCapturer.capture(new Date().getTime(), ByteBuffer.wrap(Base64.decode(this.mCuepointPlaylist.encodedManifest.getBytes(), 0)), getConsumptionId(), this.mDeviceIdentity.getDeviceId(), "AdManifest", false, null);
        manifestCapturer.uploadAdManifest();
        this.mContext.getPrimaryEventReporter().reportMetric("AdEvent", "AdManifestUpload", null, String.format(Locale.US, "%s: uploading adManifest to s3", str), null);
    }

    @Nullable
    protected String getConsumptionId() {
        ContentSession contentSession = this.mContext.getPlaybackSessionContext().getContentSession();
        if (contentSession == null) {
            return null;
        }
        try {
            return contentSession.getConsumptionId();
        } catch (IllegalStateException e) {
            DLog.warnf("cannot get consumption id for Aloysius reporting : %s", String.valueOf(e.getLocalizedMessage()));
            return null;
        }
    }

    @Nullable
    private ManifestCapturerInterface getManifestCapturer() {
        ContentSession contentSession = this.mContext.getPlaybackSessionContext().getContentSession();
        if (contentSession != null) {
            return contentSession.getManifestCapturer();
        }
        return null;
    }

    private boolean shouldCaptureAdManifest() {
        return this.mManifestCapturerConfig.shouldEnableAdManifestCapturer() && (!this.mAdsConfig.isSSAIEnabled() ? !this.mManifestCapturerConfig.shouldCaptureCSAIManifests() : !this.mManifestCapturerConfig.shouldCaptureSSAIManifests());
    }
}
