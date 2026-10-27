package com.amazon.avod.media.service;

import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playbackresource.PlaybackResourceConfig;
import com.amazon.avod.prs.AdditionalParams;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackUrlsInformation implements AdditionalParams {
    private final AVODServiceConfig mAVODServiceConfig;
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final Optional<String> mAudioTrackId;
    private final ConsumptionType mConsumptionType;
    private final ContentType mContentType;
    private final String mDeviceAdInsertionTypeOverride;

    @Nonnull
    private final EPrivacyConsentData mEPrivacyConsent;
    private final boolean mForceDash;
    private final IdentityShim mIdentity;
    private final boolean mIsLicenseRefresh;
    private final boolean mIsSSAIEnabledForDownloads;
    private final boolean mIsSSAIEnabledForStreaming;
    private final Joiner mJoiner;
    private final MediaSystem mMediaSystem;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PlaybackResourceConfig mPlaybackResourceConfig;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;

    @Nullable
    private final String mPlaybackToken;

    @Nonnull
    private Map<String, String> mPostBodyParamsMap;
    private final QoSConfig mQoSConfig;
    private final RendererSchemeType mRendererSchemeType;
    private final boolean mShouldAddDashOverrides;
    private final boolean mShouldAppendChipsetInfo;
    private final boolean mShouldAppendManufacturerInfo;
    private final boolean mShouldAppendModelInfo;
    private final boolean mShouldAppendOSVersionInfo;
    private final boolean mShouldAppendPlayerVersionParams;
    private final boolean mShouldSuppressCuepointsForChildRole;
    private final boolean mShowAds;
    private final String mTitleId;
    private final Optional<String> mUserWatchSessionId;
    private final String mVASTManifestTimelineType;
    private final Optional<String> mVersion;

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasQueryStringParams() {
        return true;
    }

    public PlaybackUrlsInformation(@Nonnull String str, @Nonnull Optional<String> optional, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nullable AdvertisingIdCollector advertisingIdCollector, @Nullable PlaybackEventReporter playbackEventReporter, boolean z, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nullable boolean z2, @Nonnull EPrivacyConsentData ePrivacyConsentData) {
        this(MediaSystem.getInstance(), MediaSystemSharedDependencies.getInstance(), AVODServiceConfig.getInstance(), PlaybackResourceConfig.getInstance(), QoSConfig.INSTANCE, str, optional, consumptionType, contentType, optional2, optional3, advertisingIdCollector, playbackEventReporter, z, playbackSupportEvaluator, rendererSchemeType, str2, ePrivacyConsentData, false, true, AdsConfig.getInstance().isSSAIEnabled(), AdsConfig.getInstance().isSSAIEnabledForDownloads(), AdsConfig.getInstance().getDeviceAdInsertionTypeOverride(), AdsConfig.getInstance().getVASTManifestTimelineType(), AdsConfig.getInstance().shouldSuppressCuepointsForChildRole(), MediaSystemSharedDependencies.getInstance().getIdentityShim(), z2);
    }

    PlaybackUrlsInformation(@Nonnull String str, @Nonnull Optional<String> optional, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nullable AdvertisingIdCollector advertisingIdCollector, @Nullable PlaybackEventReporter playbackEventReporter, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nonnull EPrivacyConsentData ePrivacyConsentData, boolean z, boolean z2) {
        this(MediaSystem.getInstance(), MediaSystemSharedDependencies.getInstance(), AVODServiceConfig.getInstance(), PlaybackResourceConfig.getInstance(), QoSConfig.INSTANCE, str, optional, consumptionType, contentType, optional2, optional3, advertisingIdCollector, playbackEventReporter, false, playbackSupportEvaluator, rendererSchemeType, str2, ePrivacyConsentData, z, z2, AdsConfig.getInstance().isSSAIEnabled(), AdsConfig.getInstance().isSSAIEnabledForDownloads(), AdsConfig.getInstance().getDeviceAdInsertionTypeOverride(), AdsConfig.getInstance().getVASTManifestTimelineType(), AdsConfig.getInstance().shouldSuppressCuepointsForChildRole(), MediaSystemSharedDependencies.getInstance().getIdentityShim(), true);
    }

    @VisibleForTesting
    PlaybackUrlsInformation(@Nonnull MediaSystem mediaSystem, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, @Nonnull AVODServiceConfig aVODServiceConfig, @Nonnull PlaybackResourceConfig playbackResourceConfig, @Nonnull QoSConfig qoSConfig, @Nonnull String str, @Nonnull Optional<String> optional, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nullable AdvertisingIdCollector advertisingIdCollector, @Nullable PlaybackEventReporter playbackEventReporter, boolean z, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nonnull EPrivacyConsentData ePrivacyConsentData, boolean z2, boolean z3, boolean z4, boolean z5, String str3, String str4, boolean z6, @Nonnull IdentityShim identityShim, boolean z7) {
        this.mJoiner = Joiner.on(",").useForNull("");
        this.mMediaSystem = (MediaSystem) Preconditions.checkNotNull(mediaSystem, "mediaSystem");
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
        this.mPlaybackResourceConfig = (PlaybackResourceConfig) Preconditions.checkNotNull(playbackResourceConfig, "playbackResourceConfig");
        this.mShouldAppendPlayerVersionParams = playbackResourceConfig.shouldAppendPlayerVersionParams();
        this.mShouldAppendManufacturerInfo = playbackResourceConfig.shouldAppendManufacturerInfo();
        this.mShouldAppendModelInfo = playbackResourceConfig.shouldAppendModelInfo();
        this.mShouldAppendChipsetInfo = playbackResourceConfig.shouldAppendChipsetInfo();
        this.mShouldAppendOSVersionInfo = playbackResourceConfig.shouldAppendOSVersionInfo();
        this.mQoSConfig = (QoSConfig) Preconditions.checkNotNull(qoSConfig, "qoSConfig");
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mAudioTrackId = (Optional) Preconditions.checkNotNull(optional, "audioTrackId");
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
        this.mUserWatchSessionId = (Optional) Preconditions.checkNotNull(optional2, "userWatchSessionId");
        this.mVersion = (Optional) Preconditions.checkNotNull(optional3, "version");
        this.mAdvertisingIdCollector = advertisingIdCollector;
        this.mPlaybackEventReporter = playbackEventReporter;
        this.mForceDash = z;
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mRendererSchemeType = rendererSchemeType;
        this.mPlaybackToken = str2;
        this.mEPrivacyConsent = (EPrivacyConsentData) Preconditions.checkNotNull(ePrivacyConsentData, "ePrivacyConsent");
        this.mIsLicenseRefresh = z2;
        this.mShouldAddDashOverrides = z3;
        this.mPostBodyParamsMap = buildPostBodyParams();
        this.mIsSSAIEnabledForStreaming = z4;
        this.mIsSSAIEnabledForDownloads = z5;
        this.mDeviceAdInsertionTypeOverride = str3;
        this.mVASTManifestTimelineType = (String) Preconditions.checkNotNull(str4, "vastManifestTimelineType");
        this.mShouldSuppressCuepointsForChildRole = z6;
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identityShim");
        this.mShowAds = z7;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasPostBodyParams() {
        return !Strings.isNullOrEmpty(this.mPlaybackToken);
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getPostBodyParams() {
        return this.mPostBodyParamsMap;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getQueryStringParams() {
        ImmutableList<PlaybackResourceServiceConstants$HdrFormat> immutableListOf;
        Map<String, String> map = new HashMap<>();
        if (this.mAVODServiceConfig.shouldPassAppIdentificationParameterInPlaybackUrlCalls()) {
            map.put("deviceApplicationName", this.mMediaSystemSharedDependencies.getDeviceIdentity().getAppPackageName());
        }
        if (this.mShouldAppendPlayerVersionParams && this.mMediaSystemSharedDependencies.isSDKPlayer() && this.mMediaSystem.getPlayerSdkVersion() != null) {
            map.put("playerVersion", this.mMediaSystem.getPlayerSdkVersion());
            map.put("playerType", this.mMediaSystem.getPlayerName());
        }
        if (isSSAIEnabled() && ((!this.mShouldSuppressCuepointsForChildRole || !this.mIdentity.isChild()) && this.mShowAds)) {
            map.put("deviceAdInsertionTypeOverride", this.mDeviceAdInsertionTypeOverride);
            map.put("vastTimelineType", this.mVASTManifestTimelineType);
        }
        map.put("deviceVideoQualityOverride", PlaybackResourceServiceConstants$VideoQuality.SD.toString());
        if (this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType)) {
            map.put("deviceVideoQualityOverride", PlaybackResourceServiceConstants$VideoQuality.HD.toString());
        }
        if (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType)) {
            map.put("deviceVideoQualityOverride", PlaybackResourceServiceConstants$VideoQuality.UHD.toString());
        }
        boolean z = this.mAVODServiceConfig.isVideoFrameRateOverrideEnabled() && (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType));
        if (z) {
            map.put("deviceFrameRateOverride", PlaybackResourceServiceConstants$VideoFrameRate.High.toString());
        }
        map.putAll(new AdvertisingIdInformation(this.mAdvertisingIdCollector, this.mAVODServiceConfig.isAdvertisingIdCollectorUseStaleData(), this.mAVODServiceConfig.getAdvertisingIdCollectorTimeoutInMillis(), false, z, null).getQueryStringParams());
        if (isHdrSupportedAndAllowed()) {
            immutableListOf = this.mPlaybackSupportEvaluator.getSupportedHdrFormats(this.mRendererSchemeType);
        } else {
            immutableListOf = ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
        }
        DLog.devf("HdrMode: supported hdr modes %s", this.mJoiner.join(immutableListOf));
        map.put("deviceHdrFormatsOverride", this.mJoiner.join(immutableListOf));
        map.put("deviceVideoCodecOverride", this.mJoiner.join(this.mPlaybackSupportEvaluator.getDeviceVideoCodecOverride(this.mRendererSchemeType)));
        map.put("liveManifestType", this.mAVODServiceConfig.getLiveManifestCapabilities());
        if (this.mPlaybackResourceConfig.shouldUseDynamicAdInsertedLiveManifestCapabilities()) {
            map.put("daiLiveManifestType", this.mPlaybackResourceConfig.getDynamicAdInsertedLiveManifestCapabilities());
        }
        if (this.mAVODServiceConfig.supportsEmbeddedTrickplay()) {
            map.put("supportsEmbeddedTrickplay", Boolean.toString(true));
        }
        if (this.mAVODServiceConfig.supportsDAIEmbeddedTrickplay()) {
            map.put("daiSupportsEmbeddedTrickplay", Boolean.toString(true));
        }
        if (this.mShouldAddDashOverrides) {
            addRequestOverrides(map);
        }
        if (this.mAudioTrackId.isPresent()) {
            map.put("audioTrackId", this.mAudioTrackId.get());
        }
        if (this.mUserWatchSessionId.isPresent()) {
            map.put("userWatchSessionId", this.mUserWatchSessionId.get());
        }
        if (this.mVersion.isPresent()) {
            map.put("version", this.mVersion.get());
        }
        map.put("languageFeature", "MLFv2");
        if (this.mPlaybackSupportEvaluator.isMultiKeyDecryptionSupported(this.mRendererSchemeType, this.mContentType)) {
            map.put("supportedDRMKeyScheme", "DUAL_KEY");
        }
        if (this.mShouldAppendManufacturerInfo) {
            map.put("manufacturer", Build.MANUFACTURER);
        }
        if (this.mShouldAppendModelInfo) {
            map.put(DeviceInformation.PARAMS_DEVICE_MODEL, Build.MODEL);
        }
        if (this.mShouldAppendChipsetInfo) {
            map.put("deviceChipset", Build.BOARD);
        }
        if (this.mShouldAppendOSVersionInfo) {
            map.put("operatingSystemVersion", Build.FINGERPRINT);
        }
        DLog.devf("PlaybackUrlInformation: Adding https DEVICE_PROTOCOL_OVERRIDE");
        map.put("deviceProtocolOverride", "Https");
        if (this.mPlaybackResourceConfig.shouldAppendGDPRCookieConsent()) {
            map.put("gdprEnabled", Boolean.toString(true));
            String ePrivacyConsentGVL = Strings.isNullOrEmpty(this.mEPrivacyConsent.getEPrivacyConsentGVL()) ? "null" : this.mEPrivacyConsent.getEPrivacyConsentGVL();
            String ePrivacyConsentAVL = Strings.isNullOrEmpty(this.mEPrivacyConsent.getEPrivacyConsentAVL()) ? "null" : this.mEPrivacyConsent.getEPrivacyConsentAVL();
            map.put("gdprConsentGVL", ePrivacyConsentGVL);
            map.put("gdprConsentAVL", ePrivacyConsentAVL);
        }
        return map;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.PlaybackUrlsInformation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType;

        static {
            int[] iArr = new int[ConsumptionType.values().length];
            $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType = iArr;
            try {
                iArr[ConsumptionType.Streaming.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[ConsumptionType.Download.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean isSSAIEnabled() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$support$ConsumptionType[this.mConsumptionType.ordinal()];
        if (i == 1) {
            return this.mIsSSAIEnabledForStreaming;
        }
        if (i == 2) {
            return this.mIsSSAIEnabledForDownloads;
        }
        DLog.logf("Invalid consumption type: %s", this.mConsumptionType.name());
        return false;
    }

    private boolean isHdrSupportedAndAllowed() {
        if (!this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType)) {
            return false;
        }
        if (this.mConsumptionType == ConsumptionType.Streaming || this.mIsLicenseRefresh) {
            return true;
        }
        return this.mAVODServiceConfig.areHdrDownloadsAllowed();
    }

    @Nonnull
    private Map<String, String> buildPostBodyParams() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (!Strings.isNullOrEmpty(this.mPlaybackToken)) {
            builder.put("playbackToken", this.mPlaybackToken);
        }
        return builder.build();
    }

    @VisibleForTesting
    void addRequestOverrides(@Nonnull Map<String, String> map) {
        if (this.mForceDash || this.mAVODServiceConfig.isDashOverrideEnabled(this.mContentType, this.mConsumptionType)) {
            List<String> deviceBitrateAdaptationsOverride = this.mPlaybackSupportEvaluator.getDeviceBitrateAdaptationsOverride(this.mRendererSchemeType);
            if (!this.mForceDash && !ContentType.isLive(this.mContentType) && deviceBitrateAdaptationsOverride.isEmpty()) {
                String str = String.format(Locale.US, "ConfigVersion: %s.", this.mQoSConfig.getReportingConfigVersion());
                DLog.logf("Looks like we have some bad server configs - %s", "Requesting DASH without specifying a bitrate adaptation.");
                PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
                if (playbackEventReporter != null) {
                    playbackEventReporter.reportError("BadStreamingOverrides", "Requesting DASH without specifying a bitrate adaptation.", str, this.mTitleId, false);
                    return;
                }
                return;
            }
            map.put("deviceStreamingTechnologyOverride", "DASH");
            map.put("deviceDrmOverride", "CENC");
            if (deviceBitrateAdaptationsOverride.isEmpty()) {
                return;
            }
            map.put("deviceBitrateAdaptationsOverride", this.mJoiner.join(deviceBitrateAdaptationsOverride));
        }
    }
}
