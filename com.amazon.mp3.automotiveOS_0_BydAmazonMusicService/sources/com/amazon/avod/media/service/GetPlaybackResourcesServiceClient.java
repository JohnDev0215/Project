package com.amazon.avod.media.service;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PlayReadyLicense;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseResource;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.core.AVODRemoteException;
import com.amazon.avod.drm.playready.PlayReadyContentIdentifier;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.NoopAdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.event.playback.AdVastManifestDownloadedEvent;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.playbackresource.PlaybackResourceConfig;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.playbackresource.PlaybackSettingsTransformer;
import com.amazon.avod.prs.AdditionalParams;
import com.amazon.avod.prs.GetPlaybackResources;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesServiceClient {
    public static final ImmutableMap<String, ErrorCodeWrapper> EXPECTED_CONTENT_ERRORS = ImmutableMap.builder().put("PRS.NoRights.NoAvailableStreams", new ErrorCodeWrapper(ContentException.ContentError.NO_AVAILABLE_ONLINE_STREAMS, LicenseError.AIV_LICENSE_SERVICE_CALL_NO_AVAILABLE_ONLINE_STREAMS)).put("PRS.CacheLoadShed", new ErrorCodeWrapper(ContentException.ContentError.SERVICE_CALL_LOAD_SHED, LicenseError.AIV_LICENSE_SERVICE_CALL_LOAD_SHED)).put("PRS.NoRights.InvalidGeoIP", new ErrorCodeWrapper(ContentException.ContentError.INVALID_GEO_IP, LicenseError.AIV_LICENSE_SERVICE_CALL_INVALID_GEO_IP)).put("PRS.Dependency", new ErrorCodeWrapper(ContentException.ContentError.TEMPORARILY_UNAVAILABLE, LicenseError.AIV_LICENSE_SERVICE_CALL_TEMPORARILY_UNAVAILABLE)).put("PRS.NoRights.DownloadLimitExceeded", new ErrorCodeWrapper(ContentException.ContentError.SERVICE_ERROR, ContentException.ContentError.NO_AVAILABLE_DOWNLOAD_RIGHTS, LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, LicenseError.AIV_LICENSE_SERVICE_CALL_NO_AVAILABLE_DOWNLOAD_RIGHTS)).put("PRS.NoRights.NotOwned", new ErrorCodeWrapper(ContentException.ContentError.STREAMING_NOT_OWNED, ContentException.ContentError.DOWNLOAD_NOT_OWNED, LicenseError.AIV_LICENSE_SERVICE_CALL_STREAMING_NOT_OWNED, LicenseError.AIV_LICENSE_SERVICE_CALL_DOWNLOAD_NOT_OWNED)).put("PRS.NoRights.AnonymizerIP", new ErrorCodeWrapper(ContentException.ContentError.HTTP_PROXY_ERROR, LicenseError.AIV_LICENSE_HTTP_PROXY_ERROR)).put("PRS.NoRights.Blackouts", new ErrorCodeWrapper(ContentException.ContentError.GAME_BLACKOUT, LicenseError.AIV_LICENSE_SERVICE_CALL_GAME_BLACKOUT)).put("PRS.NoRights.PVLowerTierConcurrencyLimitUpgrade", new ErrorCodeWrapper(ContentException.ContentError.LOWER_TIER_CONCURRENCY_LIMIT, LicenseError.AIV_LICENSE_SERVICE_CALL_LOWER_TIER_CONCURRENCY_LIMIT)).put("PRS.NoRights.PVLowerTierDeviceNotPermittedAccess", new ErrorCodeWrapper(ContentException.ContentError.LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED, LicenseError.AIV_LICENSE_SERVICE_CALL_LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED)).build();
    public static final PlaybackResourcesResponseListener<?> NULL_PRS_RESPONSE_LISTENER = null;
    private final AVODServiceConfig mAVODServiceConfig;
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final long mAdvertisingIdCollectorTimeoutInMillis;
    private final GetPlaybackResources mGetPlaybackResources;
    private final IdentityShim mIdentity;
    private final boolean mIsSSAIEnabledForDownloads;
    private final boolean mIsSSAIEnabledForStreaming;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final QOSCommunicationService mQOSCommunicationService;
    private final boolean mShouldAdvertisingIdCollectorUseStaleData;
    private final boolean mShouldSuppressCuepointsForChildRole;

    @Nonnull
    public static ContentException.ContentError toContentError(@Nullable String str) {
        if (str != null) {
            ImmutableMap<String, ErrorCodeWrapper> immutableMap = EXPECTED_CONTENT_ERRORS;
            if (immutableMap.get(str) != null) {
                return immutableMap.get(str).getContentError(ConsumptionType.Streaming);
            }
        }
        return ContentException.ContentError.SERVICE_ERROR;
    }

    public GetPlaybackResourcesServiceClient(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull GetPlaybackResources getPlaybackResources, @Nullable QOSCommunicationService qOSCommunicationService) {
        this(AVODServiceConfig.getInstance(), playbackSupportEvaluator, new NoopAdvertisingIdCollector(), AVODServiceConfig.getInstance().isAdvertisingIdCollectorUseStaleData(), AVODServiceConfig.getInstance().getAdvertisingIdCollectorTimeoutInMillis(), getPlaybackResources, PlaybackResourceConfig.getInstance(), qOSCommunicationService, AdsConfig.getInstance().isSSAIEnabled(), AdsConfig.getInstance().isSSAIEnabledForDownloads(), AdsConfig.getInstance().shouldSuppressCuepointsForChildRole(), MediaSystemSharedDependencies.getInstance().getIdentityShim());
    }

    public GetPlaybackResourcesServiceClient(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nullable QOSCommunicationService qOSCommunicationService) {
        this(AVODServiceConfig.getInstance(), playbackSupportEvaluator, advertisingIdCollector, AVODServiceConfig.getInstance().isAdvertisingIdCollectorUseStaleData(), AVODServiceConfig.getInstance().getAdvertisingIdCollectorTimeoutInMillis(), GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest()), PlaybackResourceConfig.getInstance(), qOSCommunicationService, AdsConfig.getInstance().isSSAIEnabled(), AdsConfig.getInstance().isSSAIEnabledForDownloads(), AdsConfig.getInstance().shouldSuppressCuepointsForChildRole(), MediaSystemSharedDependencies.getInstance().getIdentityShim());
    }

    GetPlaybackResourcesServiceClient(@Nonnull AVODServiceConfig aVODServiceConfig, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull AdvertisingIdCollector advertisingIdCollector, boolean z, long j, @Nonnull GetPlaybackResources getPlaybackResources, @Nonnull PlaybackResourceConfig playbackResourceConfig, @Nullable QOSCommunicationService qOSCommunicationService, boolean z2, boolean z3, boolean z4, @Nonnull IdentityShim identityShim) {
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mGetPlaybackResources = (GetPlaybackResources) Preconditions.checkNotNull(getPlaybackResources, "getPlaybackResources");
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mAdvertisingIdCollectorTimeoutInMillis = j;
        this.mShouldAdvertisingIdCollectorUseStaleData = z;
        Preconditions.checkNotNull(playbackResourceConfig, "playbackResourceConfig");
        this.mQOSCommunicationService = qOSCommunicationService;
        this.mIsSSAIEnabledForStreaming = z2;
        this.mIsSSAIEnabledForDownloads = z3;
        this.mShouldSuppressCuepointsForChildRole = z4;
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identityShim");
    }

    @Nonnull
    public AudioVideoUrls getUrls(@Nonnull String str, @Nonnull Optional<String> optional, boolean z, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Map<String, String> map, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nonnull PlaybackEventReporter playbackEventReporter, boolean z2, @Nullable PlaybackResourcesResponseListener<?> playbackResourcesResponseListener, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nullable PlaybackSettings playbackSettings, boolean z3, @Nonnull EPrivacyConsentData ePrivacyConsentData) throws ContentException {
        ImmutableSet immutableSetOf;
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(optional, "audioTrackId");
        Preconditions.checkNotNull(consumptionType, "consumptionType");
        Preconditions.checkNotNull(contentType, "contentType");
        Preconditions.checkNotNull(map, "sessionContext");
        Preconditions.checkNotNull(optional2, "userWatchSessionId");
        Preconditions.checkNotNull(optional3, "version");
        Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        if (shouldMakeCombinedCall(consumptionType, z3)) {
            return getPlaybackResources(str, optional, z, consumptionType, contentType, map, optional2, optional3, playbackEventReporter, z2, playbackResourcesResponseListener, rendererSchemeType, str2, playbackSettings, z3, ePrivacyConsentData);
        }
        PlaybackUrlsInformation playbackUrlsInformation = new PlaybackUrlsInformation(str, optional, consumptionType, contentType, optional2, optional3, this.mAdvertisingIdCollector, playbackEventReporter, z2, this.mPlaybackSupportEvaluator, rendererSchemeType, str2, z3, ePrivacyConsentData);
        VideoMaterialType videoMaterialTypeFromPlayersContentType = VideoMaterialTypeUtils.fromPlayersContentType(contentType);
        com.amazon.atvplaybackdevice.types.ConsumptionType consumptionTypeForValue = com.amazon.atvplaybackdevice.types.ConsumptionType.forValue(consumptionType.name());
        ResourceUsage resourceUsage = z ? ResourceUsage.CacheResources : ResourceUsage.ImmediateConsumption;
        if (playbackSettings != null) {
            immutableSetOf = ImmutableSet.of(Resource.PlaybackUrls);
        } else {
            immutableSetOf = ImmutableSet.of(Resource.PlaybackUrls, Resource.PlaybackSettings);
        }
        return getAudioVideoUrlsFromPlaybackResourcesWrapper(str, videoMaterialTypeFromPlayersContentType, consumptionTypeForValue, resourceUsage, immutableSetOf, playbackUrlsInformation, playbackResourcesResponseListener, consumptionType, z, "Failed to get Content urls", map, playbackSettings);
    }

    private boolean shouldMakeCombinedCall(@Nonnull ConsumptionType consumptionType, boolean z) {
        if (this.mShouldSuppressCuepointsForChildRole && this.mIdentity.isChild()) {
            DLog.logf("ServerInsertedPlaybackSession: Skipping combined call for child profile");
            return false;
        }
        if (!z) {
            DLog.logf("ServerInsertedPlaybackSession: Skipping combined call to suppress ads");
            return false;
        }
        int i = AnonymousClass2.$SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[consumptionType.ordinal()];
        if (i == 1) {
            return this.mIsSSAIEnabledForDownloads;
        }
        if (i == 2) {
            return this.mIsSSAIEnabledForStreaming;
        }
        DLog.logf("Unrecognized consumption type: %s", consumptionType);
        return false;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.GetPlaybackResourcesServiceClient$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType;

        static {
            int[] iArr = new int[ConsumptionType.values().length];
            $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType = iArr;
            try {
                iArr[ConsumptionType.Download.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[ConsumptionType.Streaming.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Nonnull
    private AudioVideoUrls getPlaybackResources(@Nonnull String str, @Nonnull Optional<String> optional, boolean z, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Map<String, String> map, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nonnull final PlaybackEventReporter playbackEventReporter, boolean z2, @Nullable PlaybackResourcesResponseListener<?> playbackResourcesResponseListener, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nullable PlaybackSettings playbackSettings, boolean z3, @Nonnull EPrivacyConsentData ePrivacyConsentData) throws ContentException {
        ImmutableSet immutableSetOf;
        if (playbackSettings != null) {
            immutableSetOf = ImmutableSet.of(Resource.PlaybackUrls, Resource.CuepointPlaylist);
        } else {
            immutableSetOf = ImmutableSet.of(Resource.PlaybackUrls, Resource.PlaybackSettings, Resource.CuepointPlaylist);
        }
        ImmutableSet immutableSet = immutableSetOf;
        return getAudioVideoUrlsFromPlaybackResourcesWrapper(str, VideoMaterialTypeUtils.fromPlayersContentType(contentType), com.amazon.atvplaybackdevice.types.ConsumptionType.forValue(consumptionType.name()), z ? ResourceUsage.CacheResources : ResourceUsage.ImmediateConsumption, immutableSet, new PlayerResourcesAndParams(str, optional, consumptionType, contentType, optional2, optional3, playbackEventReporter, z2, this.mPlaybackSupportEvaluator, rendererSchemeType, str2, ePrivacyConsentData, immutableSet, z3), playbackResourcesResponseListener != null ? playbackResourcesResponseListener : new PlaybackResourcesResponseListener<PlaybackResourcesWrapper>() { // from class: com.amazon.avod.media.service.GetPlaybackResourcesServiceClient.1
            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPSuccess(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull Response<PlaybackResourcesWrapper> response, @Nonnull DownloadStatistics downloadStatistics) {
                if (GetPlaybackResourcesServiceClient.this.mQOSCommunicationService != null) {
                    GetPlaybackResourcesServiceClient.this.mQOSCommunicationService.getEventTransport().postEvent(new AdVastManifestDownloadedEvent(downloadStatistics, null, String.valueOf(request.getUrl()), null));
                }
                playbackEventReporter.reportMetric(QOSEventName.Information.toString(), "CombinedPlaybackResourceRequest", null, null, null);
            }

            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPFailure(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
                if (GetPlaybackResourcesServiceClient.this.mQOSCommunicationService != null) {
                    GetPlaybackResourcesServiceClient.this.mQOSCommunicationService.getEventTransport().postEvent(new AdVastManifestDownloadedEvent(downloadStatistics, null, String.valueOf(request.getUrl()), boltException));
                }
                playbackEventReporter.reportMetric(QOSEventName.Information.toString(), "CombinedPlaybackResourceRequest", null, null, null);
            }
        }, consumptionType, z, "Failed to get playback resources (PlaybackUrls, PlaybackSettings, CuePoint playlist", map, playbackSettings);
    }

    @Nonnull
    private AudioVideoUrls getAudioVideoUrlsFromPlaybackResourcesWrapper(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull com.amazon.atvplaybackdevice.types.ConsumptionType consumptionType, @Nonnull ResourceUsage resourceUsage, @Nonnull Set<Resource> set, @Nonnull AdditionalParams additionalParams, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull ConsumptionType consumptionType2, boolean z, @Nonnull String str2, @Nonnull Map<String, String> map, @Nullable PlaybackSettings playbackSettings) throws ContentException {
        QATriggerPRSError.getInstance().triggerPRSErrorIfEnabled();
        try {
            PlaybackResourcesWrapper playbackResourcesWrapper = this.mGetPlaybackResources.get(str, videoMaterialType, consumptionType, resourceUsage, set, additionalParams, map, playbackResourcesResponseListener);
            Optional<PlaybackResources> playbackResources = playbackResourcesWrapper.getPlaybackResources();
            if (playbackResources.isPresent()) {
                PlaybackResources playbackResources2 = playbackResources.get();
                Optional<AudioVideoUrls> audioVideoUrls = playbackResources2.getAudioVideoUrls();
                if (audioVideoUrls.isPresent()) {
                    if (playbackSettings != null) {
                        return new AudioVideoUrlsBuilder(audioVideoUrls.get()).setPlaybackSettingsOverride(new PlaybackSettingsTransformer().transform(playbackSettings)).build();
                    }
                    return audioVideoUrls.get();
                }
                ImmutableMap<Resource, PRSException> errorsByResource = playbackResources2.getErrorsByResource();
                Resource resource = Resource.PlaybackUrls;
                if (errorsByResource.containsKey(resource)) {
                    throw toContentException(consumptionType2, errorsByResource.get(resource), z);
                }
                Resource resource2 = Resource.PlaybackSettings;
                if (errorsByResource.containsKey(resource2)) {
                    throw toContentException(consumptionType2, errorsByResource.get(resource2), z);
                }
                Resource resource3 = Resource.CuepointPlaylist;
                if (errorsByResource.containsKey(resource3)) {
                    throw toContentException(consumptionType2, errorsByResource.get(resource3), z);
                }
                throw new ContentException(ContentException.ContentError.SERVICE_ERROR, str2, z);
            }
            if (playbackResourcesWrapper.getError().isPresent() && playbackResourcesWrapper.getError().get().errorCode.isPresent() && playbackResourcesWrapper.getError().get().message.isPresent()) {
                throw toContentException(consumptionType2, playbackResourcesWrapper.getError().get(), z);
            }
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, str2, z);
        } catch (BoltException e) {
            Throwable componentCause = e.getComponentCause();
            if (componentCause instanceof AVODRemoteException) {
                throw toContentException((AVODRemoteException) componentCause, consumptionType2);
            }
            throw new ContentException(ContentException.ContentError.NETWORK_ERROR, str2);
        }
    }

    @Nonnull
    public Optional<CuepointPlaylist> getCuepointPlaylist(@Nonnull String str, @Nonnull Map<String, String> map, boolean z, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2) throws MediaException {
        if (AdsConfig.getInstance().getAdMasterEnabled()) {
            return Optional.of(AdsConfig.getInstance().getAdMasterInjectedCuepoint());
        }
        try {
            AdvertisingIdInformation advertisingIdInformation = new AdvertisingIdInformation(this.mAdvertisingIdCollector, this.mShouldAdvertisingIdCollectorUseStaleData, this.mAdvertisingIdCollectorTimeoutInMillis, this.mAVODServiceConfig.isVideoFrameRateOverrideEnabled() && (this.mPlaybackSupportEvaluator.isUhdSupported(rendererSchemeType) || this.mPlaybackSupportEvaluator.isHdrSupported(rendererSchemeType) || this.mPlaybackSupportEvaluator.isHdSupported(rendererSchemeType)), str2);
            GetPlaybackResources getPlaybackResources = this.mGetPlaybackResources;
            VideoMaterialType videoMaterialType = z ? VideoMaterialType.Trailer : VideoMaterialType.Feature;
            com.amazon.atvplaybackdevice.types.ConsumptionType consumptionType = com.amazon.atvplaybackdevice.types.ConsumptionType.Streaming;
            ResourceUsage resourceUsage = ResourceUsage.ImmediateConsumption;
            Resource resource = Resource.CuepointPlaylist;
            PlaybackResourcesWrapper playbackResourcesWrapper = getPlaybackResources.get(str, videoMaterialType, consumptionType, resourceUsage, ImmutableSet.of(resource), advertisingIdInformation, map, playbackResourcesResponseListener);
            Optional<PlaybackResources> playbackResources = playbackResourcesWrapper.getPlaybackResources();
            if (playbackResources.isPresent()) {
                Optional<CuepointPlaylist> cuepointPlaylist = playbackResources.get().getCuepointPlaylist();
                if (cuepointPlaylist.isPresent()) {
                    return cuepointPlaylist;
                }
                ImmutableMap<Resource, PRSException> errorsByResource = playbackResources.get().getErrorsByResource();
                if (errorsByResource.containsKey(resource)) {
                    throw toContentException(ConsumptionType.Streaming, errorsByResource.get(resource));
                }
                return cuepointPlaylist;
            }
            if (playbackResourcesWrapper.getError().isPresent()) {
                throw toContentException(ConsumptionType.Streaming, playbackResourcesWrapper.getError().get());
            }
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "Failed to get CuepointPlaylist");
        } catch (BoltException e) {
            Throwable componentCause = e.getComponentCause();
            if (componentCause instanceof AVODRemoteException) {
                throw toContentException((AVODRemoteException) componentCause, ConsumptionType.Streaming);
            }
            throw new ContentException(ContentException.ContentError.NETWORK_ERROR, "Failed to get cuepoint playlist");
        }
    }

    @Nonnull
    public PlayReadyLicense getPlayReadyLicense(@Nonnull PlayReadyContentIdentifier playReadyContentIdentifier, @Nonnull String str, boolean z, boolean z2, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, boolean z3, @Nonnull PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str2, boolean z4, @Nonnull Map<String, String> map) throws DrmLicensingException {
        Preconditions.checkNotNull(playReadyContentIdentifier, "contentIdentifier");
        Preconditions.checkNotNull(str, "base64EncodedChallenge");
        Preconditions.checkNotNull(consumptionType, "consumptionType");
        Preconditions.checkNotNull(contentType, "contentType");
        Preconditions.checkNotNull(playbackResourcesResponseListener, "responseListener");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        Preconditions.checkNotNull(map, "sessionContext");
        String titleId = playReadyContentIdentifier.getTitleId();
        PlaybackSupportEvaluator playbackSupportEvaluator = this.mPlaybackSupportEvaluator;
        Resource resource = Resource.PlayReadyLicense;
        LicenseRequestResourcesAndParams licenseRequestResourcesAndParams = new LicenseRequestResourcesAndParams(titleId, consumptionType, contentType, playbackSupportEvaluator, rendererSchemeType, str, resource, str2, playReadyContentIdentifier.getKeyId(), z3, z4, z);
        try {
            PlaybackResourcesWrapper playbackResourcesWrapper = this.mGetPlaybackResources.get(playReadyContentIdentifier.getTitleId(), VideoMaterialTypeUtils.fromPlayersContentType(contentType), com.amazon.atvplaybackdevice.types.ConsumptionType.forValue(consumptionType.name()), z2 ? ResourceUsage.CacheResources : ResourceUsage.ImmediateConsumption, licenseRequestResourcesAndParams.getDesiredResourcesSet(), licenseRequestResourcesAndParams, map, playbackResourcesResponseListener);
            Optional<PlaybackResources> playbackResources = playbackResourcesWrapper.getPlaybackResources();
            if (playbackResources.isPresent()) {
                Optional<PlayReadyLicense> playReadyLicense = playbackResources.get().getPlayReadyLicense();
                if (playReadyLicense.isPresent()) {
                    return playReadyLicense.get();
                }
                ImmutableMap<Resource, PRSException> errorsByResource = playbackResources.get().getErrorsByResource();
                if (errorsByResource.containsKey(resource)) {
                    throw toDrmLicensingException(consumptionType, errorsByResource.get(resource), DrmScheme.PLAYREADY);
                }
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "Null PlayReadyLicense response from server", DrmScheme.PLAYREADY);
            }
            if (playbackResourcesWrapper.getError().isPresent()) {
                throw toDrmLicensingException(consumptionType, playbackResourcesWrapper.getError().get(), DrmScheme.PLAYREADY);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, "Failed to fetch PlayReadyLicense", DrmScheme.PLAYREADY);
        } catch (BoltException e) {
            Throwable componentCause = e.getComponentCause();
            if (componentCause instanceof AVODRemoteException) {
                throw toDrmLicensingException((AVODRemoteException) componentCause, consumptionType, DrmScheme.PLAYREADY);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_NETWORK_ERROR, "Exception requesting PlayReadyLicense", e, DrmScheme.PLAYREADY);
        }
    }

    @Nonnull
    public WidevineLicenseResource getWidevineLicense(@Nonnull String str, @Nonnull String str2, boolean z, boolean z2, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str3, boolean z3, @Nonnull Map<String, String> map) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(str2, "base64EncodedChallenge");
        Preconditions.checkNotNull(consumptionType, "consumptionType");
        Preconditions.checkNotNull(contentType, "contentType");
        Preconditions.checkNotNull(playbackResourcesResponseListener, "responseListener");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        Preconditions.checkNotNull(map, "sessionContext");
        PlaybackSupportEvaluator playbackSupportEvaluator = this.mPlaybackSupportEvaluator;
        Resource resource = Resource.Widevine2License;
        LicenseRequestResourcesAndParams licenseRequestResourcesAndParams = new LicenseRequestResourcesAndParams(str, consumptionType, contentType, playbackSupportEvaluator, rendererSchemeType, str2, resource, str3, null, true, z3, z);
        try {
            PlaybackResourcesWrapper playbackResourcesWrapper = this.mGetPlaybackResources.get(str, VideoMaterialTypeUtils.fromPlayersContentType(contentType), com.amazon.atvplaybackdevice.types.ConsumptionType.forValue(consumptionType.name()), z2 ? ResourceUsage.CacheResources : ResourceUsage.ImmediateConsumption, licenseRequestResourcesAndParams.getDesiredResourcesSet(), licenseRequestResourcesAndParams, map, playbackResourcesResponseListener);
            Optional<PlaybackResources> playbackResources = playbackResourcesWrapper.getPlaybackResources();
            if (playbackResources.isPresent()) {
                Optional<WidevineLicenseResource> widevineLicenseResource = playbackResources.get().getWidevineLicenseResource();
                if (widevineLicenseResource.isPresent()) {
                    return widevineLicenseResource.get();
                }
                ImmutableMap<Resource, PRSException> errorsByResource = playbackResources.get().getErrorsByResource();
                if (errorsByResource.containsKey(resource)) {
                    throw toDrmLicensingException(consumptionType, errorsByResource.get(resource), DrmScheme.WIDEVINE);
                }
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "Null WidevineLicense response from server", DrmScheme.WIDEVINE);
            }
            if (playbackResourcesWrapper.getError().isPresent()) {
                throw toDrmLicensingException(consumptionType, playbackResourcesWrapper.getError().get(), DrmScheme.WIDEVINE);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, "Failed to fetch WidevineLicense", DrmScheme.WIDEVINE);
        } catch (BoltException e) {
            Throwable componentCause = e.getComponentCause();
            if (componentCause instanceof AVODRemoteException) {
                throw toDrmLicensingException((AVODRemoteException) componentCause, consumptionType, DrmScheme.WIDEVINE);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_NETWORK_ERROR, "Exception requesting WidevineLicense", e, DrmScheme.WIDEVINE);
        }
    }

    @Nonnull
    private ContentException toContentException(@Nonnull ConsumptionType consumptionType, @Nonnull PRSException pRSException) {
        if (pRSException.errorCode.isPresent()) {
            String str = pRSException.errorCode.get();
            String strOr = pRSException.message.or(str);
            ErrorCodeWrapper errorCodeWrapper = EXPECTED_CONTENT_ERRORS.get(str);
            if (errorCodeWrapper != null) {
                return new ContentException(errorCodeWrapper.getContentError(consumptionType), strOr);
            }
        }
        return new ContentException(ContentException.ContentError.SERVICE_ERROR);
    }

    @Nonnull
    static ContentException toContentException(@Nonnull AVODRemoteException aVODRemoteException, @Nonnull ConsumptionType consumptionType) {
        String errorCode = aVODRemoteException.getErrorCode();
        ErrorCodeWrapper errorCodeWrapper = EXPECTED_CONTENT_ERRORS.get(errorCode);
        if (errorCodeWrapper != null) {
            return new ContentException(errorCodeWrapper.getContentError(consumptionType), errorCode, aVODRemoteException, aVODRemoteException.getUrl());
        }
        return new ContentException(ContentException.ContentError.SERVICE_ERROR, errorCode, aVODRemoteException, aVODRemoteException.getUrl());
    }

    @Nonnull
    private ContentException toContentException(@Nonnull ConsumptionType consumptionType, @Nonnull PRSException pRSException, boolean z) {
        if (pRSException.errorCode.isPresent()) {
            String str = pRSException.errorCode.get();
            String strOr = pRSException.message.or(str);
            ErrorCodeWrapper errorCodeWrapper = EXPECTED_CONTENT_ERRORS.get(str);
            if (errorCodeWrapper != null) {
                return new ContentException(errorCodeWrapper.getContentError(consumptionType), strOr, z);
            }
        }
        return new ContentException(ContentException.ContentError.SERVICE_ERROR, z);
    }

    @Nonnull
    public static DrmLicensingException toDrmLicensingException(@Nonnull ConsumptionType consumptionType, @Nonnull PRSException pRSException, @Nonnull DrmScheme drmScheme) {
        if (pRSException.errorCode.isPresent()) {
            String str = pRSException.errorCode.get();
            String strOr = pRSException.message.or(str);
            ErrorCodeWrapper errorCodeWrapper = EXPECTED_CONTENT_ERRORS.get(str);
            if (errorCodeWrapper != null) {
                return new DrmLicensingException(errorCodeWrapper.getLicenseError(consumptionType), strOr, drmScheme);
            }
            return new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, str, drmScheme);
        }
        return new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, "Unknown service error fetching license", drmScheme);
    }

    @Nonnull
    static DrmLicensingException toDrmLicensingException(@Nonnull AVODRemoteException aVODRemoteException, @Nonnull ConsumptionType consumptionType, @Nonnull DrmScheme drmScheme) {
        String errorCode = aVODRemoteException.getErrorCode();
        ErrorCodeWrapper errorCodeWrapper = EXPECTED_CONTENT_ERRORS.get(errorCode);
        if (errorCodeWrapper != null) {
            return new DrmLicensingException(errorCodeWrapper.getLicenseError(consumptionType), errorCode, aVODRemoteException, drmScheme, aVODRemoteException.getUrl());
        }
        return new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, errorCode, aVODRemoteException, drmScheme, aVODRemoteException.getUrl());
    }

    public static class ErrorCodeWrapper {
        private final ContentException.ContentError mDownloadContentError;
        private final LicenseError mDownloadLicenseError;
        private final ContentException.ContentError mStreamingContentError;
        private final LicenseError mStreamingLicenseError;

        ErrorCodeWrapper(@Nonnull ContentException.ContentError contentError, @Nonnull LicenseError licenseError) {
            this(contentError, contentError, licenseError, licenseError);
        }

        ErrorCodeWrapper(@Nonnull ContentException.ContentError contentError, @Nonnull ContentException.ContentError contentError2, @Nonnull LicenseError licenseError, @Nonnull LicenseError licenseError2) {
            this.mStreamingContentError = (ContentException.ContentError) Preconditions.checkNotNull(contentError, "streamingContentError");
            this.mDownloadContentError = (ContentException.ContentError) Preconditions.checkNotNull(contentError2, "downloadContentError");
            this.mStreamingLicenseError = (LicenseError) Preconditions.checkNotNull(licenseError, "streamingLicenseError");
            this.mDownloadLicenseError = (LicenseError) Preconditions.checkNotNull(licenseError2, "downloadLicenseError");
        }

        @Nonnull
        public ContentException.ContentError getContentError(@Nonnull ConsumptionType consumptionType) {
            return consumptionType == ConsumptionType.Streaming ? this.mStreamingContentError : this.mDownloadContentError;
        }

        @Nonnull
        LicenseError getLicenseError(@Nonnull ConsumptionType consumptionType) {
            return consumptionType == ConsumptionType.Streaming ? this.mStreamingLicenseError : this.mDownloadLicenseError;
        }
    }
}
