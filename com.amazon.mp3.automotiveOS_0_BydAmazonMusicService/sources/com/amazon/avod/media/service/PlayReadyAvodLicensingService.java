package com.amazon.avod.media.service;

import android.util.Xml;
import com.amazon.atvplaybackresource.PlayReadyLicense;
import com.amazon.atvplaybackresource.playready.AsinResponse;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.drm.event.LicenseAcquisitionEventReporter;
import com.amazon.avod.drm.playready.PlayReadyContentIdentifier;
import com.amazon.avod.drm.playready.PlayReadyHeader;
import com.amazon.avod.drm.playready.PlayReadyLicenseChallenge;
import com.amazon.avod.drm.playready.PlayReadyLicenseResponse;
import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.config.LicensingConfig;
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
import com.amazon.avod.playbackresourcev2.PlayReadyLicenseV2;
import com.amazon.avod.prs.GetPlayReadyLicensePlayerRequest;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.prs.PlaybackResourcesResponseListener;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyAvodLicensingService implements PlayReadyLicensingService, ResetObserver {
    private static final PlayReadyHeader mFixedPlayReadyHeader = PlayReadyHeader.fromBase64("EAMAAAEAAQAGAzwAVwBSAE0ASABFAEEARABFAFIAIAB4AG0AbABuAHMAPQAiAGgAdAB0AHAAOgAvAC8AcwBjAGgAZQBtAGEAcwAuAG0AaQBjAHIAbwBzAG8AZgB0AC4AYwBvAG0ALwBEAFIATQAvADIAMAAwADcALwAwADMALwBQAGwAYQB5AFIAZQBhAGQAeQBIAGUAYQBkAGUAcgAiACAAdgBlAHIAcwBpAG8AbgA9ACIANAAuADAALgAwAC4AMAAiAD4APABEAEEAVABBAD4APABQAFIATwBUAEUAQwBUAEkATgBGAE8APgA8AEsARQBZAEwARQBOAD4AMQA2ADwALwBLAEUAWQBMAEUATgA+ADwAQQBMAEcASQBEAD4AQQBFAFMAQwBUAFIAPAAvAEEATABHAEkARAA+ADwALwBQAFIATwBUAEUAQwBUAEkATgBGAE8APgA8AEsASQBEAD4ANgBnADYARABEAFUAUwBkAHYAVQB5AG0AMQB5AGYARgBmADIAagBrACsAQQA9AD0APAAvAEsASQBEAD4APABDAEgARQBDAEsAUwBVAE0APgBLAGEATwBzAGMASwB4AE4AagA2ADQAPQA8AC8AQwBIAEUAQwBLAFMAVQBNAD4APABMAEEAXwBVAFIATAA+AGgAdAB0AHAAcwA6AC8ALwBwAHIAbABzAC4AYQB0AHYALQBwAHMALgBhAG0AYQB6AG8AbgAuAGMAbwBtAC8AYwBkAHAAPAAvAEwAQQBfAFUAUgBMAD4APABDAFUAUwBUAE8ATQBBAFQAVABSAEkAQgBVAFQARQBTAD4APABJAEkAUwBfAEQAUgBNAF8AVgBFAFIAUwBJAE8ATgA+ADcALgAxAC4AMQA0ADMAOQAuADAAPAAvAEkASQBTAF8ARABSAE0AXwBWAEUAUgBTAEkATwBOAD4APAAvAEMAVQBTAFQATwBNAEEAVABUAFIASQBCAFUAVABFAFMAPgA8AC8ARABBAFQAQQA+ADwALwBXAFIATQBIAEUAQQBEAEUAUgA+AA==");
    private PlayReadyLicenseChallenge mFixedLicenseChallenge;
    private final GetDrmLicenseResourcesServiceClient mGetDrmLicenseResourcesServiceClient;
    private final GetPlaybackResourcesServiceClient mGetPlaybackResourcesServiceClient;
    private final LicensingConfig mLicensingConfig;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final XmlPullParser mXmlParser;

    public PlayReadyAvodLicensingService(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this(LicensingConfig.getInstance(), Xml.newPullParser(), new GetPlaybackResourcesServiceClient(playbackSupportEvaluator, GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest()), qOSCommunicationService), new GetDrmLicenseResourcesServiceClient(GetPlaybackResourcesFactory.createPlayReadyInstance(new GetPlayReadyLicensePlayerRequest())), PlaybackResourcesV2Config.getInstance());
    }

    PlayReadyAvodLicensingService(@Nonnull LicensingConfig licensingConfig, @Nonnull XmlPullParser xmlPullParser, @Nonnull GetPlaybackResourcesServiceClient getPlaybackResourcesServiceClient, @Nonnull GetDrmLicenseResourcesServiceClient getDrmLicenseResourcesServiceClient, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config) {
        this.mFixedLicenseChallenge = null;
        this.mLicensingConfig = (LicensingConfig) Preconditions.checkNotNull(licensingConfig, "config");
        this.mXmlParser = (XmlPullParser) Preconditions.checkNotNull(xmlPullParser, "xmlParser");
        this.mGetPlaybackResourcesServiceClient = (GetPlaybackResourcesServiceClient) Preconditions.checkNotNull(getPlaybackResourcesServiceClient, "getPlaybackResourcesServiceClient");
        this.mGetDrmLicenseResourcesServiceClient = (GetDrmLicenseResourcesServiceClient) Preconditions.checkNotNull(getDrmLicenseResourcesServiceClient, "getDrmLicenseResourcesServiceClient");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
    }

    private synchronized PlayReadyLicenseChallenge getLicenseChallenge(@Nonnull DrmFramework drmFramework, @Nonnull PlayReadyContentIdentifier playReadyContentIdentifier, boolean z) throws DrmLicensingException {
        boolean z2 = true;
        if (!drmFramework.supportsChallengeReplay()) {
            String strAsBase64 = playReadyContentIdentifier.getHeader().asBase64();
            DrmScheme drmScheme = DrmScheme.PLAYREADY;
            if (z) {
                z2 = false;
            }
            return new PlayReadyLicenseChallenge(drmFramework.generateLicenseChallenge(strAsBase64, drmScheme, z2));
        }
        if (this.mFixedLicenseChallenge == null) {
            String strAsBase642 = mFixedPlayReadyHeader.asBase64();
            DrmScheme drmScheme2 = DrmScheme.PLAYREADY;
            if (z) {
                z2 = false;
            }
            byte[] bArrGenerateLicenseChallenge = drmFramework.generateLicenseChallenge(strAsBase642, drmScheme2, z2);
            if (bArrGenerateLicenseChallenge == null) {
                throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "PlayReady returned a null license challenge for the fixed PlayReady header object", drmScheme2);
            }
            this.mFixedLicenseChallenge = new PlayReadyLicenseChallenge(bArrGenerateLicenseChallenge);
        }
        return this.mFixedLicenseChallenge;
    }

    @Override // com.amazon.avod.drm.playready.PlayReadyLicensingService
    public synchronized void resetState() {
        DLog.logf("Clearing cached license challenge to force a new one to be generated");
        this.mFixedLicenseChallenge = null;
    }

    @Override // com.amazon.avod.drm.playready.PlayReadyLicensingService
    @Nonnull
    public PlayReadyLicenseResponse getLicenseResponse(@Nonnull DrmFramework drmFramework, @Nonnull PlayReadyContentIdentifier playReadyContentIdentifier, @Nonnull ConsumptionType consumptionType, @Nullable final String str, @Nonnull ContentType contentType, boolean z, boolean z2, @Nonnull RendererSchemeType rendererSchemeType, final boolean z3, @Nullable String str2, boolean z4, @Nonnull final LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, @Nonnull Map<String, String> map, @Nullable PlaybackEnvelope playbackEnvelope) throws DrmLicensingException {
        PlayReadyLicenseChallenge licenseChallenge = getLicenseChallenge(drmFramework, playReadyContentIdentifier, z3);
        ImmutableList<PlayReadyContentIdentifier> immutableListOf = ImmutableList.of(playReadyContentIdentifier);
        PlaybackResourcesResponseListener<PlaybackResourcesWrapper> playbackResourcesResponseListener = new PlaybackResourcesResponseListener<PlaybackResourcesWrapper>() { // from class: com.amazon.avod.media.service.PlayReadyAvodLicensingService.1
            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPSuccess(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull Response<PlaybackResourcesWrapper> response, @Nonnull DownloadStatistics downloadStatistics) {
                licenseAcquisitionEventReporter.reportQOS2LicenseAcquisition(true, downloadStatistics, 200, z3, str, request.getUrl().toString());
            }

            @Override // com.amazon.avod.prs.PlaybackResourcesResponseListener
            public void onHTTPFailure(@Nonnull Request<PlaybackResourcesWrapper> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
                licenseAcquisitionEventReporter.reportQOS2LicenseAcquisition(false, downloadStatistics, 500, z3, str, request.getUrl().toString());
            }
        };
        if (this.mPlaybackResourcesV2Config.shouldCallPrsV2Service(playbackEnvelope, contentType)) {
            String envelope = playbackEnvelope.getEnvelope();
            if (envelope == null) {
                throw new DrmLicensingException(LicenseError.AIV_MISSING_PLAYBACK_ENVELOPE);
            }
            return getValidatedResponse(immutableListOf, this.mGetDrmLicenseResourcesServiceClient.getPlayReadyLicense(playReadyContentIdentifier, licenseChallenge.asBase64(), z2, map, null, envelope, consumptionType));
        }
        return getValidatedResponse(immutableListOf, this.mGetPlaybackResourcesServiceClient.getPlayReadyLicense(playReadyContentIdentifier, licenseChallenge.asBase64(), z, false, consumptionType, contentType, z2, playbackResourcesResponseListener, rendererSchemeType, str2, z4, map));
    }

    private PlayReadyLicenseResponse getValidatedResponse(@Nonnull ImmutableList<PlayReadyContentIdentifier> immutableList, @Nonnull PlayReadyLicense playReadyLicense) throws DrmLicensingException {
        boolean keyIdMismatchDetectionEnabled = this.mLicensingConfig.getKeyIdMismatchDetectionEnabled();
        PlayReadyLicenseResponse.Builder builderExtractPlaybackResourcesLicenseResponse = extractPlaybackResourcesLicenseResponse(playReadyLicense);
        String responseString = builderExtractPlaybackResourcesLicenseResponse.getResponseString();
        if (keyIdMismatchDetectionEnabled && responseString != null) {
            builderExtractPlaybackResourcesLicenseResponse = validateLicenseResponse(builderExtractPlaybackResourcesLicenseResponse, immutableList);
        }
        return builderExtractPlaybackResourcesLicenseResponse.build();
    }

    private PlayReadyLicenseResponse getValidatedResponse(@Nonnull ImmutableList<PlayReadyContentIdentifier> immutableList, @Nonnull PlayReadyLicenseV2 playReadyLicenseV2) throws DrmLicensingException {
        Preconditions.checkNotNull(immutableList, "contentIdentifiers");
        Preconditions.checkNotNull(playReadyLicenseV2, "playReadyLicense");
        Optional<String> encodedLicenseResponse = playReadyLicenseV2.getEncodedLicenseResponse();
        if (!encodedLicenseResponse.isPresent() || encodedLicenseResponse.get().isEmpty()) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetPlayReadyLicense succeeded but did not return a license response", DrmScheme.PLAYREADY);
        }
        PlayReadyLicenseResponse.Builder builderNewBuilder = PlayReadyLicenseResponse.newBuilder();
        builderNewBuilder.setResponseString(encodedLicenseResponse.get());
        if (this.mLicensingConfig.getKeyIdMismatchDetectionEnabled()) {
            builderNewBuilder = validateLicenseResponse(builderNewBuilder, immutableList);
        }
        return builderNewBuilder.build();
    }

    PlayReadyLicenseResponse.Builder extractPlaybackResourcesLicenseResponse(@Nonnull PlayReadyLicense playReadyLicense) throws DrmLicensingException {
        LicenseError licenseError;
        try {
            Optional<AsinResponse> optional = playReadyLicense.asinResponse;
            if (!optional.isPresent()) {
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetPlayReadyLicense succeeded but returned no request responses", DrmScheme.PLAYREADY);
            }
            PlayReadyLicenseResponse.Builder builderNewBuilder = PlayReadyLicenseResponse.newBuilder();
            String str = optional.get().asin.get();
            String value = optional.get().statusCode.get().getValue();
            String strOr = optional.get().message.or("none");
            DLog.logf("Response: ASIN %s, status %s, message \"%s\"", str, value, strOr);
            if (!value.equals("GRANTED")) {
                if (value.equals("DEGRADED")) {
                    licenseError = LicenseError.AIV_PLAYREADY_SERVICE_REQUEST_DEGRADED;
                } else if (value.equals("DENIED")) {
                    licenseError = LicenseError.AIV_PLAYREADY_SERVICE_REQUEST_DENIED;
                } else if (value.equals(MediaError.ERROR_TYPE_ERROR)) {
                    licenseError = LicenseError.AIV_PLAYREADY_SERVICE_REQUEST_ERROR;
                } else {
                    licenseError = LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE;
                }
                builderNewBuilder.addToErrorMap(str, new DrmLicensingException(licenseError, strOr));
                return builderNewBuilder.setResponseString(null);
            }
            Optional<String> optional2 = playReadyLicense.encodedLicenseResponse;
            if (!optional2.isPresent() || optional2.get().isEmpty()) {
                throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "GetPlayReadyLicense succeeded but did not return a license response", DrmScheme.PLAYREADY);
            }
            return builderNewBuilder.setResponseString(optional2.get());
        } catch (IllegalStateException e) {
            throw new DrmLicensingException(LicenseError.AIV_LICENSE_SERVICE_CALL_MALFORMED_RESPONSE, "IllegalStateException: " + e.getMessage(), DrmScheme.PLAYREADY);
        }
    }

    PlayReadyLicenseResponse.Builder validateLicenseResponse(@Nonnull PlayReadyLicenseResponse.Builder builder, @Nonnull List<PlayReadyContentIdentifier> list) {
        ArrayList arrayListNewArrayList = Lists.newArrayList(list);
        try {
            this.mXmlParser.setInput(new StringReader(new String(Base64.decode(builder.getResponseString()))));
            int next = this.mXmlParser.next();
            boolean z = false;
            while (next != 1) {
                if (next == 4 && z) {
                    PlayReadyContentIdentifier playReadyContentIdentifierExtractKeyId = extractKeyId(this.mXmlParser.getText(), arrayListNewArrayList);
                    if (playReadyContentIdentifierExtractKeyId != null) {
                        arrayListNewArrayList.remove(playReadyContentIdentifierExtractKeyId);
                    }
                } else {
                    z = Objects.equal(this.mXmlParser.getName(), "License") && next == 2;
                }
                next = this.mXmlParser.next();
            }
            StringBuilder sb = new StringBuilder();
            for (PlayReadyContentIdentifier playReadyContentIdentifier : arrayListNewArrayList) {
                if (playReadyContentIdentifier.getKeyId() != null) {
                    String str = String.format(Locale.US, " KeyID mismatch detected for %s: expected %s", playReadyContentIdentifier.getTitleId(), playReadyContentIdentifier.getKeyId());
                    sb.append(str);
                    builder.addToErrorMap(playReadyContentIdentifier.getTitleId(), new DrmLicensingException(LicenseError.AIV_PLAYREADY_SERVICE_CALL_KEYID_MISMATCH, str, DrmScheme.PLAYREADY));
                }
            }
            String string = sb.toString();
            if (!Strings.isNullOrEmpty(string)) {
                DLog.logf(string);
            }
            return builder;
        } catch (IOException | XmlPullParserException e) {
            DLog.exceptionf(e, "Unable to parse licensing server response XML. Cancelling response validation.", new Object[0]);
            return builder;
        }
    }

    PlayReadyContentIdentifier extractKeyId(@Nullable String str, @Nonnull List<PlayReadyContentIdentifier> list) throws IOException {
        if (str != null && str.length() >= 16 && !list.isEmpty()) {
            HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(list.size());
            for (PlayReadyContentIdentifier playReadyContentIdentifier : list) {
                if (playReadyContentIdentifier.getKeyIdBase64() != null) {
                    mapNewHashMapWithExpectedSize.put(Integer.valueOf(Arrays.hashCode(Base64.decode(playReadyContentIdentifier.getKeyIdBase64()))), playReadyContentIdentifier);
                }
            }
            if (mapNewHashMapWithExpectedSize.isEmpty()) {
                return null;
            }
            byte[] bArrDecode = Base64.decode(str);
            for (int i = 0; i < bArrDecode.length - 16; i++) {
                int iHashCode = Arrays.hashCode(Arrays.copyOfRange(bArrDecode, i, i + 16));
                if (mapNewHashMapWithExpectedSize.containsKey(Integer.valueOf(iHashCode))) {
                    return (PlayReadyContentIdentifier) mapNewHashMapWithExpectedSize.get(Integer.valueOf(iHashCode));
                }
            }
        }
        return null;
    }

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
        resetState();
    }
}
