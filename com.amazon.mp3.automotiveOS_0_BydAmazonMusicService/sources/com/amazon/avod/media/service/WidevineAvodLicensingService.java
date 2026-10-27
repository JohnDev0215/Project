package com.amazon.avod.media.service;

import com.amazon.atvplaybackresource.widevine.WidevineKeyMetadata;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseConstraints;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseHdcpConstraint;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseKeyIdInfo;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseMetadata;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseResource;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.drm.event.LicenseAcquisitionEventReporter;
import com.amazon.avod.drm.widevine.WidevineLicenseResponse;
import com.amazon.avod.drm.widevine.WidevineLicensingService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.ResetObserver;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.playbackresourcev2.WidevineLicenseMetadataV2;
import com.amazon.avod.playbackresourcev2.WidevineLicenseV2;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.prs.GetWidevineLicensePlayerRequest;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineAvodLicensingService implements WidevineLicensingService, ResetObserver {
    private final GetDrmLicenseResourcesServiceClient mGetDrmLicenseResourcesServiceClient;
    private final GetPlaybackResourcesServiceClient mGetPlaybackResourcesServiceClient;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;

    public WidevineAvodLicensingService(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this(new GetPlaybackResourcesServiceClient(playbackSupportEvaluator, GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest()), qOSCommunicationService), new GetDrmLicenseResourcesServiceClient(GetPlaybackResourcesFactory.createWidevineInstance(new GetWidevineLicensePlayerRequest())), PlaybackResourcesV2Config.getInstance());
    }

    WidevineAvodLicensingService(@Nonnull GetPlaybackResourcesServiceClient getPlaybackResourcesServiceClient, @Nonnull GetDrmLicenseResourcesServiceClient getDrmLicenseResourcesServiceClient, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config) {
        this.mGetPlaybackResourcesServiceClient = (GetPlaybackResourcesServiceClient) Preconditions.checkNotNull(getPlaybackResourcesServiceClient, "getPlaybackResourcesServiceClient");
        this.mGetDrmLicenseResourcesServiceClient = (GetDrmLicenseResourcesServiceClient) Preconditions.checkNotNull(getDrmLicenseResourcesServiceClient, "getDrmLicenseResourcesServiceClient");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
    }

    @Nonnull
    private synchronized String getLicenseChallenge(@Nonnull DrmFramework drmFramework, @Nonnull String str, boolean z) throws DrmLicensingException {
        return Base64.encodeBytes(drmFramework.generateLicenseChallenge(str, DrmScheme.WIDEVINE, !z));
    }

    public synchronized void resetState() {
        DLog.logf("Widevine licensing service does not support challenge replay");
    }

    @Override // com.amazon.avod.drm.widevine.WidevineLicensingService
    @Nonnull
    public WidevineLicenseResponse getLicenseResponse(@Nonnull DrmFramework drmFramework, @Nonnull String str, @Nonnull String str2, @Nonnull ConsumptionType consumptionType, @Nullable final String str3, @Nonnull ContentType contentType, boolean z, @Nonnull RendererSchemeType rendererSchemeType, final boolean z2, @Nullable String str4, boolean z3, @Nonnull final LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, @Nonnull Map<String, String> map, @Nullable PlaybackEnvelope playbackEnvelope) throws DrmLicensingException {
        String licenseChallenge = getLicenseChallenge(drmFramework, str2, z2);
        PlaybackResourcesResponseListener<PlaybackResourcesWrapper> playbackResourcesResponseListener = new PlaybackResourcesResponseListener<PlaybackResourcesWrapper>() { // from class: com.amazon.avod.media.service.WidevineAvodLicensingService.1
            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPSuccess(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull Response<PlaybackResourcesWrapper> response, @Nonnull DownloadStatistics downloadStatistics) {
                licenseAcquisitionEventReporter.reportQOS2LicenseAcquisition(true, downloadStatistics, 200, z2, str3, request.getUrl().toString());
            }

            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPFailure(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
                licenseAcquisitionEventReporter.reportQOS2LicenseAcquisition(false, downloadStatistics, 500, z2, str3, request.getUrl().toString());
            }
        };
        if (this.mPlaybackResourcesV2Config.shouldCallPrsV2Service(playbackEnvelope, contentType)) {
            String envelope = playbackEnvelope.getEnvelope();
            if (envelope == null) {
                throw new DrmLicensingException(LicenseError.AIV_MISSING_PLAYBACK_ENVELOPE);
            }
            return getParsedResponse(this.mGetDrmLicenseResourcesServiceClient.getWidevineLicense(licenseChallenge, map, null, envelope, str, consumptionType));
        }
        return getParsedResponse(this.mGetPlaybackResourcesServiceClient.getWidevineLicense(str, licenseChallenge, z, false, consumptionType, contentType, playbackResourcesResponseListener, rendererSchemeType, str4, z3, map));
    }

    @Nonnull
    WidevineLicenseResponse getParsedResponse(@Nonnull WidevineLicenseResource widevineLicenseResource) throws DrmLicensingException {
        String strEncodeBytes = Base64.encodeBytes(getLicenseBytes(widevineLicenseResource.license));
        if (Strings.isNullOrEmpty(strEncodeBytes)) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but did not return a license response");
        }
        WidevineLicenseMetadata widevineLicenseMetadata = widevineLicenseResource.metadata;
        if (widevineLicenseMetadata == null) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but did not return license metadata");
        }
        WidevineLicenseKeyIdInfo widevineLicenseKeyIdInfo = widevineLicenseMetadata.keyIds;
        if (widevineLicenseKeyIdInfo == null) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but license metadata does not have keyIds element");
        }
        ImmutableList<String> immutableList = widevineLicenseKeyIdInfo.contentKeyIds;
        if (immutableList == null) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but license metadata contentKeyIds array is null");
        }
        if (immutableList.size() < 1) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but license metadata contentKeyIds array is empty");
        }
        UnmodifiableIterator<String> it = immutableList.iterator();
        while (it.hasNext()) {
            if (Strings.isNullOrEmpty(it.next())) {
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but the returned content keyId is null or empty");
            }
        }
        WidevineLicenseResponse.Builder builderNewBuilder = WidevineLicenseResponse.newBuilder(strEncodeBytes, ImmutableList.copyOf((Collection) immutableList));
        Optional<WidevineLicenseConstraints> optional = widevineLicenseMetadata.constraints;
        if (optional.isPresent()) {
            Optional<WidevineLicenseHdcpConstraint> optional2 = optional.get().hdcp;
            if (optional2.isPresent()) {
                String value = optional2.get().hdcp.getValue();
                if (Strings.isNullOrEmpty(value)) {
                    throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but did not contain HDCP version");
                }
                builderNewBuilder.setHdcpVersion(value);
                builderNewBuilder.setHdcpEnforcementResolutionPixels(optional2.get().hdcpEnforcementResolutionPixels.or(-1).intValue());
            }
        }
        builderNewBuilder.setHdcpTestKeyId(widevineLicenseKeyIdInfo.hdcpTestKeyId.orNull());
        return builderNewBuilder.build();
    }

    @Nonnull
    private WidevineLicenseResponse getParsedResponse(@Nonnull WidevineLicenseV2 widevineLicenseV2) throws DrmLicensingException {
        String strEncodeBytes = Base64.encodeBytes(getLicenseBytes(widevineLicenseV2.getLicense()));
        if (Strings.isNullOrEmpty(strEncodeBytes)) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but did not return a license response");
        }
        WidevineLicenseMetadataV2 metadata = widevineLicenseV2.getMetadata();
        if (metadata == null) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but did not return license metadata");
        }
        ImmutableList<WidevineKeyMetadata> keyMetadata = metadata.getKeyMetadata();
        ArrayList arrayList = new ArrayList();
        for (WidevineKeyMetadata widevineKeyMetadata : keyMetadata) {
            if (widevineKeyMetadata.keyType.getValue().equals("CONTENT")) {
                String str = widevineKeyMetadata.id;
                if (Strings.isNullOrEmpty(str)) {
                    throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but the returned content keyId is null or empty");
                }
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetWidevineLicense succeeded but license metadata contentKeyIds array is empty");
        }
        return WidevineLicenseResponse.newBuilder(strEncodeBytes, ImmutableList.copyOf((Collection) arrayList)).build();
    }

    private byte[] getLicenseBytes(@Nonnull ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer, "licenseBuffer");
        if (byteBuffer.hasArray()) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
        resetState();
    }
}
