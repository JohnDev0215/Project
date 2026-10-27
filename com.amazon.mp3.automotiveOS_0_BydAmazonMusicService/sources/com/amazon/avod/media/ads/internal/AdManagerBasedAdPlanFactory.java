package com.amazon.avod.media.ads.internal;

import android.util.Base64;
import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atv.cuepoint.CuepointPlaylistMetadata;
import com.amazon.atvplaybackresource.types.EntitlementType;
import com.amazon.avod.ads.api.AdManifest;
import com.amazon.avod.ads.api.AdParsingException;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.http.BoltHttpAdClient;
import com.amazon.avod.ads.http.DefaultAdHttpClient;
import com.amazon.avod.ads.http.HttpParameters;
import com.amazon.avod.ads.parser.VastVmapParser;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.http.HttpRequestBuilder;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdQualityConfig;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.service.GetPlaybackResourcesServiceClient;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.AdVastManifestDownloadedEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableList;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdManagerBasedAdPlanFactory implements AdPlanFactory {
    private final AdManagerBasedFactory mAdManagerBasedFactory;
    private final AdsConfig mAdsConfig;
    private final PersistentAndroidCookieStore mCookieStore;
    private final GetPlaybackResourcesServiceClient mGetPlaybackResourcesServiceClient;
    private final HttpParameters.Builder mHttpParamsBuilder;

    @Nonnull
    private final QOSCommunicationService mQOSCommunicationService;
    private final Ticker mTicker;

    public AdManagerBasedAdPlanFactory(MediaSystemSharedContext mediaSystemSharedContext, FirstMatchVideoPresentationFactory firstMatchVideoPresentationFactory, AdUriProxy adUriProxy, DownloadService downloadService, PlaybackSupportEvaluator playbackSupportEvaluator, QOSCommunicationService qOSCommunicationService, AdvertisingIdCollector advertisingIdCollector, AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
        this(new AdManagerBasedFactory(firstMatchVideoPresentationFactory, mediaSystemSharedContext.getExecutorService(), adUriProxy, AdsConfig.getInstance(), new AdClipMediaFileChooser(downloadService, AdQualityConfig.getInstance(mediaSystemSharedContext.getDeviceConfiguration())), qOSCommunicationService, androidVideoPlayerV2Cache), AdsConfig.getInstance(), new HttpParameters.Builder(), new GetPlaybackResourcesServiceClient(playbackSupportEvaluator, advertisingIdCollector, qOSCommunicationService), new PersistentAndroidCookieStore(), Tickers.androidTicker(), qOSCommunicationService);
    }

    AdManagerBasedAdPlanFactory(@Nonnull AdManagerBasedFactory adManagerBasedFactory, @Nonnull AdsConfig adsConfig, @Nonnull HttpParameters.Builder builder, @Nonnull GetPlaybackResourcesServiceClient getPlaybackResourcesServiceClient, @Nonnull PersistentAndroidCookieStore persistentAndroidCookieStore, @Nonnull Ticker ticker, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this.mAdManagerBasedFactory = (AdManagerBasedFactory) Preconditions.checkNotNull(adManagerBasedFactory, "factory");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mHttpParamsBuilder = (HttpParameters.Builder) Preconditions.checkNotNull(builder, "httpParamsBuilder");
        this.mGetPlaybackResourcesServiceClient = (GetPlaybackResourcesServiceClient) Preconditions.checkNotNull(getPlaybackResourcesServiceClient, "serviceClient");
        this.mCookieStore = (PersistentAndroidCookieStore) Preconditions.checkNotNull(persistentAndroidCookieStore, "cookieStore");
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "qosCommunicationService");
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlanFactory
    public AdPlanResult newAdPlan(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nullable String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable String str2, @Nullable PlaybackResources playbackResources, @Nullable CuepointPlaylist cuepointPlaylist, @Nullable RendererSchemeType rendererSchemeType) throws MediaException {
        String value;
        Optional<CuepointPlaylist> cuepointPlaylist2;
        Preconditions.checkNotNull(videoSpecification, "VideoSpecification");
        Preconditions.checkNotNull(map, "sessionContext");
        Preconditions.checkNotNull(playbackEventReporter, "eventReporter");
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(this.mTicker);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long jElapsed = stopwatchCreateStarted.elapsed(timeUnit);
        String titleId = videoSpecification.getTitleId();
        boolean zIsTrailer = videoSpecification.isTrailer();
        TimeSpan videoDuration = getVideoDuration(playbackResources, videoSpecification);
        try {
            Stopwatch stopwatchCreateStarted2 = Stopwatch.createStarted(this.mTicker);
            long jElapsed2 = stopwatchCreateStarted2.elapsed(timeUnit);
            if (this.mAdsConfig.isSSAIEnabled()) {
                if (AdsConfig.getInstance().getAdMasterEnabled()) {
                    cuepointPlaylist2 = Optional.of(AdsConfig.getInstance().getAdMasterInjectedCuepoint());
                } else {
                    cuepointPlaylist2 = cuepointPlaylist != null ? Optional.of(cuepointPlaylist) : Optional.absent();
                }
            } else if (playbackResources == null) {
                cuepointPlaylist2 = getCuepointPlaylist(titleId, map, zIsTrailer, str2, rendererSchemeType, videoSpecification.getPlaybackToken());
            } else {
                EntitlementType entitlementTypeOrNull = playbackResources.getEntitlementType().orNull();
                value = entitlementTypeOrNull != null ? entitlementTypeOrNull.getValue() : null;
                cuepointPlaylist2 = playbackResources.getCuepointPlaylist();
                return new AdPlanResult(prepareAdPlan(cuepointPlaylist2, jElapsed2, stopwatchCreateStarted2.stop().elapsed(timeUnit), value, videoDuration, playbackEventReporter, jElapsed, stopwatchCreateStarted), cuepointPlaylist2);
            }
            value = str;
            return new AdPlanResult(prepareAdPlan(cuepointPlaylist2, jElapsed2, stopwatchCreateStarted2.stop().elapsed(timeUnit), value, videoDuration, playbackEventReporter, jElapsed, stopwatchCreateStarted), cuepointPlaylist2);
        } catch (IllegalArgumentException e) {
            AdException adException = new AdException(e);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, adException));
            throw adException;
        }
    }

    @Nullable
    private TimeSpan getVideoDuration(@Nullable PlaybackResources playbackResources, @Nullable VideoSpecification videoSpecification) {
        if (playbackResources != null && playbackResources.getAudioVideoUrls().isPresent()) {
            return TimeSpan.fromMilliseconds(playbackResources.getAudioVideoUrls().get().getContentUrls().get(0).getDurationMillis());
        }
        return videoSpecification.getDuration();
    }

    private AdPlan prepareAdPlan(Optional<CuepointPlaylist> optional, long j, long j2, @Nullable String str, @Nullable TimeSpan timeSpan, @Nonnull PlaybackEventReporter playbackEventReporter, long j3, Stopwatch stopwatch) throws MediaException {
        AdManifest adManifestCreateEmtpyAdManifest;
        boolean z;
        String str2;
        try {
            boolean z2 = false;
            if (optional.isPresent() && !Strings.isNullOrEmpty(optional.get().encodedManifest)) {
                String str3 = new String(Base64.decode(optional.get().encodedManifest.getBytes(), 0), ParserUtil.UTF_8);
                Preconditions.checkNotNull(str3, "vmapContent");
                if (this.mAdsConfig.shouldDumpVastManifestToLogs()) {
                    DLog.logHugeString("AdsManifest:", str3);
                }
                adManifestCreateEmtpyAdManifest = processVmap(str3, j, j2);
                CuepointPlaylistMetadata cuepointPlaylistMetadata = optional.get().additionalMetadata;
                z2 = cuepointPlaylistMetadata != null ? cuepointPlaylistMetadata.showCountdownTimer : false;
                z = false;
            } else {
                adManifestCreateEmtpyAdManifest = AdManifest.createEmtpyAdManifest();
                z = true;
                this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.FAILED_TO_PARSE_MANIFEST, "Ad Manifest Missing")));
            }
            AdManagerBasedAdPlan adManagerBasedAdPlanNewAdPlan = this.mAdManagerBasedFactory.newAdPlan(str, z2);
            adManagerBasedAdPlanNewAdPlan.initialize(adManifestCreateEmtpyAdManifest, timeSpan, playbackEventReporter);
            if (z) {
                str2 = "MissingManifest";
            } else {
                str2 = adManagerBasedAdPlanNewAdPlan.hasPlayableAds() ? "FullManifest" : "EmptyManifest";
            }
            ImmutableList immutableListOf = ImmutableList.of("Metric", str2);
            long jElapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
            Profiler.reportTimerMetric(new SimpleTimerMetric("GetAds-Execution", immutableListOf, j3, jElapsed));
            playbackEventReporter.reportMetric("AdEvent", str2, new TimeSpan(jElapsed), null, null);
            return adManagerBasedAdPlanNewAdPlan;
        } catch (AdParsingException | UnsupportedEncodingException | IllegalArgumentException e) {
            AdException adException = new AdException(e);
            this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, adException));
            throw adException;
        }
    }

    private Optional<CuepointPlaylist> getCuepointPlaylist(@Nonnull String str, @Nonnull Map<String, String> map, boolean z, @Nullable final String str2, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str3) throws MediaException {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(map, "sessionContext");
        PlaybackResourcesResponseListener<PlaybackResourcesWrapper> playbackResourcesResponseListener = new PlaybackResourcesResponseListener<PlaybackResourcesWrapper>() { // from class: com.amazon.avod.media.ads.internal.AdManagerBasedAdPlanFactory.1
            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPSuccess(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull Response<PlaybackResourcesWrapper> response, @Nonnull DownloadStatistics downloadStatistics) {
                AdManagerBasedAdPlanFactory.this.mQOSCommunicationService.getEventTransport().postEvent(new AdVastManifestDownloadedEvent(downloadStatistics, str2, String.valueOf(request.getUrl()), null));
            }

            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPFailure(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
                AdManagerBasedAdPlanFactory.this.mQOSCommunicationService.getEventTransport().postEvent(new AdVastManifestDownloadedEvent(downloadStatistics, str2, String.valueOf(request.getUrl()), boltException));
            }
        };
        DLog.logf("AdManagerBasedAdPlanFactory: No cuepoint playlist record found in ContentCache for titleId: %s, So making a cuepoint playlist call", str);
        return this.mGetPlaybackResourcesServiceClient.getCuepointPlaylist(str, map, z, playbackResourcesResponseListener, rendererSchemeType, str3);
    }

    AdManifest processVmap(String str, long j, long j2) throws AdParsingException {
        AdManifest adManifestCreateAdManifest;
        String str2;
        String userAgent = this.mAdsConfig.getUserAgent();
        if (this.mAdsConfig.shouldUseDeviceUserAgent()) {
            String property = System.getProperty("http.agent");
            if (!Strings.isNullOrEmpty(property)) {
                userAgent = property;
            }
        }
        HttpParameters httpParametersBuild = this.mHttpParamsBuilder.followRedirects(true).withBeaconRetryCount(this.mAdsConfig.getBeaconRetries()).withBeaconTimeout((int) this.mAdsConfig.getBeaconTimeout().getTotalMilliseconds()).withRequestRetryCount(this.mAdsConfig.getRequestRetries()).withRequestTimeout((int) this.mAdsConfig.getRequestTimeout().getTotalMilliseconds()).withUserAgent(HttpRequestBuilder.sanitizeUserAgent(userAgent)).build();
        AdHttpClient boltHttpAdClient = this.mAdsConfig.isBoltHttpClientEnabledForAds() ? new BoltHttpAdClient(httpParametersBuild) : new DefaultAdHttpClient(httpParametersBuild, this.mCookieStore);
        try {
            Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(this.mTicker);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            long jElapsed = stopwatchCreateStarted.elapsed(timeUnit);
            if (this.mAdsConfig.shouldEnableEmptyAdsManifestParsingOptimization() && !str.contains("/Ad>")) {
                adManifestCreateAdManifest = AdManifest.createEmtpyAdManifest();
                str2 = "EmptyManifest";
            } else {
                adManifestCreateAdManifest = AdManifest.createAdManifest(VastVmapParser.loadVmapDocument(str).getAdBreaks(), boltHttpAdClient);
                str2 = "FullManifest";
            }
            AdManifest adManifest = adManifestCreateAdManifest;
            String str3 = str2;
            long jElapsed2 = stopwatchCreateStarted.stop().elapsed(timeUnit);
            reportPmetMetric("GetAds-NetworkRequest", str3, j, j2);
            reportPmetMetric("GetAds-Parsing", str3, jElapsed, jElapsed2);
            return adManifest;
        } catch (Exception e) {
            throw new AdParsingException(e);
        }
    }

    private void reportPmetMetric(String str, String str2, long j, long j2) {
        Profiler.reportTimerMetric(new SimpleTimerMetric(str, ImmutableList.of("Metric", str2), j, j2));
    }
}
