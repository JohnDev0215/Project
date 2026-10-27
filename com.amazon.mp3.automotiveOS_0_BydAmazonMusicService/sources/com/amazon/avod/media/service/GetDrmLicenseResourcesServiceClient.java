package com.amazon.avod.media.service;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.drm.playready.PlayReadyContentIdentifier;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.service.drmv2.PlayReadyLicenseParamsCreator;
import com.amazon.avod.media.service.drmv2.WidevineLicenseParamsCreator;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playbackresourcev2.PlayReadyLicenseV2;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.avod.playbackresourcev2.Prsv2Error;
import com.amazon.avod.playbackresourcev2.WidevineLicenseV2;
import com.amazon.avod.prs.GetDrmLicenseResources;
import com.amazon.avod.prs.PlayReadyLicenseRequest;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.prs.WidevineLicenseRequest;
import com.amazon.bolthttp.BoltException;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class GetDrmLicenseResourcesServiceClient {
    private final GetDrmLicenseResources mGetDrmLicenseResources;

    public GetDrmLicenseResourcesServiceClient(@Nonnull GetDrmLicenseResources getDrmLicenseResources) {
        this.mGetDrmLicenseResources = (GetDrmLicenseResources) Preconditions.checkNotNull(getDrmLicenseResources, "getDrmLicenseResources");
    }

    @Nonnull
    public PlayReadyLicenseV2 getPlayReadyLicense(@Nonnull PlayReadyContentIdentifier playReadyContentIdentifier, @Nonnull String str, boolean z, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull String str2, @Nonnull ConsumptionType consumptionType) throws DrmLicensingException {
        Preconditions.checkNotNull(playReadyContentIdentifier, "contentIdentifier");
        Preconditions.checkNotNull(str, "base64EncodedChallenge");
        Preconditions.checkNotNull(map, "sessionContext");
        try {
            PlaybackResourcesV2Wrapper playbackResourcesV2Wrapper = this.mGetDrmLicenseResources.get(new PlayReadyLicenseRequest.Builder().titleId(playReadyContentIdentifier.getTitleId()).params(new PlayReadyLicenseParamsCreator(str, str2, playReadyContentIdentifier.getKeyId(), z).create()).sessionContext(map).responseListener(playbackResourcesResponseListener).build());
            Optional<PlaybackResourcesV2> playbackResources = playbackResourcesV2Wrapper.getPlaybackResources();
            if (!playbackResources.isPresent()) {
                if (playbackResourcesV2Wrapper.getError().isPresent()) {
                    throw GetPlaybackResourcesServiceClient.toDrmLicensingException(consumptionType, Prsv2Error.transformToPRSException(playbackResourcesV2Wrapper.getError().get()), DrmScheme.PLAYREADY);
                }
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, "Failed to fetch PlayReadyLicense", DrmScheme.PLAYREADY);
            }
            PlayReadyLicenseV2 playReadyLicenseV2OrNull = playbackResources.get().getPlayReadyLicense().orNull();
            if (playReadyLicenseV2OrNull != null) {
                return playReadyLicenseV2OrNull;
            }
            PRSException pRSException = playbackResources.get().getErrorsByResource().get(Resource.PlayReadyLicense);
            if (pRSException != null) {
                throw GetPlaybackResourcesServiceClient.toDrmLicensingException(consumptionType, pRSException, DrmScheme.PLAYREADY);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "Null PlayReadyLicense response from server", DrmScheme.PLAYREADY);
        } catch (ContentException | BoltException e) {
            throw new DrmLicensingException(e.toString());
        }
    }

    @Nonnull
    public WidevineLicenseV2 getWidevineLicense(@Nonnull String str, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, @Nonnull String str2, @Nonnull String str3, @Nonnull ConsumptionType consumptionType) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "base64EncodedChallenge");
        Preconditions.checkNotNull(map, "sessionContext");
        Preconditions.checkNotNull(str3, "titleId");
        try {
            PlaybackResourcesV2Wrapper playbackResourcesV2Wrapper = this.mGetDrmLicenseResources.get(new WidevineLicenseRequest.Builder().titleId(str3).params(new WidevineLicenseParamsCreator(str, str2).create()).sessionContext(map).responseListener(playbackResourcesResponseListener).build());
            Optional<PlaybackResourcesV2> playbackResources = playbackResourcesV2Wrapper.getPlaybackResources();
            if (!playbackResources.isPresent()) {
                if (playbackResourcesV2Wrapper.getError().isPresent()) {
                    throw GetPlaybackResourcesServiceClient.toDrmLicensingException(consumptionType, Prsv2Error.transformToPRSException(playbackResourcesV2Wrapper.getError().get()), DrmScheme.WIDEVINE);
                }
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE, "Failed to fetch WidevineLicense", DrmScheme.WIDEVINE);
            }
            WidevineLicenseV2 widevineLicenseV2OrNull = playbackResources.get().getWidevineLicenseResource().orNull();
            if (widevineLicenseV2OrNull != null) {
                return widevineLicenseV2OrNull;
            }
            PRSException pRSException = playbackResources.get().getErrorsByResource().get(Resource.Widevine2License);
            if (pRSException != null) {
                throw GetPlaybackResourcesServiceClient.toDrmLicensingException(consumptionType, pRSException, DrmScheme.WIDEVINE);
            }
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "Null WidevineLicense response from server", DrmScheme.WIDEVINE);
        } catch (ContentException | BoltException e) {
            throw new DrmLicensingException(e.toString());
        }
    }
}
